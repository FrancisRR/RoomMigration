package com.francis.roommigration

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ContactModel::class, UserModel::class), version = 3)
abstract class RoomDatabaseEx : RoomDatabase() {
    abstract fun getDao(): RoomDao
}