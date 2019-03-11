package com.francis.roommigration

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ContactModel::class), version = 1)
abstract class RoomDatabaseEx : RoomDatabase() {
    abstract fun getDao(): RoomDao
}