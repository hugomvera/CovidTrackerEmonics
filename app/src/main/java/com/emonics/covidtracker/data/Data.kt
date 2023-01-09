package com.emonics.covidtracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "data_table")
data class Data(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name ="date") val date: Int?,
    @ColumnInfo(name ="dateChecked") val dateChecked: String,
    @ColumnInfo(name ="death") val death: Int?,
    @ColumnInfo(name ="deathIncrease") val deathIncrease: Int?,
   // val hash: String?,
    @ColumnInfo(name ="hospitalized")  val hospitalized: Int?,
    @ColumnInfo(name ="hospitalizedCumulative") val hospitalizedCumulative: Int?,
    @ColumnInfo(name ="hospitalizedCurrently")     val hospitalizedCurrently: Int,
    @ColumnInfo(name ="hospitalizedIncrease")      val hospitalizedIncrease: Int,
    @ColumnInfo(name ="inIcuCumulative")    val inIcuCumulative: Int,
    @ColumnInfo(name ="inIcuCurrently")    val inIcuCurrently: Int,
//    val lastModified: String,
    @ColumnInfo(name ="negative") val negative: Int,
    @ColumnInfo(name ="negativeIncrease")    val negativeIncrease: Int,
    @ColumnInfo(name ="onVentilatorCumulative")     val onVentilatorCumulative: Int,
    @ColumnInfo(name ="onVentilatorCurrently")      val onVentilatorCurrently: Int,
    @ColumnInfo(name ="pending") val pending: Int,
//    val posNeg: Int,
    @ColumnInfo(name ="positive")    val positive: Int?,
    @ColumnInfo(name ="positiveIncrease")     val positiveIncrease: Int,
//    val recovered: Any,
    @ColumnInfo(name ="states")    val states: Int?,
    @ColumnInfo(name ="total")    val total: Int?,
    @ColumnInfo(name ="totalTestResults")     val totalTestResults: Int,
    @ColumnInfo(name ="totalTestResultsIncrease")  val totalTestResultsIncrease: Int?
//
)