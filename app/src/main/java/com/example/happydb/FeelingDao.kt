package com.example.happydb
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FeelingDao {

    @Insert
    suspend fun insertFeeling(feeling: Feeling)

    @Query("Select * From feeling")
    suspend fun getAllRecords():LiveData<List<Feeling>>

    @Query("Select * From feeling WHERE id=:search_id")
    suspend fun getOneRecord(search_id:Int)
}