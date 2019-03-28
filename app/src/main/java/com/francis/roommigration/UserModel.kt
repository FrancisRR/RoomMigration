package com.francis.roommigration

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserModel")
class UserModel(@ColumnInfo(name = "name") @PrimaryKey val name: String)