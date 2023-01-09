package com.emonics.covidtracker.data

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface DataDao {



    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addData(data: Data)

    @Update
     fun updateData(data: Data)

    @Query("SELECT * FROM data_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Data>>

    //and Date between '2011/02/25' and '2011/02/27'
    @Query("SELECT * FROM data_table WHERE states  = :stateFind   and date between :date1 and :date2 ")
    fun  readByState(stateFind: Int,date1:Int, date2:Int): LiveData<List<Data>>




}