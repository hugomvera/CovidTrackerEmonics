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



        mDataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        //starting the API retrofit to get the data
        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)

        //will write to the data with this codde
        // launching a new coroutine
        GlobalScope.launch {

            var counter = 0;
            val result = dataApi.getData()
            // Checking the results
            Log.d("ayush: ", result.body().toString())
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
                        dataItem.date.toString(),
                        dataItem.dateChecked,
                        dataItem.death,
                        dataItem.hospitalized,
                        dataItem.negative,
                        dataItem.pending,
                        dataItem.positive,
                        dataItem.states,
                        dataItem.total
                    );

                    mDataViewModel.addData(data)

                }
            }


        }




    }
}