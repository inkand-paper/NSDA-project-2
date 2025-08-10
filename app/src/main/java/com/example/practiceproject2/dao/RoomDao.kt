package com.example.practiceproject2.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practiceproject2.model.RoomEntity


@Dao
interface RoomDao {
    @Insert
    suspend fun insert(user: RoomEntity)
    @Update
    suspend fun update(user: RoomEntity)
    @Delete
    suspend fun delete(user: RoomEntity)

    @Query("Select * FROM user_table")
    fun getAllData(): LiveData<List<RoomEntity>>

    @Query("Select * FROM user_table WHERE id = :id")
    fun getUserById(id: Int): LiveData<RoomEntity>
}