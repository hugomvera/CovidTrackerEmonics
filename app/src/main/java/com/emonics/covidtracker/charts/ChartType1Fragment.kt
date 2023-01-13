package com.emonics.covidtracker.charts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emonics.covidtracker.R
import com.emonics.covidtracker.databinding.ActivityInfoChartPageBinding
import com.emonics.covidtracker.databinding.FragmentChartType1Binding
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class ChartType1Fragment : Fragment(R.layout.fragment_chart_type1) {

    lateinit var binding: FragmentChartType1Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentChartType1Binding.bind(view)

        setLineChartValues()

    }

    fun setLineChartValues() {


        var inputData = arguments?.getIntegerArrayList("arg1")
        var toast0 = Toast.makeText(context, "the arg1 is " + inputData.toString() , Toast.LENGTH_LONG)
        toast0.show()


        val inputData2 = arguments?.getIntegerArrayList("arg2")
        var toast1 = Toast.makeText(context, "the arg2 is " + inputData.toString() , Toast.LENGTH_LONG)
        toast1.show()

//        if (inputData != null) {
//            inputData.reverse()
//        }
//
//
//        if (inputData2 != null) {
//            inputData2.reverse()
//        }



        val xvalues = ArrayList<String>()
        if (inputData != null) {
            for(input1 in inputData.reversed()){
                xvalues.add(input1.toString())
            }
        }


        val lineEntry1 = ArrayList<Entry>()
        if (inputData2 != null) {
            var counter = 1;
            for(input1 in inputData2.reversed()){
                lineEntry1.add(BarEntry(input1.toFloat(), counter))
                counter++
            }
        }

//        val lineEntry2 = ArrayList<Entry>()
//        lineEntry2.add(Entry(23.0f, 1))
//        lineEntry2.add(Entry(10.0f, 2))
//        lineEntry2.add(Entry(46.0f, 3))
//        lineEntry2.add(Entry(67.1f, 4))
//        lineEntry2.add(Entry(52.0f, 5))
//        lineEntry2.add(Entry(23.0f, 6))
//        lineEntry2.add(Entry(43.0f, 7))
//        lineEntry2.add(Entry(67.0f, 8))
//        lineEntry2.add(Entry(80.0f, 9))
//        lineEntry2.add(Entry(100.1f, 10))
//        lineEntry2.add(Entry(90.0f, 11))
//        lineEntry2.add(Entry(100.0f, 12))

        val lineDataSet1 = LineDataSet(lineEntry1, "Positive")
        lineDataSet1.color = resources.getColor(R.color.purple_500)

//        val lineDataSet2 = LineDataSet(lineEntry2, "Negative")
//        lineDataSet2.color = resources.getColor(R.color.teal_200)

        val finalDataSet = ArrayList<LineDataSet>()
        finalDataSet.add(lineDataSet1)
 //       finalDataSet.add(lineDataSet2)

        val data = LineData(xvalues, finalDataSet as List<ILineDataSet>?)

        binding.lineChart.data = data
        binding.lineChart.setBackgroundColor(resources.getColor(R.color.white))
        binding.lineChart.animateXY(3000,3000)
    }

    // TODO: Use bundle/intent in order to retrieve DataViewModel object information.
    // Reminder: Use hardcoded dummy data when implementing charts for the first time inside the
    // fragment classes' logic.

}