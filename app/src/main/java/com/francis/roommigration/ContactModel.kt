package com.francis.roommigration

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ContactModel")
data class ContactModel(
    @ColumnInfo(name = "name") var name: String?,
    @PrimaryKey @ColumnInfo(name = "number") var number: String,
    @ColumnInfo(name = "age") var age: Int?,
    @ColumnInfo(name = "isVerified") var isVerified: Boolean?
)


//@TypeConverters(Converters.class)  subclass