package com.emonics.covidtracker



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.emonics.covidtracker.data.DataViewModel
import com.emonics.covidtracker.retrofit.DataApi
import com.emonics.covidtracker.retrofit.RetrofitHelper
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


                   // val dataOut = mDataViewModel.readAllData.value

                    //println()
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

                strOut =     strOut+
                                    "   id : " + data.id.toString() +
                                  "  dateChecked: " + data.dateChecked.toString()+
                                    "  death: " + data.death.toString() + "\n"

            }

            val textView: TextView = findViewById(R.id.textView) as TextView

            textView.setText(strOut)




         //  Log.d("queryResult",mDataViewModel.readByState(56).observe())


            mDataViewModel.readByState(56).observe(this@MainActivity) { datas ->

                // txt1.text=words[1].word
                println(datas.toString())

                for(data in datas){
                    Log.d("readOutQuery",data.id.toString())
                    Log.d("readOutQuery",data.dateChecked.toString())
                    Log.d("readOutQuery",data.death.toString())
                    Log.d("readOutQuery","state="+data.states.toString())
                }

        }}
    }
}