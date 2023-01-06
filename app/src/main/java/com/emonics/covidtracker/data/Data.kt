package com.emonics.covidtracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class Data(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name ="date") val String: String?,
    @ColumnInfo(name ="dateChecked") val dateChecked: String,
    @ColumnInfo(name ="death") val death: Int?
    //val deathIncrease: Int?,
   // val hash: String?,
   // val hospitalized: Int?,
  //  val hospitalizedCumulative: Int?,
//    val hospitalizedCurrently: Int,
//    val hospitalizedIncrease: Int,
//    val inIcuCumulative: Int,
//    val inIcuCurrently: Int,
//    val lastModified: String,
//    val negative: Int,
//    val negativeIncrease: Int,
//    val onVentilatorCumulative: Int,
//    val onVentilatorCurrently: Int,
//    val pending: Int,
//    val posNeg: Int,
//    val positive: Int,
//    val positiveIncrease: Int,
//    val recovered: Any,
//    val states: Int,
//    val total: Int,
//    val totalTestResults: Int,
  //  val totalTestResultsIncrease: Int?
//
)