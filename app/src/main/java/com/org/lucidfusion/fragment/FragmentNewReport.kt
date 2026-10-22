package com.org.lucidfusion.fragment

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.org.lucidfusion.AppConstants
import com.org.lucidfusion.MainActivity
import com.org.lucidfusion.R
import com.org.lucidfusion.Utils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import com.org.lucidfusion.data.api.NetworkResult
import com.org.lucidfusion.databinding.FragmentNewReportBinding
import com.org.lucidfusion.dbhelper.DBConstant
import com.org.lucidfusion.domain.usecase.SaveReportUseCase
import com.org.lucidfusion.domain.usecase.UploadReportUseCase
import com.org.lucidfusion.dtos.ReportDTO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class FragmentNewReport : Fragment() {

    private var _binding: FragmentNewReportBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var saveReportUseCase: SaveReportUseCase
    @Inject lateinit var uploadReportUseCase: UploadReportUseCase

    private var isEditMode = false
    private var reportDTO = ReportDTO()
    private var strSaveOffline = ""
    private var strSendToServer = ""
    private var strMinSec = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle == null) {
            isEditMode = false
            (activity as? MainActivity)?.setShareButtonVisibility(View.INVISIBLE)
            fetchMachineData()
        } else {
            (activity as? MainActivity)?.setShareButtonVisibility(View.VISIBLE)
        }

        val isHindi = Utils.getStringSetting(requireContext(), Utils.PREF_LANGAUGE) == "hindi"
        if (isHindi) {
            strSaveOffline = getString(R.string.hindi_lbl_save_offline)
            strSendToServer = getString(R.string.hindi_lbl_send_server)
            (activity as? MainActivity)?.setTitle(getString(R.string.hindi_lbl_new_report))
        } else {
            strSaveOffline = getString(R.string.lbl_save_offline)
            strSendToServer = getString(R.string.lbl_send_server)
            (activity as? MainActivity)?.setTitle(getString(R.string.lbl_new_report))
        }

        (activity as? MainActivity)?.setBackButtonVisibility(View.VISIBLE)
        setLanguageLabels(isHindi)

        if (bundle != null) {
            if (bundle.containsKey("title")) {
                (activity as? MainActivity)?.setTitle(bundle.getString("title") ?: "")
            }
            bundle.getParcelable<ReportDTO>("reportDTO")?.let {
                reportDTO = it
            }
            if (bundle.containsKey("mode")) {
                isEditMode = true
                binding.btnSave.text = strSendToServer
            } else {
                isEditMode = false
                binding.btnSave.text = strSaveOffline
            }
            populateData()
        }

        binding.btnSave.setOnClickListener {
            handleSaveClick()
        }

        (activity as? MainActivity)?.iv_share?.setOnClickListener {
            shareReportData()
        }
    }

    private fun handleSaveClick() {
        try {
            (activity as? MainActivity)?.closeKeyboard(binding.edCompanyName)

            if (reportDTO.dataObject == null) {
                reportDTO.dataObject = JSONObject()
            }
            val dataObj = reportDTO.dataObject!!

            if (reportDTO.fn.isBlank()) {
                val weldNo = dataObj.optString(AppConstants.KEY_WELDNO, "N/A")
                reportDTO.fn = if (weldNo != "N/A" && weldNo.isNotBlank()) weldNo else "FN_${System.currentTimeMillis()}"
            }

            dataObj.put(AppConstants.KEY_COMPANY_NAME, binding.edCompanyName.text.toString().trim())
            dataObj.put(AppConstants.KEY_DATE_TIME, binding.edDateTime.text.toString().trim())
            dataObj.put(AppConstants.KEY_WELDER_NAME, binding.edWelderName.text.toString().trim())
            dataObj.put(AppConstants.KEY_PROJECT_NAME, binding.edProjectName.text.toString().trim())
            dataObj.put(AppConstants.KEY_SITE_PLACE, binding.edSitePlace.text.toString().trim())

            if (!dataObj.has(AppConstants.KEY_PIPE_LENGTH) || dataObj.optString(AppConstants.KEY_PIPE_LENGTH).isEmpty()) {
                dataObj.put(AppConstants.KEY_PIPE_LENGTH, "N/A")
            }
            if (!dataObj.has(AppConstants.KEY_MATERIAL_GRADE) || dataObj.optString(AppConstants.KEY_MATERIAL_GRADE).isEmpty()) {
                dataObj.put(AppConstants.KEY_MATERIAL_GRADE, "N/A")
            }
            dataObj.put(AppConstants.KEY_COMMENT, binding.edComments.text.toString().trim())

            lifecycleScope.launch {
                val dbQueries = (activity as? MainActivity)?.dbQueries
                if (isEditMode) {
                    val plc = dataObj.optString(AppConstants.KEY_MACHINE_ID, "")
                    val result = uploadReportUseCase.execute(plc, reportDTO.fn, dataObj)
                    when (result) {
                        is NetworkResult.Success -> {
                            dbQueries?.update(DBConstant.TBL_REPORT, android.content.ContentValues().apply {
                                put(DBConstant._FILENAME, reportDTO.fn)
                                put(DBConstant._REPORT_DATA, dataObj.toString())
                                put(DBConstant._IS_UPLOADED, "1")
                            }, "${DBConstant._FILENAME}=?", arrayOf(reportDTO.fn))
                            showAlertDialog("Report uploaded successfully")
                        }
                        is NetworkResult.Error -> {
                            showAlertDialog("Upload failed: ${result.message}")
                        }
                        else -> {}
                    }
                } else {
                    val exists = dbQueries?.isReportExist(reportDTO.fn) == true
                    if (!exists) {
                        saveReportUseCase.execute(reportDTO.fn, dataObj)
                        showAlertDialog("Report added successfully")
                    } else {
                        AlertDialog.Builder(requireContext())
                            .setTitle("Alert!")
                            .setMessage("Report already exists, do you want to update it?")
                            .setNegativeButton("Cancel", null)
                            .setPositiveButton("Update") { _, _ ->
                                lifecycleScope.launch {
                                    saveReportUseCase.execute(reportDTO.fn, dataObj)
                                    showAlertDialog("Report updated successfully")
                                }
                            }
                            .show()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun shareReportData() {
        try {
            val dataObj = reportDTO.dataObject ?: JSONObject()
            dataObj.put(AppConstants.KEY_COMPANY_NAME, binding.edCompanyName.text.toString().trim())
            dataObj.put(AppConstants.KEY_DATE_TIME, binding.edDateTime.text.toString().trim())
            dataObj.put(AppConstants.KEY_WELDER_NAME, binding.edWelderName.text.toString().trim())
            dataObj.put(AppConstants.KEY_PROJECT_NAME, binding.edProjectName.text.toString().trim())
            dataObj.put(AppConstants.KEY_SITE_PLACE, binding.edSitePlace.text.toString().trim())
            dataObj.put(AppConstants.KEY_COMMENT, binding.edComments.text.toString().trim())
            Utils.shareReport(requireContext(), dataObj)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setLanguageLabels(isHindi: Boolean) {
        if (isHindi) {
            binding.tvMachineid.text = getString(R.string.lbl_hindi_machine_id)
            binding.tvModel.text = getString(R.string.hindi_lbl_model)
            binding.tvWeldNo.text = getString(R.string.hindi_lbl_weld_no)
            binding.tvCompanyName.text = getString(R.string.hindi_lbl_company_name)
            binding.tvDateTime.text = getString(R.string.hindi_lbl_datetime)
            binding.tvWelderName.text = getString(R.string.hindi_lbl_weldername)
            binding.tvProjectName.text = getString(R.string.hindi_lbl_projectname)
            binding.tvSitePlace.text = getString(R.string.hindi_lbl_siteplace)
            binding.tvPipeParameter.text = getString(R.string.hindi_lbl_pipe_parameter)
            binding.tvPipeDiameter.text = getString(R.string.hindi_lbl_pipe_diameter)
            binding.tvMaterialGrade.text = getString(R.string.hindi_lbl_material_grade)
            binding.tvPipePressureRating.text = getString(R.string.hindi_lbl_pipe_pressure_rating)
            binding.tvWallThickness.text = getString(R.string.hindi_lbl_wall_thickness)
            binding.tvSdr.text = getString(R.string.hindi_lbl_sdr)
            binding.tvPipeLength.text = getString(R.string.hindi_lbl_pipe_length)
            binding.tvWeldingData.text = getString(R.string.hindi_lbl_welding_data)
            binding.tvDragPressure.text = getString(R.string.hindi_lbl_drag_presr)
            binding.tvJointPressure.text = getString(R.string.hindi_lbl_joint_pressure)
            binding.tvPressureAtJoint.text = getString(R.string.hindi_lbl_pressure_at_joint)
            binding.tvHotPlateTemp.text = getString(R.string.hindi_lbl_hot_plate_temp)
            binding.tvMirrorTime.text = getString(R.string.hindi_lbl_mirror_time)
            binding.tvBeadUpTime.text = getString(R.string.hindi_lbl_bead_up_time)
            binding.tvSoakingTime.text = getString(R.string.hindi_lbl_soaking_time)
            binding.tvChangeoverTime.text = getString(R.string.hindi_lbl_changeover_time)
            binding.tvCoolingTime.text = getString(R.string.hindi_lbl_coling_time)
            binding.tvAchiPressJoint.text = getString(R.string.hindi_lbl_achived_drag_pressure)
            binding.tvAchivedDragPressure.text = getString(R.string.hindi_lbl_achived_pressure_joint)
            binding.tvComment.text = getString(R.string.hindi_lbl_comment)
            strMinSec = "${getString(R.string.lbl_hindi_min)} ${getString(R.string.lbl_hindi_sec)}"
        } else {
            binding.tvMachineid.text = getString(R.string.lbl_machine_id)
            binding.tvModel.text = getString(R.string.lbl_model)
            binding.tvWeldNo.text = getString(R.string.lbl_weld_no)
            binding.tvCompanyName.text = getString(R.string.lbl_company_name)
            binding.tvDateTime.text = getString(R.string.lbl_datetime)
            binding.tvWelderName.text = getString(R.string.lbl_weldername)
            binding.tvProjectName.text = getString(R.string.lbl_projectname)
            binding.tvSitePlace.text = getString(R.string.lbl_siteplace)
            binding.tvPipeParameter.text = getString(R.string.lbl_pipe_parameter)
            binding.tvPipeDiameter.text = getString(R.string.lbl_pipe_diameter)
            binding.tvMaterialGrade.text = getString(R.string.lbl_material_grade)
            binding.tvPipePressureRating.text = getString(R.string.lbl_pipe_pressure_rating)
            binding.tvWallThickness.text = getString(R.string.lbl_wall_thickness)
            binding.tvSdr.text = getString(R.string.lbl_sdr)
            binding.tvPipeLength.text = getString(R.string.lbl_pipe_length)
            binding.tvWeldingData.text = getString(R.string.lbl_welding_data)
            binding.tvDragPressure.text = getString(R.string.lbl_drag_presr)
            binding.tvJointPressure.text = getString(R.string.lbl_joint_pressure)
            binding.tvPressureAtJoint.text = getString(R.string.lbl_pressure_at_joint)
            binding.tvHotPlateTemp.text = getString(R.string.lbl_hot_plate_temp)
            binding.tvMirrorTime.text = getString(R.string.lbl_mirror_time)
            binding.tvBeadUpTime.text = getString(R.string.lbl_bead_up_time)
            binding.tvSoakingTime.text = getString(R.string.lbl_soaking_time)
            binding.tvChangeoverTime.text = getString(R.string.lbl_changeover_time)
            binding.tvCoolingTime.text = getString(R.string.lbl_coling_time)
            binding.tvAchiPressJoint.text = getString(R.string.lbl_achived_pressure_joint)
            binding.tvAchivedDragPressure.text = getString(R.string.lbl_achived_drag_pressure)
            binding.tvComment.text = getString(R.string.lbl_comment)
            strMinSec = getString(R.string.lbl_min_sec)
        }
    }

    private fun populateData() {
        try {
            val dataObj = reportDTO.dataObject ?: return

            val welderIdx = dataObj.optString(AppConstants.KEY_LOGIC_WELDER, "0").toIntOrNull() ?: 0
            val welderVal = AppConstants.LOGIC_WELDER.get(welderIdx) ?: ""
            binding.txtModel.text = "${AppConstants.KEY_LOGIC_WELDER} $welderVal"
            binding.txtMachineId.text = dataObj.optString(AppConstants.KEY_MACHINE_ID, "").trim()
            binding.txtWeldNo.text = dataObj.optString(AppConstants.KEY_WELDNO, "")

            val date = dataObj.optString(AppConstants.KEY_DATE)
            val month = dataObj.optString(AppConstants.KEY_MONTH)
            val year = dataObj.optString(AppConstants.KEY_YEAR)
            val hour = dataObj.optString(AppConstants.KEY_HOUR)
            val min = dataObj.optString(AppConstants.KEY_MIN)
            val sec = dataObj.optString(AppConstants.KEY_SEC)

            if (date.isNotEmpty() && month.isNotEmpty() && year.isNotEmpty()) {
                binding.edDateTime.setText("$date/$month/$year  $hour:$min:$sec")
            } else {
                binding.edDateTime.setText("N/A")
            }

            binding.txtPipeDiameter.text = dataObj.optString(AppConstants.KEY_PIPE_DIA, "N/A")
            try {
                val gradeIdx = dataObj.optInt(AppConstants.KEY_MATERIAL_GRADE, 0)
                binding.txtMaterialGrade.text = AppConstants.MATERIAL_GRADE.get(gradeIdx) ?: "N/A"
            } catch (e: Exception) {
                binding.txtMaterialGrade.text = "N/A"
            }

            try {
                val pressIdx = dataObj.optInt(AppConstants.KEY_PIPE_PRESSURE_RATING, 0)
                binding.txtPipePressureRating.text = AppConstants.PIPE_PRESSURE_RATING.get(pressIdx) ?: "N/A"
            } catch (e: Exception) {
                binding.txtPipePressureRating.text = "N/A"
            }

            binding.txtWallThickness.text = dataObj.optString(AppConstants.KEY_WALL_THICKNESS, "N/A")

            val kgCm = getString(R.string.lbl_kg_cm)
            binding.txtDragPressure.text = formatValue(dataObj.optString(AppConstants.KEY_SET_DRAG_PRESS), kgCm)
            binding.txtJointPressure.text = formatValue(dataObj.optString(AppConstants.KEY_SET_JOINT_PRESS), kgCm)
            binding.txtPressureAtJoint.text = formatValue(dataObj.optString(AppConstants.KEY_SET_PRESS_AT_JOINT), kgCm)
            binding.txtHotPlateTemp.text = formatValue(dataObj.optString(AppConstants.KEY_HOT_PLATE_TEMP), getString(R.string.lbl_celcius_degree))

            binding.txtBreadUpTime.text = formatValue(dataObj.optString(AppConstants.KEY_BEAD_UP_TIME), strMinSec)
            binding.txtSoakingTime.text = formatValue(dataObj.optString(AppConstants.KEY_SOAKING_TIME), strMinSec)
            binding.txtChangeoverTime.text = formatValue(dataObj.optString(AppConstants.KEY_CHANGEOVER_TIME), strMinSec)
            binding.txtCoolingTime.text = formatValue(dataObj.optString(AppConstants.KEY_COOLING_TIME), strMinSec)

            binding.txtAchiPressureJoint.text = formatValue(dataObj.optString(AppConstants.KEY_ACHI_PRESS_JOINT), kgCm)
            binding.txtAchivedDragPressure.text = formatValue(dataObj.optString(AppConstants.KEY_ACHIVED_DRAG_PRESS), kgCm)

            if (dataObj.has(AppConstants.KEY_COMPANY_NAME)) binding.edCompanyName.setText(dataObj.optString(AppConstants.KEY_COMPANY_NAME))
            if (dataObj.has(AppConstants.KEY_DATE_TIME)) binding.edDateTime.setText(dataObj.optString(AppConstants.KEY_DATE_TIME))
            if (dataObj.has(AppConstants.KEY_WELDER_NAME)) binding.edWelderName.setText(dataObj.optString(AppConstants.KEY_WELDER_NAME))
            if (dataObj.has(AppConstants.KEY_PROJECT_NAME)) binding.edProjectName.setText(dataObj.optString(AppConstants.KEY_PROJECT_NAME))
            if (dataObj.has(AppConstants.KEY_SITE_PLACE)) binding.edSitePlace.setText(dataObj.optString(AppConstants.KEY_SITE_PLACE))
            binding.txtSdr.text = dataObj.optString(AppConstants.KEY_SDR, "N/A")

            val pipeLenIdx = dataObj.optString(AppConstants.KEY_PIPE_LENGTH, "0").toIntOrNull() ?: 0
            binding.txtPipeLength.text = AppConstants.PIPE_LENGTH.get(pipeLenIdx) ?: "N/A"

            if (dataObj.has(AppConstants.KEY_COMMENT)) binding.edComments.setText(dataObj.optString(AppConstants.KEY_COMMENT))

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun formatValue(value: String, unit: String): String {
        return if (value != "N/A" && value.isNotBlank()) "$value  $unit" else value
    }

    private fun showAlertDialog(message: String) {
        try {
            AlertDialog.Builder(requireContext())
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as? MainActivity)?.setShareButtonVisibility(View.INVISIBLE)
    }

    private fun fetchMachineData() {
        val progressDialog = AlertDialog.Builder(requireContext())
            .setMessage("Getting machine data...")
            .setCancelable(false)
            .create()
        progressDialog.show()

        var fetchError: String? = null
        var fetchSuccess = false

        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val date = "lasttest"
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
                        val responseObject = JSONObject(stringBuffer.toString())
                        val dataObject = JSONObject()
                        val keys = responseObject.keys()
                        if (keys.hasNext()) {
                            val firstKey = keys.next()
                            val innerResponseObject: JSONObject
                            if (responseObject.optJSONObject(firstKey) != null) {
                                dataObject.put("FN", firstKey)
                                reportDTO.fn = firstKey
                                innerResponseObject = responseObject.getJSONObject(firstKey)
                            } else {
                                innerResponseObject = responseObject
                                dataObject.put("FN", firstKey)
                                reportDTO.fn = firstKey
                            }
                            val innerKeys = innerResponseObject.keys()
                            while (innerKeys.hasNext()) {
                                val key = innerKeys.next()
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
                            fetchSuccess = true
                        } else {
                            fetchError = "Empty JSON response from machine"
                        }
                    } else {
                        fetchError = "HTTP Error $responseCode: $stringBuffer"
                    }
                } catch (e: Exception) {
                    fetchError = "${e.javaClass.simpleName}: ${e.message}"
                    Log.e("LUCID FUSION", "Error fetching data", e)
                }
            }

            if (progressDialog.isShowing) {
                progressDialog.dismiss()
            }
            if (fetchSuccess) {
                populateData()
            } else {
                val errorMsg = fetchError ?: "Unknown connection error"
                showAlertDialog("Could not fetch machine data:\n$errorMsg")
            }
        }
    }
}
