package com.dengfx.smart.db

import androidx.annotation.Keep
import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

@Keep
@Entity(
    tableName = "curriculums",
    indices = [Index("id")]
)

data class Curriculum(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String,
    @ColumnInfo(name = "teacher_name")
    @SerializedName("teacher_name")
    var teacherName: String,
    @ColumnInfo(name = "college_name")
    @SerializedName("college_name")
    var collegeName: String,
    var location: String,
    var date: Date
) : Serializable

@Keep
@Entity(
    tableName = "attendances",
    foreignKeys = [
        ForeignKey(
            entity = Curriculum::class,
            parentColumns = ["id"],
            childColumns = ["curriculum_id"]
        )
    ],
    indices = [Index("curriculum_id")]
)
data class Attendance(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "curriculum_id")
    @SerializedName("curriculum_id")
    var curriculumId: Long,
    @ColumnInfo(name = "student_name")
    @SerializedName("student_name")
    var studentName: String,
    @ColumnInfo(name = "student_number")
    @SerializedName("student_number")
    var studentNumber: String,
    @ColumnInfo(name = "student_class")
    @SerializedName("student_class")
    var studentClass: String,
    var date: Date
) : Serializable

@Keep
@Entity(tableName = "equipments")
data class Equipment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String,
    var number: String,
    var ip: String,
    var status: Boolean, //在线true 离线false
    @ColumnInfo(name = "person_in_charge")
    @SerializedName("person_in_charge")
    var personInCharge: String,//负责人
    @ColumnInfo(name = "phone_number")
    @SerializedName("phone_number")
    var phoneNumber: String,
    var location: String? ,
    //1:图书馆电脑 2:安防相机 0:其他
    var type: Int = 0
) : Serializable

@Keep
@Entity(
    tableName = "books",
    foreignKeys = [
        ForeignKey(
            entity = Equipment::class,
            parentColumns = ["id"],
            childColumns = ["equipment_id"]
        )
    ],
    indices = [Index("equipment_id")]
)
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "equipment_id")
    @SerializedName("equipment_id")
    var equipmentId: Long,
    var name: String,
    var location: String,
    var status: Boolean, //在线true 离线false
    @ColumnInfo(name = "update_date")
    @SerializedName("update_date")
    var updateDate: Date = Calendar.getInstance().time
) : Serializable

@Keep
@Entity(tableName = "consumptions")
data class Consumption(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "student_name")
    @SerializedName("student_name")
    var studentName: String,
    @ColumnInfo(name = "student_number")
    @SerializedName("student_number")
    var studentNumber: String,
    var amount: BigDecimal? = BigDecimal.ZERO,
    var location: String,
    var date: Date = Calendar.getInstance().time
) : Serializable

