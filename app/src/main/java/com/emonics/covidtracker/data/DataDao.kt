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



}