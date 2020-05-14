package com.dengfx.smart.db

import androidx.lifecycle.LiveData

class CurriculumRepository private constructor(private val curriculumDao: CurriculumDao) {
    companion object {

        @Volatile
        private var instance: CurriculumRepository? = null

        fun getInstance(curriculumDao: CurriculumDao): CurriculumRepository =
            instance ?: synchronized(this) {
                instance ?: CurriculumRepository(curriculumDao).also { instance = it }
            }
    }

    fun getCurriculums(): LiveData<List<Curriculum>> = curriculumDao.getCurriculums()

    suspend fun getCurriculum(id: Long): LiveData<Curriculum> = curriculumDao.getCurriculum(id)

    suspend fun insertCurriculum(curriculum: Curriculum): Long =
        curriculumDao.insertCurriculum(curriculum)

    suspend fun insertCurriculums(curriculums: List<Curriculum>) =
        curriculumDao.insertCurriculums(curriculums)

    suspend fun updateCurriculums(curriculums: List<Curriculum>): Int =
        curriculumDao.updateCurriculums(curriculums)

    suspend fun deleteCurriculum(curriculums: List<Curriculum>): Int =
        curriculumDao.deleteCurriculum(curriculums)
}

class AttendanceRepository private constructor(private val attendanceDao: AttendanceDao) {
    companion object {

        @Volatile
        private var instance: AttendanceRepository? = null

        fun getInstance(attendanceDao: AttendanceDao): AttendanceRepository =
            instance ?: synchronized(this) {
                instance ?: AttendanceRepository(attendanceDao).also { instance = it }
            }
    }

    fun getAttendances(): LiveData<List<Attendance>> = attendanceDao.getAttendances()

    fun getAttendances(curriculumId: Long): LiveData<List<Attendance>> =
        attendanceDao.getAttendances(curriculumId)

    suspend fun insertAttendance(attendance: Attendance): Long =
        attendanceDao.insertAttendance(attendance)

    suspend fun insertAttendances(attendances: List<Attendance>) =
        attendanceDao.insertAttendances(attendances)

    suspend fun updateAttendances(attendances: List<Attendance>): Int =
        attendanceDao.updateAttendances(attendances)

    suspend fun deleteAttendances(attendances: List<Attendance>): Int =
        attendanceDao.deleteAttendances(attendances)
}

class EquipmentRepository private constructor(private val equipmentDao: EquipmentDao) {
    companion object {

        @Volatile
        private var instance: EquipmentRepository? = null

        fun getInstance(equipmentDao: EquipmentDao): EquipmentRepository =
            instance ?: synchronized(this) {
                instance ?: EquipmentRepository(equipmentDao).also { instance = it }
            }
    }

    fun getEquipments(): LiveData<List<Equipment>> = equipmentDao.getEquipments()

    fun getEquipments(type: Int): LiveData<List<Equipment>> = equipmentDao.getEquipments(type)

    suspend fun insertEquipment(equipment: Equipment): Long =
        equipmentDao.insertEquipment(equipment)

    suspend fun insertEquipments(equipments: List<Equipment>) =
        equipmentDao.insertEquipments(equipments)

    suspend fun updateEquipment(equipment: Equipment): Int =
        equipmentDao.updateEquipment(equipment)

    suspend fun updateEquipments(equipments: List<Equipment>): Int =
        equipmentDao.updateEquipments(equipments)

    suspend fun deleteEquipments(equipments: List<Equipment>): Int =
        equipmentDao.deleteEquipments(equipments)

    suspend fun deleteEquipment(equipment: Equipment): Int =
        equipmentDao.deleteEquipment(equipment)
}

class BookRepository private constructor(private val bookDao: BookDao) {
    companion object {

        @Volatile
        private var instance: BookRepository? = null

        fun getInstance(bookDao: BookDao): BookRepository =
            instance ?: synchronized(this) {
                instance ?: BookRepository(bookDao).also { instance = it }
            }
    }

    fun getBooks(): LiveData<List<Book>> = bookDao.getBooks()

    fun getBooks(equipmentId: Long): LiveData<List<Book>> = bookDao.getBooks(equipmentId)

    suspend fun insertBook(book: Book): Long = bookDao.insertBook(book)

    suspend fun insertBooks(books: List<Book>) = bookDao.insertBooks(books)

    suspend fun updateBook(book: Book): Int = bookDao.updateBook(book)

    suspend fun updateBooks(books: List<Book>): Int = bookDao.updateBooks(books)

    suspend fun deleteBooks(books: List<Book>): Int = bookDao.deleteBooks(books)

    suspend fun deleteBook(book: Book): Int = bookDao.deleteBook(book)
}

class ConsumptionRepository private constructor(private val consumptionDao: ConsumptionDao) {
    companion object {

        @Volatile
        private var instance: ConsumptionRepository? = null

        fun getInstance(consumptionDao: ConsumptionDao): ConsumptionRepository =
            instance ?: synchronized(this) {
                instance ?: ConsumptionRepository(consumptionDao).also { instance = it }
            }
    }

    fun getConsumptions(): LiveData<List<Consumption>> = consumptionDao.getConsumptions()

    suspend fun insertConsumption(consumption: Consumption): Long =
        consumptionDao.insertConsumption(consumption)

    suspend fun insertConsumptions(consumptions: List<Consumption>) =
        consumptionDao.insertConsumptions(consumptions)

    suspend fun updateConsumption(consumption: Consumption): Int =
        consumptionDao.updateConsumption(consumption)

    suspend fun updateConsumptions(consumptions: List<Consumption>): Int =
        consumptionDao.updateConsumptions(consumptions)

    suspend fun deleteConsumptions(consumptions: List<Consumption>): Int =
        consumptionDao.deleteConsumptions(consumptions)

    suspend fun deleteConsumption(consumption: Consumption): Int =
        consumptionDao.deleteConsumption(consumption)

}