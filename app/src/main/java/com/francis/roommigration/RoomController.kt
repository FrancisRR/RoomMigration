package com.francis.roommigration

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

open class RoomController(private val context: Context?) {


    internal fun roomInstance() = Room.databaseBuilder(context!!, RoomDatabaseEx::class.java, "demo_db")
        .fallbackToDestructiveMigration().build()


    val migration1 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            val MigString = "ALTER TABLE ContactModel ADD COLUMN age INTEGER NOT NULL DEFAULT 0"
            database.execSQL(MigString)
        }
    }


    val migration2 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            val migString = "ALTER TABLE ContactModel ADD COLUMN isVerified Boolean"
            database.execSQL(migString)
        }
    }

}