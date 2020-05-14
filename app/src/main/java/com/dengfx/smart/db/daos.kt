package com.dengfx.smart.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CurriculumDao {
    @Query("SELECT * FROM curriculums ORDER BY id")
    fun getCurriculums(): LiveData<List<Curriculum>>

    @Query("SELECT * FROM curriculums WHERE id = :id ORDER BY id")
    fun getCurriculum(id: Long): LiveData<Curriculum>

    @Insert(entity = Curriculum::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurriculum(curriculum: Curriculum): Long

    @Insert(entity = Curriculum::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurriculums(curriculums: List<Curriculum>)

    @Update(entity = Curriculum::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurriculums(curriculums: List<Curriculum>): Int

    @Delete(entity = Curriculum::class)
    suspend fun deleteCurriculum(curriculums: List<Curriculum>): Int
}

@Dao
interface AttendanceDao {
    @Query("SELECT * FROM attendances ORDER BY id")
    fun getAttendances(): LiveData<List<Attendance>>

    @Query("SELECT * FROM attendances WHERE curriculum_id = :curriculumId ORDER BY id")
    fun getAttendances(curriculumId: Long): LiveData<List<Attendance>>

    @Insert(entity = Attendance::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendance: Attendance): Long

    @Insert(entity = Attendance::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendances(attendances: List<Attendance>)

    @Update(entity = Attendance::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAttendances(attendances: List<Attendance>): Int

    @Delete(entity = Attendance::class)
    suspend fun deleteAttendances(attendances: List<Attendance>): Int
}

@Dao
interface EquipmentDao {
    @Query("SELECT * FROM equipments ORDER BY id")
    fun getEquipments(): LiveData<List<Equipment>>

    @Query("SELECT * FROM equipments WHERE type = :type ORDER BY id")
    fun getEquipments(type: Int): LiveData<List<Equipment>>

    @Insert(entity = Equipment::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEquipment(equipment: Equipment): Long

    @Insert(entity = Equipment::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEquipments(equipments: List<Equipment>)

    @Update(entity = Equipment::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEquipment(equipment: Equipment): Int

    @Update(entity = Equipment::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEquipments(equipments: List<Equipment>): Int

    @Delete(entity = Equipment::class)
    suspend fun deleteEquipments(equipments: List<Equipment>): Int

    @Delete(entity = Equipment::class)
    suspend fun deleteEquipment(equipment: Equipment): Int
}

@Dao
interface BookDao {
    @Query("SELECT * FROM books ORDER BY id")
    fun getBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE equipment_id = :equipmentId ORDER BY id")
    fun getBooks(equipmentId: Long): LiveData<List<Book>>

    @Insert(entity = Book::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book): Long

    @Insert(entity = Book::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<Book>)

    @Update(entity = Book::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBook(book: Book): Int

    @Update(entity = Book::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBooks(books: List<Book>): Int

    @Delete(entity = Book::class)
    suspend fun deleteBooks(books: List<Book>): Int

    @Delete(entity = Book::class)
    suspend fun deleteBook(book: Book): Int
}

@Dao
interface ConsumptionDao {
    @Query("SELECT * FROM consumptions ORDER BY id")
    fun getConsumptions(): LiveData<List<Consumption>>

    @Insert(entity = Consumption::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsumption(consumption: Consumption): Long

    @Insert(entity = Consumption::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsumptions(consumptions: List<Consumption>)

    @Update(entity = Consumption::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateConsumption(consumption: Consumption): Int

    @Update(entity = Consumption::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateConsumptions(consumptions: List<Consumption>): Int

    @Delete(entity = Consumption::class)
    suspend fun deleteConsumptions(consumptions: List<Consumption>): Int

    @Delete(entity = Consumption::class)
    suspend fun deleteConsumption(consumption: Consumption): Int
}