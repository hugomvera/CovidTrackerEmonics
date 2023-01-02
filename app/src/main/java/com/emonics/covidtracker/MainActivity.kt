package com.emonics.covidtracker



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // val text_view = findViewById<TextView>(R.id.text_view);

        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = dataApi.getData()
            // Checking the results
            Log.d("ayush: ", result.body().toString())
            val dataList = result.body()?.listIterator()
            if (dataList != null) {
                while (dataList.hasNext()) {
                    val dataItem = dataList.next()
                    val resultData = " " + "Date : ${dataItem.date}" + "\n" +
                            " " + "Death : ${dataItem.death}" + "\n" +
                            " " + "hospitilized : ${dataItem.hospitalized}" + "\n\n\n"

                    Log.d("deaths",resultData)
                    // text_view.setText(resultData)
                }
            }



        }

    }
}