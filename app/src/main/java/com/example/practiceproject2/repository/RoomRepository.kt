package com.example.practiceproject2.repository

import androidx.lifecycle.LiveData
import com.example.practiceproject2.dao.RoomDao
import com.example.practiceproject2.model.RoomEntity

class RoomRepository(private val userDao: RoomDao) {
    fun getAllData(): LiveData<List<RoomEntity>>{
        return userDao.getAllData()
    }
    fun getUserById(id: Int): LiveData<RoomEntity>{
        return userDao.getUserById(id)
    }

    suspend fun insert(user: RoomEntity){
        userDao.insert(user)
    }
    suspend fun update(user: RoomEntity){
        userDao.update(user)
    }
    suspend fun delete(user: RoomEntity){
        userDao.delete(user)
    }
}