package com.dengfx.smart.db

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.dengfx.smart.gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import java.lang.reflect.Type

@Database(
    entities = [Curriculum::class, Attendance::class, Equipment::class, Book::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [DateConverter::class, BigDecimalConverter::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun curriculumDao(): CurriculumDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun equipmentDao(): EquipmentDao
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(context, AppDataBase::class.java, "smart_campus")
                .addMigrations(migration_1_2)
                .fallbackToDestructiveMigration()
                .setJournalMode(JournalMode.WRITE_AHEAD_LOGGING)
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        WorkManager.getInstance(context)
                            .enqueue(OneTimeWorkRequestBuilder<SeedDataBaseWorker>().build())
                    }
                })
                .build().also { instance = it }
        }
    }
}

private val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
    }
}


class SeedDataBaseWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {

        val database: AppDataBase = AppDataBase.getInstance(applicationContext)
        val assetManager = applicationContext.assets

        try {
            assetManager.open("curriculums.json", AssetManager.ACCESS_BUFFER).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type: Type = object : TypeToken<List<Curriculum>>() {}.type
                    val curriculums = gson.fromJson<List<Curriculum>>(jsonReader, type)
                    database.curriculumDao().insertCurriculums(curriculums)
                }
            }

            assetManager.open("attendances.json", AssetManager.ACCESS_BUFFER).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type: Type = object : TypeToken<List<Attendance>>() {}.type
                    val attendances = gson.fromJson<List<Attendance>>(jsonReader, type)
                    database.attendanceDao().insertAttendances(attendances)
                }
            }

            assetManager.open("equipments.json", AssetManager.ACCESS_BUFFER).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type: Type = object : TypeToken<List<Equipment>>() {}.type
                    val equipments = gson.fromJson<List<Equipment>>(jsonReader, type)
                    database.equipmentDao().insertEquipments(equipments)
                }
            }

            assetManager.open("books.json", AssetManager.ACCESS_BUFFER).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type: Type = object : TypeToken<List<Book>>() {}.type
                    val books = gson.fromJson<List<Book>>(jsonReader, type)
                    database.bookDao().insertBooks(books)
                }
            }


            Log.e("AAAA", "success")
            Result.success()
        } catch (ex: Exception) {
            Log.e("AAAA", "failure : $ex")
            ex.printStackTrace()
            Result.failure()
        }
    }
}