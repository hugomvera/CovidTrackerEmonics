package com.emonics.covidtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.emonics.covidtracker.charts.ChartType1Fragment
import com.emonics.covidtracker.charts.ChartType2Fragment
import com.emonics.covidtracker.data.DataViewModel
import com.emonics.covidtracker.databinding.ActivityInfoChartPageBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*



// Goals: Create logic for new chart activity/fragments.

class InfoChartPage : AppCompatActivity() {

    private lateinit var binding: ActivityInfoChartPageBinding
    var stateNumber = 56;
    var date1 = 0
    var date2 = 0
    var aLDates = ArrayList<Int>()
    //arraylist where the deaths or whatever ill be saved
    var aLDeaths = ArrayList<Int>();

    var aLNegative = ArrayList<Int>();
    var aLPositive = ArrayList<Int>();



    var positionForY = 0;
    var dataForY = ArrayList<Int>()

    //string Out
    var stringTestOut = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoChartPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //state list array strings
        val statesList = resources.getStringArray(R.array.States)

        //chartList is the avaible chart types
        val chartList = resources.getStringArray(R.array.Charts)

        //dataTypeList is the dataType
        val dataTypeList = resources.getStringArray(R.array.DataType)

        // Declare and initialize adaptors
        //adapter for  states
        val adapterState = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statesList)

        //adapter for chart type
        val adapterChart = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, chartList)

        //adapter for datatype
        val adapterDataType = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dataTypeList)



        //this for the state drop down
        binding.spState.adapter = adapterState

        //this is for the chartype
        binding.spChartType.adapter = adapterChart

        //this is for the data type like positive, negative, ...
        binding.spDataType.adapter = adapterDataType
        binding.btnDateSelector.setOnClickListener {
            showDateRangePicker()
        }



        // THis NEEDs to pass a int to the query
        // the integer is going to be the number of state that was made
        binding.spState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            //this passes live what is selected on the drop down to select the state
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val text: String = adapterView?.getItemAtPosition(position).toString()

                stateNumber = position

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


        //this for the chart type
        binding.spChartType.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{

            //this passes live what is selected on the drop down to select the state
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //val text: String = adapterView?.getItemAtPosition(position).toString()

               // Toast.makeText(this@InfoChartPage,"hello on chartType is = "+text,Toast.LENGTH_LONG).show()
                when(position){
                    0 -> replaceFragment(ChartType1Fragment())
                    1 -> replaceFragment(ChartType2Fragment())
                }


            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        //this is for the data type like deaths, positive, ...
        binding.spDataType.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val text: String = adapterView?.getItemAtPosition(position).toString()

              positionForY = position

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.btnCSV.setOnClickListener {
            val intent = Intent(this, CSVmainactivity::class.java)
            //TODO pass the Result String
            intent.putExtra("arg1",stringTestOut)
            startActivity(intent)
        }
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

           if(startDate != null && endDate != null) {

              // binding.tvDateRange.text =stringTestOut
                date1 = Integer.valueOf(convertLongToDate(startDate).toString().replace("-","") )
                date2  = Integer.valueOf(convertLongToDate(endDate).toString().replace("-","") )

           }


            stringTestOut = ""
            aLDates.clear()
            aLDeaths.clear()
            aLNegative.clear()
            aLPositive.clear()

            var mDataViewModel: DataViewModel
            mDataViewModel = ViewModelProvider(this@InfoChartPage).get(DataViewModel::class.java)

            mDataViewModel.readByState(stateNumber,date1,date2).observe(this@InfoChartPage) { datas ->


                for(data in datas){
                    stringTestOut += "id: "+data.id.toString() +
                                     "date: " + data.date +
                                    "death: " + data.death.toString() +
                                     "state: " + data.states.toString() + "\n"


                    aLDates.add(data.date as Int);
                    aLDeaths.add(data.death as Int)
                    aLNegative.add(data.negative as Int)
                    aLPositive.add(data.positive as Int)


                }

                binding.tvDateRange.text = stringTestOut

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

    private fun replaceFragment(fragment : Fragment){

        val bundle = Bundle().apply {

            putIntegerArrayList("arg1",aLDates)

            if(positionForY ==0) {
                putIntegerArrayList("arg2",aLDeaths)
            }

            if(positionForY ==1) {
                putIntegerArrayList("arg2",aLNegative)
            }

            if(positionForY ==2) {
                putIntegerArrayList("arg2",aLPositive)
            }

        }

        fragment.arguments = bundle

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}