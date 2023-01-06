package com.emonics.covidtracker



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.emonics.covidtracker.data.Data
import com.emonics.covidtracker.data.DataDatabase
import com.emonics.covidtracker.data.DataViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mDataViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val text_view = findViewById<TextView>(R.id.text_view);


        mDataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)


        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)
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


//                    val db = Room.databaseBuilder(
//                        applicationContext,
//                        DataDatabase::class.java, "database-name"
//                    ).build()


//                    val dataDao = db.dataDao()

                    val dataItem = dataList.next()
                    val resultData = " " + "Date : ${dataItem.date}" + "\n" +
                            " " + "Death : ${dataItem.death}" + "\n" +
                            " " + "hospitilized : ${dataItem.hospitalized}" + "\n\n\n"

                    Log.d("deaths", resultData)
                    // text_view.setText(resultData)

                    val data = com.emonics.covidtracker.data.Data(
                        counter,
                        dataItem.date.toString(), dataItem.dateChecked, dataItem.death
                    );

                    mDataViewModel.addData(data)


                    val dataOut = mDataViewModel.readAllData.value

                    println()
                    // Log.d("output", counter.toString())


//                    val users: LiveData<List<Data>> = dataDao.readAllData()


//                    Log.d("output", users.value?.listIterator().toString())


                }
            }


        }



        mDataViewModel.readAllData.observe(this@MainActivity) { datas ->

            // txt1.text=words[1].word
            println(datas.toString())
            Log.d("readOut", datas.size.toString())
            Log.d("readOut",datas.toString())


            var strOut = ""

            for(data in datas){
                Log.d("readOutTable",data.id.toString())
                Log.d("readOutTable",data.dateChecked.toString())
                Log.d("readOutTable",data.death.toString())

                strOut =  strOut+ data.id.toString() + " " + data.dateChecked.toString()+ data.death.toString() + "\n"

            }

            val textView: TextView = findViewById(R.id.textView) as TextView

            textView.setText(strOut)





        }
    }
}