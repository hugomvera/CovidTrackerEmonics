package com.emonics.covidtracker.charts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emonics.covidtracker.R
import com.emonics.covidtracker.databinding.FragmentChartType2Binding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


class ChartType2Fragment : Fragment(R.layout.fragment_chart_type2) {

    lateinit var binding: FragmentChartType2Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentChartType2Binding.bind(view)

        setBarChartValues()

    }

    fun setBarChartValues(){
        // x axis hardcoded values of chart
        val xvalues = ArrayList<String>()
            xvalues.add("Jan")
            xvalues.add("Feb")
            xvalues.add("Mar")
            xvalues.add("Apr")
            xvalues.add("May")
            xvalues.add("Jun")
            xvalues.add("Jul")
            xvalues.add("Aug")
            xvalues.add("Sep")
            xvalues.add("Oct")
            xvalues.add("Nov")
            xvalues.add("Dec")


        // y axis hardcoded values of chart
        val barEntries:ArrayList<BarEntry> = ArrayList()
        barEntries.add(BarEntry(0.0f, 1))
        barEntries.add(BarEntry(1.0f, 2))
        barEntries.add(BarEntry(2.0f, 3))
        barEntries.add(BarEntry(30.1f, 4))
        barEntries.add(BarEntry(4.0f, 5))
        barEntries.add(BarEntry(5.0f, 6))
        barEntries.add(BarEntry(6.0f, 7))
        barEntries.add(BarEntry(7.0f, 8))
        barEntries.add(BarEntry(8.0f, 9))
        barEntries.add(BarEntry(9.0f, 10))
        barEntries.add(BarEntry(10.0f, 11))
        barEntries.add(BarEntry(11.0f, 12))

        // Bar Data Set
        val barDataset = BarDataSet(barEntries, "Positive Cases")
        barDataset.color = resources.getColor(R.color.purple_500)

        // Make Bar Data
        val data = BarData(xvalues,barDataset)

        //binding.barChart.setFitBars(true)
        binding.barChart.data = data
        binding.barChart.setBackgroundColor(resources.getColor(R.color.white))
        binding.barChart.animateXY(3000, 3000)



    }

}