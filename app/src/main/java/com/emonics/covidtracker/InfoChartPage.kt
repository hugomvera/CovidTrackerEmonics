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

        // Declare and initialize adaptors
        val adapterState = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statesList)
        val adapterChart = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, chartList)
        val adapterDataType = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dataTypeList)

        /*
         Use this code block as a potential reference later.
         On the chance we need to check for nulls.
         Ideally would like to populate with a default value.
        ------------------------------------------------------
        val spState = findViewById<Spinner>(R.id.spState)

            if (spState != null){
                val adapterState = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, statesList)
                spState.adapter = adapterState
            }
        -------------------------------------------------------
        */


        binding.spState.adapter = adapterState
        binding.spChartType.adapter = adapterChart
        binding.spDataType.adapter = adapterDataType
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

    // Function shows calendar and allows user to selection start and end dates.
    // *** Potential Issue:
    // Start Date selected is 1 day behind the day selected by the user.
    // End Date selected is also 1 day behind.
    // ***
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
//            Log.d("Emonics:", startDate.toString())
//            Log.d("Emonics:", endDate.toString())

           if(startDate != null && endDate != null) {
               binding.tvDateRange.text = "Year-Month-Day\n"+ "Start Date:\t" + convertLongToDate(startDate) +
                       "\nEnd Date:\t" + convertLongToDate(endDate)
           }
        }

    }
    // Converts a long into a string with a formatted value.
    //*** Potential Issue: May need to create another function that can format the date into a queryable string.
    private fun convertLongToDate(time: Long) : String{
        val date = Date(time)
        val formattedDate = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )
        return formattedDate.format(date)
    }

}