package com.emonics.covidtracker.data

import androidx.lifecycle.LiveData

class DataRepository (private val dataDao: DataDao) {

    val readAllData: LiveData<List<Data>> = dataDao.readAllData()

    suspend fun addUser(data: Data){
        dataDao.addData(data)
    }

    suspend fun updateData(data: Data){
        dataDao.updateData(data)
    }

}