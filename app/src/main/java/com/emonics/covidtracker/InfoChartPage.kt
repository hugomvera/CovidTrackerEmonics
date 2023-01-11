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
    private lateinit var mDataViewModel: DataViewModel
    private lateinit var binding: ActivityInfoChartPageBinding
    var stateNumber = 56;
    var date1 = 0
    var date2 = 0

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

        //this is to debug
        //      Log.d("Emonics", statesList.toString())

        // Declare and initialize adaptors
        //adapter for  states
        val adapterState = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statesList)

        //adapter for chart type
        val adapterChart = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, chartList)

        //adapter for datatype
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

                Toast.makeText(this@InfoChartPage,"the state position is = "+stateNumber,Toast.LENGTH_LONG).show()


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

            //this passes live what is selected on the drop down to select the state
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val text: String = adapterView?.getItemAtPosition(position).toString()

                //Toast.makeText(this@InfoChartPage,"hello on dataType is = "+text,Toast.LENGTH_LONG).show()


            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.btnCSV.setOnClickListener {
            val intent = Intent(this, CSVmainactivity::class.java)
            startActivity(intent)
        }
    }
    /*
    Function shows calendar and allows user to selection start and end dates.
    ------------------------------------------------------------------
    TODO: Potential Issue
    Start Date selected is 1 day behind the day selected by the user.
    End Date selected is also 1 day behind.
    ------------------------------------------------------------------
    */
    private fun showDateRangePicker(){
        val dateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Select Date")
            .build()

        dateRangePicker.show(supportFragmentManager,"date_range_picker")

        dateRangePicker.addOnPositiveButtonClickListener {
            datesPicked ->
            var stringTestOut = ""
            val startDate = datesPicked.first
            val endDate = datesPicked.second
//            Log.d("Emonics:", startDate.toString())
//            Log.d("Emonics:", endDate.toString())

           if(startDate != null && endDate != null) {

              // binding.tvDateRange.text =stringTestOut
                date1 = Integer.valueOf(convertLongToDate(startDate).toString().replace("-","") )
                date2  = Integer.valueOf(convertLongToDate(endDate).toString().replace("-","") )

               stringTestOut = "Year-Month-Day\n"+ "Start Date:\t" + date1 +
                       "\nEnd Date:\t " + date2+ "\n"
           }

            //TODO can place query here
            Toast.makeText(this,"testing out",Toast.LENGTH_LONG).show()


            mDataViewModel = ViewModelProvider(this@InfoChartPage).get(DataViewModel::class.java)

            mDataViewModel.readByState(stateNumber,date1,date2).observe(this@InfoChartPage) { datas ->

                // txt1.text=words[1].word
                println(datas.toString())

                Toast.makeText(this,"inside the readbystate observable",Toast.LENGTH_LONG).show()

                for(data in datas){
                    stringTestOut += "id: "+data.id.toString() +
                                     "date: " + data.date +
                                    "death: " + data.death.toString() +
                                     "state: " + data.states.toString() + "\n"
                    Log.d("readOutQueryFromInfoChartPage",data.id.toString())
                    Log.d("readOutQueryFromInfoChartPage",data.dateChecked.toString())
                    Log.d("readOutQueryFromInfoChartPage",data.death.toString())
                    Log.d("readOutQueryFromInfoChartPage","state="+data.states.toString())
                    Toast.makeText(this,"inside the datas loop:" + stringTestOut,Toast.LENGTH_SHORT).show()
                }

                binding.tvDateRange.text = stringTestOut

                //Toast.makeText(this,"the dates are" + convertLongToDate(startDate).toString().replace("-","") +" to "+ convertLongToDate(endDate) ,Toast.LENGTH_LONG).show()

                //Toast.makeText(this,"the start date int is" + startDate + " to " +endDate,Toast.LENGTH_LONG )
            }
            //stringTestOut += "\n"+ "the output of query is "


        }



    }
    /*
    Converts a long into a string with a formatted value.
    ---------------------------------------------------------------------------------------
    TODO: Potential Issue
    May need to create another function that can format the date into a queryable string.
    ---------------------------------------------------------------------------------------
    */
    private fun convertLongToDate(time: Long) : String{
        val date = Date(time)
        val formattedDate = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )
        return formattedDate.format(date)
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }


}

// TODO: Use the fragment container in this activity and create 3 fragments inside it. 1 for each of
//  the charts we want to use.