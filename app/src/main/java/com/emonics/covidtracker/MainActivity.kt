//Nibedita created this branch

package com.emonics.covidtracker

//nibedita's
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

//hugo's
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.emonics.covidtracker.data.DataViewModel
import com.emonics.covidtracker.retrofit.DataApi
import com.emonics.covidtracker.retrofit.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    //making a data view model to create the datase
    private lateinit var mDataViewModel: DataViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)


        // Created access to the database using the DataViewModel.
        mDataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        //starting the API retrofit to get the data
        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)

        //will write to the data with this code
        // launching a new coroutine
        GlobalScope.launch {

            var counter = 0;
            val result = dataApi.getData()
            // Checking the results
//          result of the api call.
            val dataList = result.body()?.listIterator()
            if (dataList != null) {
                while (dataList.hasNext()) {
                    counter++;

                    val dataItem = dataList.next()
                    val resultData = " " + "Date : ${dataItem.date}" + "\n" +
                            " " + "Death : ${dataItem.death}" + "\n" +
                            " " + "hospitilized : ${dataItem.hospitalized}" +
                            " " + "total : ${dataItem.total}" + "\n\n\n"

                    Log.d("deaths", resultData)
                    // text_view.setText(resultData)

                    val data = com.emonics.covidtracker.data.Data(
                        counter,
                        dataItem.date,
                        dataItem.dateChecked,
                        dataItem.death,
                        dataItem.deathIncrease,
                        dataItem.hospitalized,
                        dataItem.hospitalizedCumulative,
                        dataItem.hospitalizedCurrently,
                        dataItem.hospitalizedIncrease,
                        dataItem.inIcuCumulative,
                        dataItem.inIcuCurrently,
                        dataItem.negative,
                        dataItem.negativeIncrease,
                        dataItem.onVentilatorCumulative,
                        dataItem.onVentilatorCurrently,
                        dataItem.pending,
                        dataItem.positive,
                        dataItem.positiveIncrease,
                        dataItem.states,
                        dataItem.total,
                        dataItem.totalTestResults,
                        dataItem.totalTestResultsIncrease
                    );

                    mDataViewModel.addData(data)

                }
            }


        }




    }
}