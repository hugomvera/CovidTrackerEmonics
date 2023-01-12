package com.emonics.covidtracker.charts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val lineEntry1 = ArrayList<Entry>()
        lineEntry1.add(Entry(0.0f, 1))
        lineEntry1.add(Entry(1.0f, 2))
        lineEntry1.add(Entry(2.0f, 3))
        lineEntry1.add(Entry(30.1f, 4))
        lineEntry1.add(Entry(40.0f, 5))
        lineEntry1.add(Entry(53.0f, 6))
        lineEntry1.add(Entry(40.0f, 7))
        lineEntry1.add(Entry(62.0f, 8))
        lineEntry1.add(Entry(50.0f, 9))
        lineEntry1.add(Entry(66.1f, 10))
        lineEntry1.add(Entry(70.0f, 11))
        lineEntry1.add(Entry(100.0f, 12))

        val lineEntry2 = ArrayList<Entry>()
        lineEntry2.add(Entry(23.0f, 1))
        lineEntry2.add(Entry(10.0f, 2))
        lineEntry2.add(Entry(46.0f, 3))
        lineEntry2.add(Entry(67.1f, 4))
        lineEntry2.add(Entry(52.0f, 5))
        lineEntry2.add(Entry(23.0f, 6))
        lineEntry2.add(Entry(43.0f, 7))
        lineEntry2.add(Entry(67.0f, 8))
        lineEntry2.add(Entry(80.0f, 9))
        lineEntry2.add(Entry(100.1f, 10))
        lineEntry2.add(Entry(90.0f, 11))
        lineEntry2.add(Entry(100.0f, 12))

        val lineDataSet1 = LineDataSet(lineEntry1, "Positive")
        lineDataSet1.color = resources.getColor(R.color.purple_500)

        val lineDataSet2 = LineDataSet(lineEntry2, "Negative")
        lineDataSet2.color = resources.getColor(R.color.teal_200)

        val finalDataSet = ArrayList<LineDataSet>()
        finalDataSet.add(lineDataSet1)
        finalDataSet.add(lineDataSet2)

        val data = LineData(xvalues, finalDataSet as List<ILineDataSet>?)

        binding.lineChart.data = data
        binding.lineChart.setBackgroundColor(resources.getColor(R.color.white))
        binding.lineChart.animateXY(3000,3000)
    }

    // TODO: Use bundle/intent in order to retrieve DataViewModel object information.
    // Reminder: Use hardcoded dummy data when implementing charts for the first time inside the
    // fragment classes' logic.

}