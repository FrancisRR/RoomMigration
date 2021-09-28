package com.francis.roommigration

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(ContactModel::class, UserModel::class), version = 3)
@TypeConverters(SubModelTypeConverter::class, ChildModelTypeConverter::class)
abstract class RoomDatabaseEx : RoomDatabase() {
    abstract fun getDao(): RoomDao
}