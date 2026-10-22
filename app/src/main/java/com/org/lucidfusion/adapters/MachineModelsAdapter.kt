package com.org.lucidfusion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.org.lucidfusion.R
import com.org.lucidfusion.dtos.MachineModel

class MachineModelsAdapter(
    val machineModels: List<MachineModel>
) : BaseAdapter() {

    override fun getCount(): Int = machineModels.size

    override fun getItem(position: Int): MachineModel = machineModels[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.spinner_item_row, parent, false)

        val item = getItem(position)
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        tvName.text = item.machine_name ?: ""

        return view
    }
}
