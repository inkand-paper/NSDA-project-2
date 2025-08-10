package com.example.practiceproject2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.practiceproject2.database.RoomDatabase
import com.example.practiceproject2.model.RoomEntity
import com.example.practiceproject2.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(application: Application): AndroidViewModel(application) {
    private val repository: RoomRepository

    init {
        val userDao = RoomDatabase.getDatabase(application).roomDao()
        repository = RoomRepository(userDao)
    }

    fun getAllData(): LiveData<List<RoomEntity>>{
        return repository.getAllData()
    }

    fun getUserById(id: Int): LiveData<RoomEntity>{
        return repository.getUserById(id)
    }

    fun insert(user: RoomEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
    }

    fun update(user: RoomEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(user)
        }
    }
    fun delete(user: RoomEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }
}