package com.emonics.covidtracker.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.emonics.covidtracker.data.Data
import com.emonics.covidtracker.data.DataDatabase
import com.emonics.covidtracker.data.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel (application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Data>>


    private val repository: DataRepository

    init {
        val dataDao = DataDatabase.getDatabase(
            application
        ).dataDao()
        repository = DataRepository(dataDao)
        readAllData = repository.readAllData
    }

    fun addData(data: Data){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addData(data)
        }
    }

    fun updateData(data: Data){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(data)
        }
    }

      fun readByState(state:Int):LiveData<List<Data>>{

             return repository.readByState(state)

    }

}