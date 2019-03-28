package com.francis.roommigration

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

open class RoomController(private val context: Context?) {


    internal fun roomInstance() = Room.databaseBuilder(context!!, RoomDatabaseEx::class.java, "demo_db")
        .addMigrations(migration1)
        .addMigrations(migration2)
        .build()


    val migration1 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            val MigString = "CREATE TABLE 'UserModel' ('name' TEXT NOT NULL, PRIMARY KEY (`name`) ) "
            database.execSQL(MigString)
        }
    }


    val migration2 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            val MigString = "ALTER TABLE ContactModel ADD COLUMN isNew INTEGER NOT NULL DEFAULT 1"
            database.execSQL(MigString)
        }
    }


}