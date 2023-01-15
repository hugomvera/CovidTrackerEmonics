package com.emonics.covidtracker.charts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emonics.covidtracker.R
import com.emonics.covidtracker.databinding.FragmentChartType2Binding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet


class ChartType2Fragment : Fragment(R.layout.fragment_chart_type2) {

    lateinit var binding: FragmentChartType2Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentChartType2Binding.bind(view)

        setBarChartValues()





    }

    fun setBarChartValues(){




        var inputData = arguments?.getIntegerArrayList("arg1")



        val inputData2 = arguments?.getIntegerArrayList("arg2")

//
//        if (inputData != null) {
//            inputData.reverse()
//        }
//
//
//        if (inputData2 != null) {
//            inputData2.reverse()
//        }


        // x axis hardcoded values of chart
            val xvalues = ArrayList<String>()


        if (inputData != null) {
            for(input1 in inputData.reversed()){
                xvalues.add(input1.toString())
            }
        }



        val barEntries:ArrayList<BarEntry> = ArrayList()

        if (inputData2 != null) {
            var counter = 1;
            for(input1 in inputData2.reversed()){

                barEntries.add(BarEntry(input1.toFloat(), counter))
                counter++
            }
        }



        // y axis hardcoded values of chart

//        barEntries.add(BarEntry(0.0f, 1))
//        barEntries.add(BarEntry(1.0f, 2))
//        barEntries.add(BarEntry(2.0f, 3))
//        barEntries.add(BarEntry(30.1f, 4))
//        barEntries.add(BarEntry(4.0f, 5))
//        barEntries.add(BarEntry(5.0f, 6))
//        barEntries.add(BarEntry(6.0f, 7))
//        barEntries.add(BarEntry(7.0f, 8))
//        barEntries.add(BarEntry(8.0f, 9))
//        barEntries.add(BarEntry(9.0f, 10))
//        barEntries.add(BarEntry(10.0f, 11))
//        barEntries.add(BarEntry(11.0f, 12))

//        val barEntries2:ArrayList<BarEntry> = ArrayList()
//        barEntries2.add(BarEntry(14f, 1))
//        barEntries2.add(BarEntry(23f, 2))
//        barEntries2.add(BarEntry(21f, 3))
//        barEntries2.add(BarEntry(30.1f, 4))
//        barEntries2.add(BarEntry(42.0f, 5))
//        barEntries2.add(BarEntry(23.0f, 6))
//        barEntries2.add(BarEntry(34.0f, 7))
//        barEntries2.add(BarEntry(20.0f, 8))
//        barEntries2.add(BarEntry(50.0f, 9))
//        barEntries2.add(BarEntry(57.0f, 10))
//        barEntries2.add(BarEntry(70.0f, 11))
//        barEntries2.add(BarEntry(80.0f, 12))

        // Bar Data Set
        val barDataset = BarDataSet(barEntries, "Positive Cases")
        barDataset.color = resources.getColor(R.color.purple_500)

            //val barDataset2 = BarDataSet(barEntries2, "Negative Cases")
            //barDataset2.color = resources.getColor(R.color.teal_200)
        // Make Bar Data
        val finalBarDataSet = ArrayList<BarDataSet>()
        finalBarDataSet.add(barDataset)
            // finalBarDataSet.add(barDataset2)

        val data = BarData(xvalues, finalBarDataSet as List<IBarDataSet>?)

        //binding.barChart.setFitBars(true)
        binding.barChart.data = data
        binding.barChart.setBackgroundColor(resources.getColor(R.color.white))
        binding.barChart.animateXY(3000, 3000)







    }



}