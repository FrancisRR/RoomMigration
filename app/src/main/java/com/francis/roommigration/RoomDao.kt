package com.francis.roommigration

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable

@Dao
interface RoomDao {

    @Query("select * from ContactModel")
    fun getAll(): LiveData<MutableList<ContactModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg data: ContactModel?)

    @Insert
    fun insertList(data: MutableList<ContactModel>?)


    @Query("delete from ContactModel where name=:inputName ")
    fun deleteId(inputName: String?): Completable

    @Delete
    fun delete(data: ContactModel?)

    @Query("DELETE FROM ContactModel")
    fun nukeTable()

}