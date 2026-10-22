package com.org.lucidfusion.fragment

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.org.lucidfusion.AppConstants
import com.org.lucidfusion.MainActivity
import com.org.lucidfusion.R
import com.org.lucidfusion.Utils
import com.org.lucidfusion.databinding.FragmentPastReportBinding
import com.org.lucidfusion.dtos.ReportDTO
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class FragmentPastReport : Fragment(), View.OnClickListener {

    private var _binding: FragmentPastReportBinding? = null
    private val binding get() = _binding!!

    private var edTempDate: EditText? = null
    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val sdf2 = SimpleDateFormat("ddMMyy", Locale.getDefault())
    private val pastReports = ArrayList<ReportDTO>()
    private var title = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPastReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setBackButtonVisibility(View.VISIBLE)
        val isHindi = Utils.getStringSetting(requireContext(), Utils.PREF_LANGAUGE) == "hindi"
        title = if (isHindi) getString(R.string.hindi_lbl_past_report) else getString(R.string.lbl_past_report)
        (activity as? MainActivity)?.setTitle(title)

        binding.btnFrom.setOnClickListener(this)
        binding.btnTo.setOnClickListener(this)
        binding.btnSearch.setOnClickListener(this)

        val calendar = Calendar.getInstance()
        binding.edToDate.setText(sdf.format(calendar.time))
        calendar.add(Calendar.DATE, -7)
        binding.edFromDate.setText(sdf.format(calendar.time))

        binding.btnSearch.text = if (isHindi) getString(R.string.hindi_lbl_search) else getString(R.string.lbl_search)
    }

    override fun onClick(v: View) {
        val calendar = Calendar.getInstance()
        when (v.id) {
            R.id.btn_from -> {
                edTempDate = binding.edFromDate
                val from = binding.edFromDate.text.toString().trim()
                if (from.isNotEmpty()) {
                    try { sdf.parse(from)?.let { calendar.time = it } } catch (e: ParseException) { e.printStackTrace() }
                }
                showDatePicker(calendar)
            }
            R.id.btn_to -> {
                edTempDate = binding.edToDate
                val to = binding.edToDate.text.toString().trim()
                if (to.isNotEmpty()) {
                    try { sdf.parse(to)?.let { calendar.time = it } } catch (e: ParseException) { e.printStackTrace() }
                }
                showDatePicker(calendar)
            }
            R.id.btn_search -> {
                val fromDate = binding.edFromDate.text.toString().trim()
                val toDate = binding.edToDate.text.toString().trim()
                val fromCal = Calendar.getInstance()
                val toCal = Calendar.getInstance()

                if (fromDate.isEmpty() || toDate.isEmpty()) {
                    Toast.makeText(requireContext(), "Please select valid From Date and To Date", Toast.LENGTH_LONG).show()
                    return
                }

                try {
                    sdf.parse(fromDate)?.let { fromCal.time = it }
                    sdf.parse(toDate)?.let { toCal.time = it }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                if (fromCal.after(toCal)) {
                    Toast.makeText(requireContext(), "From Date must be less than To Date", Toast.LENGTH_LONG).show()
                    return
                } else if (TimeUnit.MILLISECONDS.toDays(toCal.timeInMillis - fromCal.timeInMillis) > 7) {
                    Toast.makeText(requireContext(), "Cannot search for more than 7 days.", Toast.LENGTH_LONG).show()
                    return
                }

                fetchPastReports()
            }
        }
    }

    private fun showDatePicker(calendar: Calendar) {
        try {
            val dialog = DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    edTempDate?.setText("$dayOfMonth/${monthOfYear + 1}/$year")
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dialog.datePicker.maxDate = Date().time
            dialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private inner class PastDataListAdapter : BaseAdapter() {
        private val sdf1 = SimpleDateFormat("ddMMyyyy,hhmmss", Locale.getDefault())
        private val sdf2 = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())

        override fun getItemId(position: Int): Long = position.toLong()
        override fun getCount(): Int = pastReports.size
        override fun getItem(position: Int): JSONObject? = pastReports.getOrNull(position)?.dataObject

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_past_report_list_row, parent, false)

            val tvModel = view.findViewById<TextView>(R.id.tv_model)
            val tvWeldNo = view.findViewById<TextView>(R.id.tv_welder_no)
            val tvDateTime = view.findViewById<TextView>(R.id.tv_date_time)

            try {
                val obj = getItem(position)
                if (obj != null) {
                    tvModel.text = obj.optString(AppConstants.KEY_MACHINE_ID)
                    tvWeldNo.text = obj.optString(AppConstants.KEY_WELDNO)

                    val dateTimeStr = "${obj.optString(AppConstants.KEY_DATE)}${obj.optString(AppConstants.KEY_MONTH)}${obj.optString(AppConstants.KEY_YEAR)},${obj.optString(AppConstants.KEY_HOUR)}${obj.optString(AppConstants.KEY_MIN)}${obj.optString(AppConstants.KEY_SEC)}"
                    try {
                        sdf1.parse(dateTimeStr)?.let {
                            tvDateTime.text = sdf2.format(it)
                        }
                    } catch (e: Exception) {
                        tvDateTime.text = dateTimeStr
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            view.setOnClickListener {
                val fragmentNewReport = FragmentNewReport()
                val bundle = Bundle()
                val report = pastReports.getOrNull(position)
                val dbReport = (activity as? MainActivity)?.dbQueries?.getReport(report?.fn ?: "")
                bundle.putParcelable("reportDTO", dbReport ?: report)
                bundle.putString("title", title)
                fragmentNewReport.arguments = bundle
                (activity as? MainActivity)?.replaceFragment(fragmentNewReport, true, null, true)
            }

            return view
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.setShareButtonVisibility(View.INVISIBLE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchPastReports() {
        var fromDateFormatted = ""
        var toDateFormatted = ""
        try {
            fromDateFormatted = sdf2.format(sdf.parse(binding.edFromDate.text.toString().trim())!!)
            toDateFormatted = sdf2.format(sdf.parse(binding.edToDate.text.toString().trim())!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val progressDialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setMessage("Fetching past reports...")
            .setCancelable(false)
            .create()
        progressDialog.show()

        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val date = "From${fromDateFormatted}To${toDateFormatted}"
                    val request_url = Utils.REQUEST_URL + date
                    val url = URL(request_url)
                    val urlConnection: HttpURLConnection
                    var boundNetwork: android.net.Network? = null
                    if (activity is MainActivity) {
                        boundNetwork = (activity as MainActivity).getBoundNetwork()
                    }
                    if (boundNetwork != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        urlConnection = boundNetwork.openConnection(url) as HttpURLConnection
                    } else {
                        urlConnection = url.openConnection() as HttpURLConnection
                    }
                    urlConnection.connectTimeout = 10000
                    urlConnection.readTimeout = 22000
                    urlConnection.requestMethod = "GET"

                    val responseCode = urlConnection.responseCode
                    val inputStream = if (responseCode == 200) urlConnection.inputStream else urlConnection.errorStream
                    val br = BufferedReader(InputStreamReader(inputStream))
                    val stringBuffer = StringBuffer()
                    var line: String?
                    while (br.readLine().also { line = it } != null) {
                        stringBuffer.append(line)
                    }
                    urlConnection.disconnect()

                    if (responseCode == 200) {
                        val responseText = stringBuffer.toString().trim()
                        var responseArray: JSONArray? = null
                        if (responseText.startsWith("[")) {
                            responseArray = JSONArray(responseText)
                        } else if (responseText.startsWith("{")) {
                            val rootObj = JSONObject(responseText)
                            responseArray = JSONArray()
                            val keys = rootObj.keys()
                            while (keys.hasNext()) {
                                val k = keys.next()
                                val item = rootObj.optJSONObject(k)
                                if (item != null) {
                                    if (!item.has("FN")) {
                                        item.put("FN", k)
                                    }
                                    responseArray.put(item)
                                }
                            }
                        }
                        if (responseArray != null && responseArray.length() > 0) {
                            pastReports.clear()
                            for (i in 0 until responseArray.length()) {
                                val reportDTO = ReportDTO()
                                val innerResponseObject = responseArray.getJSONObject(i)
                                if (innerResponseObject.has("FN")) {
                                    reportDTO.fn = innerResponseObject.optString("FN")
                                } else {
                                    reportDTO.fn = "FN_$i"
                                }
                                val keys = innerResponseObject.keys()
                                val dataObject = JSONObject()
                                while (keys.hasNext()) {
                                    val key = keys.next()
                                    var value = "N/A"
                                    val strVal = innerResponseObject.optString(key, "")
                                    if (strVal.isNotEmpty() && strVal != "null") {
                                        value = strVal
                                    }
                                    val mappedKey = AppConstants.KEYS[key] ?: key
                                    if (mappedKey != null) {
                                        dataObject.put(mappedKey, value)
                                    }
                                }
                                reportDTO.dataObject = dataObject
                                reportDTO.isUploaded = "0"
                                pastReports.add(reportDTO)
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.e("LUCID FUSION", "Error fetching past reports", e)
                }

                if (pastReports.isEmpty() && activity is MainActivity) {
                    try {
                        val dbReports = (activity as MainActivity).dbQueries?.getReports()
                        if (!dbReports.isNullOrEmpty()) {
                            pastReports.addAll(dbReports)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            if (progressDialog.isShowing) {
                progressDialog.dismiss()
            }
            if (pastReports.size > 0) {
                binding.lvData.adapter = PastDataListAdapter()
                binding.tvNoRecords.visibility = View.GONE
            } else {
                binding.tvNoRecords.visibility = View.VISIBLE
            }
        }
    }
}
