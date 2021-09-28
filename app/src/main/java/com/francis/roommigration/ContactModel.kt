package com.francis.roommigration

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


@Entity(tableName = "ContactModel")
data class ContactModel(
    @ColumnInfo(name = "name") var name: String?,
    @PrimaryKey @ColumnInfo(name = "number") var number: String,
    @ColumnInfo(name = "age") var age: Int?,
    @ColumnInfo(name = "isVerified") var isVerified: Boolean?,
    @ColumnInfo(name = "isNew") var isNew: Boolean,
    @ColumnInfo(name = "subList") var subList: MutableList<SubModel>,
    @ColumnInfo(name = "childModel") var childModel: ChildModel
)


data class SubModel(
    @ColumnInfo(name = "name") var name: String?,
    @PrimaryKey @ColumnInfo(name = "number") var number: String
)

data class ChildModel(
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "number") var number: String
)


object SubModelTypeConverter {
    @TypeConverter
    fun fromData(list: MutableList<SubModel>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toResult(value: String?): MutableList<SubModel> {
        val listType: Type = object : TypeToken<MutableList<SubModel>>() {}.getType()
        return Gson().fromJson(value, listType)
    }


}

object ChildModelTypeConverter {
    @TypeConverter
    fun fromData(model: ChildModel?): String {
        val gson = Gson()
        return gson.toJson(model)
    }

    @TypeConverter
    fun toResult(value: String?): ChildModel {
        return Gson().fromJson(value, ChildModel::class.java)
    }


}

