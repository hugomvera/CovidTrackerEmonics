package com.emonics.covidtracker.data

import androidx.lifecycle.LiveData

class DataRepository (private val dataDao: DataDao) {

    val readAllData: LiveData<List<Data>> = dataDao.readAllData()

    //this is for the query
     //val readbyState: LiveData<List<Data>> = dataDao.readByState()

    //fun getPlantsWithGrowZoneNumber(growZoneNumber: Int) =
    //        plantDao.getPlantsWithGrowZoneNumber(growZoneNumber)

       fun readByState(state:Int,date1:Int,date2:Int) :LiveData<List<Data>>  {
        return dataDao.readByState(state,date1,date2)
    }


    suspend fun addData(data: Data){
        dataDao.addData(data)
    }

    suspend fun updateData(data: Data){
        dataDao.updateData(data)
    }

}