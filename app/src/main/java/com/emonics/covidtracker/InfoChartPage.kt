package com.emonics.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.emonics.covidtracker.databinding.ActivityInfoChartPageBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class InfoChartPage : AppCompatActivity() {
    private lateinit var binding: ActivityInfoChartPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoChartPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val statesList = resources.getStringArray(R.array.States)
        val chartList = resources.getStringArray(R.array.Charts)
        val dataTypeList = resources.getStringArray(R.array.DataType)

//      Log.d("Emonics", statesList.toString())

        val dateList = listOf("2020-03-15", "2020-03-16", "2020-03-17")

        // Declare and initialize adaptors
        val adapterState = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statesList)
        val adapterChart = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, chartList)
        val adapterDataType = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dataTypeList)
        //val adapterDate = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dateList)

        /*
        val spState = findViewById<Spinner>(R.id.spState)

            if (spState != null){
                val adapterState = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, statesList)
                spState.adapter = adapterState
            }
        */


        binding.spState.adapter = adapterState
        binding.spChartType.adapter = adapterChart
        binding.spDataType.adapter = adapterDataType
        //binding.spDate.adapter = adapterDate
        binding.btnDateSelector.setOnClickListener {
            showDateRangePicker()
        }





        /*TODO:
            On item selected from slider, send value into a dynamic sql query in the room database,
            in order to retrieve database object containing filtered data.

        binding.spState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        */

    }

    private fun showDateRangePicker(){
        val dateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Select Date")
            .build()

        dateRangePicker.show(supportFragmentManager,"date_range_picker")

        dateRangePicker.addOnPositiveButtonClickListener {
            datesPicked ->
            val startDate = datesPicked.first
            val endDate = datesPicked.second
            Log.d("Emonics:", startDate.toString())
            Log.d("Emonics:", endDate.toString())

           if(startDate != null && endDate != null) {
               binding.tvDateRange.text = "Year-Month-Day\n"+ "Start Date:\t" + convertLongToDate(startDate) +
                       "\nEnd Date:\t" + convertLongToDate(endDate)
           }
        }

    }
    private fun convertLongToDate(time: Long) : String{
        val date = Date(time)
        val formattedDate = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )
        return formattedDate.format(date)
    }

}