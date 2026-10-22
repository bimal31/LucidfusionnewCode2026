package com.org.lucidfusion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.org.lucidfusion.R
import com.org.lucidfusion.dtos.PressureSize

class PerssureRatingAdapter(
    val pressureSizes: List<PressureSize>
) : BaseAdapter() {

    override fun getCount(): Int = pressureSizes.size

    override fun getItem(position: Int): PressureSize = pressureSizes[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.spinner_item_row, parent, false)

        val item = getItem(position)
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        tvName.text = item.pressure_size_name ?: ""

        return view
    }
}
