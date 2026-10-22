package com.org.lucidfusion.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.org.lucidfusion.AppConstants
import com.org.lucidfusion.MainActivity
import com.org.lucidfusion.R
import com.org.lucidfusion.Utils
import com.org.lucidfusion.data.api.NetworkResult
import com.org.lucidfusion.databinding.FragmentSendToServerBinding
import com.org.lucidfusion.domain.usecase.GetPastReportsUseCase
import com.org.lucidfusion.domain.usecase.UploadReportUseCase
import com.org.lucidfusion.dtos.ReportDTO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class FragmentSendToServer : Fragment() {

    private var _binding: FragmentSendToServerBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var getPastReportsUseCase: GetPastReportsUseCase
    @Inject lateinit var uploadReportUseCase: UploadReportUseCase

    private val pastReports = ArrayList<ReportDTO>()
    private var title = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSendToServerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isHindi = Utils.getStringSetting(requireContext(), Utils.PREF_LANGAUGE) == "hindi"
        title = if (isHindi) getString(R.string.hindi_lbl_send_server) else getString(R.string.lbl_send_server)
        (activity as? MainActivity)?.setTitle(title)
        (activity as? MainActivity)?.setBackButtonVisibility(View.VISIBLE)

        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            val reports = getPastReportsUseCase.execute()
            pastReports.clear()
            pastReports.addAll(reports)

            if (pastReports.isNotEmpty()) {
                binding.lvData.adapter = PastDataListAdapter()
                binding.tvNoRecords.visibility = View.GONE
            } else {
                binding.tvNoRecords.visibility = View.VISIBLE
            }
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
                .inflate(R.layout.layout_send_to_server_list_row, parent, false)

            val tvLabelWeldNo = view.findViewById<TextView>(R.id.tv_lable_weld_no)
            val tvWeldNo = view.findViewById<TextView>(R.id.tv_welder_no)
            val tvProjectName = view.findViewById<TextView>(R.id.tv_project_name)
            val tvProjectSite = view.findViewById<TextView>(R.id.tv_project_site)
            val tvDateTime = view.findViewById<TextView>(R.id.tv_date_time)
            val ivUpload = view.findViewById<ImageView>(R.id.iv_upload)
            val ivEdit = view.findViewById<ImageView>(R.id.iv_edit)
            val ivShare = view.findViewById<ImageView>(R.id.iv_share)

            val isHindi = Utils.getStringSetting(requireContext(), Utils.PREF_LANGAUGE) == "hindi"
            tvLabelWeldNo.text = if (isHindi) getString(R.string.hindi_lbl_weld_no) else getString(R.string.lbl_weld_no)

            val itemObj = getItem(position)
            if (itemObj != null) {
                tvWeldNo.text = itemObj.optString(AppConstants.KEY_WELDNO)
                val projName = itemObj.optString(AppConstants.KEY_PROJECT_NAME).trim()
                tvProjectName.text = if (projName.isNotEmpty()) projName else "N/A"

                val sitePlace = itemObj.optString(AppConstants.KEY_SITE_PLACE).trim()
                tvProjectSite.text = if (sitePlace.isNotEmpty()) sitePlace else "N/A"

                val dateTimeStr = "${itemObj.optString(AppConstants.KEY_DATE)}${itemObj.optString(AppConstants.KEY_MONTH)}${itemObj.optString(AppConstants.KEY_YEAR)},${itemObj.optString(AppConstants.KEY_HOUR)}${itemObj.optString(AppConstants.KEY_MIN)}${itemObj.optString(AppConstants.KEY_SEC)}"
                try {
                    sdf1.parse(dateTimeStr)?.let { tvDateTime.text = sdf2.format(it) }
                } catch (e: Exception) {
                    tvDateTime.text = dateTimeStr
                }

                val currentReport = pastReports[position]
                if (currentReport.isUploaded == "1") {
                    ivUpload.setImageResource(R.drawable.ic_cab_done_mtrl_alpha)
                } else {
                    ivUpload.setImageResource(R.drawable.stat_sys_upload_anim0)
                }

                ivUpload.setOnClickListener {
                    if (currentReport.isUploaded == "1") {
                        AlertDialog.Builder(requireContext())
                            .setTitle("Alert!")
                            .setMessage("Report already uploaded on the server.")
                            .setNegativeButton("Ok", null)
                            .show()
                    } else {
                        lifecycleScope.launch {
                            val plc = itemObj.optString(AppConstants.KEY_MACHINE_ID, "")
                            val res = uploadReportUseCase.execute(plc, currentReport.fn, itemObj)
                            when (res) {
                                is NetworkResult.Success -> {
                                    ivUpload.setImageResource(R.drawable.ic_cab_done_mtrl_alpha)
                                    currentReport.isUploaded = "1"
                                    AlertDialog.Builder(requireContext())
                                        .setTitle("Success")
                                        .setMessage("Report uploaded successfully!")
                                        .setPositiveButton("OK", null)
                                        .show()
                                }
                                is NetworkResult.Error -> {
                                    AlertDialog.Builder(requireContext())
                                        .setTitle("Alert!")
                                        .setMessage("Upload failed: ${res.message}")
                                        .setPositiveButton("OK", null)
                                        .show()
                                }
                                else -> {}
                            }
                        }
                    }
                }

                ivEdit.setOnClickListener {
                    val fragmentNewReport = FragmentNewReport()
                    val bundle = Bundle().apply {
                        putParcelable("reportDTO", currentReport)
                        putBoolean("mode", true)
                        putString("title", title)
                    }
                    fragmentNewReport.arguments = bundle
                    (activity as? MainActivity)?.replaceFragment(fragmentNewReport, true, null, true)
                }

                ivShare.setOnClickListener {
                    Utils.shareReport(requireContext(), itemObj)
                }
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
}
