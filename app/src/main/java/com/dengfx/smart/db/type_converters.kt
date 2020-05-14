package com.dengfx.smart.db

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.*


class DateConverter {
    @TypeConverter
    fun revertDate(value: Long): Date = Date(value)

    @TypeConverter
    fun convertDate(value: Date) = value.time
}

class BigDecimalConverter {
    @TypeConverter
    fun fromLong(value: Long?): BigDecimal? = value?.let {
        BigDecimal(value).divide(BigDecimal(100))
    } ?: BigDecimal.ZERO

    @TypeConverter
    fun toLong(bigDecimal: BigDecimal?): Long? =
        bigDecimal?.multiply(BigDecimal(100))?.longValueExact() ?: 0L
}