package com.org.lucidfusion.data.repository

import android.content.ContentValues
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import com.org.lucidfusion.AppConstants
import com.org.lucidfusion.ApplicationClass
import com.org.lucidfusion.data.api.NetworkResult
import com.org.lucidfusion.data.api.WeldReportApiService
import com.org.lucidfusion.dbhelper.DBConstant
import com.org.lucidfusion.dbhelper.DBQueries
import com.org.lucidfusion.domain.repository.ReportRepository
import com.org.lucidfusion.dtos.ReportDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dbQueries: DBQueries,
    private val apiService: WeldReportApiService
) : ReportRepository {

    override suspend fun getReports(): List<ReportDTO> = withContext(Dispatchers.IO) {
        dbQueries.getReports()
    }

    override suspend fun getReport(fn: String): ReportDTO? = withContext(Dispatchers.IO) {
        dbQueries.getReport(fn)
    }

    override suspend fun isReportExist(fn: String): Boolean = withContext(Dispatchers.IO) {
        dbQueries.isReportExist(fn)
    }

    override suspend fun insertReport(fn: String, reportData: JSONObject): Long = withContext(Dispatchers.IO) {
        val contentValues = ContentValues().apply {
            put(DBConstant._FILENAME, fn)
            put(DBConstant._REPORT_DATA, reportData.toString())
            put(DBConstant._IS_UPLOADED, "0")
        }
        dbQueries.insert(DBConstant.TBL_REPORT, contentValues)
    }

    override suspend fun markReportUploaded(fn: String): Long = withContext(Dispatchers.IO) {
        val contentValues = ContentValues().apply {
            put(DBConstant._IS_UPLOADED, "1")
        }
        dbQueries.update(DBConstant.TBL_REPORT, contentValues, "${DBConstant._FILENAME}=?", arrayOf(fn))
    }

    override suspend fun uploadReport(
        plcNumber: String,
        fn: String,
        reportData: JSONObject
    ): NetworkResult<String> = withContext(Dispatchers.IO) {
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            var internetNetwork: Network? = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && cm != null) {
                // 1. Release machine Wi-Fi process lock
                cm.bindProcessToNetwork(null)

                // 2. Locate active network with Internet capability (e.g. Cellular Data)
                cm.allNetworks.forEach { net ->
                    val caps = cm.getNetworkCapabilities(net)
                    if (caps != null && caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                        internetNetwork = net
                        return@forEach
                    }
                }
            }

            val params = LinkedHashMap<String, String>()
            val logicWelderIdx = reportData.optString(AppConstants.KEY_LOGIC_WELDER, "0").toIntOrNull() ?: 0
            val logicWelderVal = AppConstants.LOGIC_WELDER.get(logicWelderIdx) ?: ""
            params["Logic_Welder"] = "${AppConstants.KEY_LOGIC_WELDER} $logicWelderVal"
            params["Weld_No"] = reportData.optString(AppConstants.KEY_WELDNO, "")
            params["company_name"] = reportData.optString(AppConstants.KEY_COMPANY_NAME, "")
            params["date_time"] = reportData.optString(AppConstants.KEY_DATE_TIME, "")
            params["Welder_name"] = reportData.optString(AppConstants.KEY_WELDER_NAME, "")
            params["project_name"] = reportData.optString(AppConstants.KEY_PROJECT_NAME, "")
            params["site_place"] = reportData.optString(AppConstants.KEY_SITE_PLACE, "")
            params["Pipe_Dia"] = reportData.optString(AppConstants.KEY_PIPE_DIA, "")

            val matGradeIdx = reportData.optString(AppConstants.KEY_MATERIAL_GRADE, "0").toIntOrNull() ?: 0
            params["Material_Grade"] = AppConstants.MATERIAL_GRADE.get(matGradeIdx) ?: ""

            val pressRatingIdx = reportData.optString(AppConstants.KEY_PIPE_PRESSURE_RATING, "0").toIntOrNull() ?: 0
            params["Pipe_pressure_rating"] = AppConstants.PIPE_PRESSURE_RATING.get(pressRatingIdx) ?: ""

            params["Wall_thickness"] = reportData.optString(AppConstants.KEY_WALL_THICKNESS, "")
            params["sdr"] = reportData.optString(AppConstants.KEY_SDR, "")

            val pipeLengthIdx = reportData.optString(AppConstants.KEY_PIPE_LENGTH, "0").toIntOrNull() ?: 0
            params["pipe_length"] = AppConstants.PIPE_LENGTH.get(pipeLengthIdx) ?: ""

            params["Set_drag_press"] = reportData.optString(AppConstants.KEY_SET_DRAG_PRESS, "")
            params["Achived_drag_press"] = reportData.optString(AppConstants.KEY_ACHIVED_DRAG_PRESS, "")
            params["Set_joint_press"] = reportData.optString(AppConstants.KEY_SET_JOINT_PRESS, "")
            params["Set_press_joint"] = reportData.optString(AppConstants.KEY_SET_PRESS_AT_JOINT, "")
            params["Achi_press_joint"] = reportData.optString(AppConstants.KEY_ACHI_PRESS_JOINT, "")
            params["Hot_plate_temp"] = reportData.optString(AppConstants.KEY_HOT_PLATE_TEMP, "")
            params["Bead_up_time"] = reportData.optString(AppConstants.KEY_BEAD_UP_TIME, "")
            params["Soaking_time"] = reportData.optString(AppConstants.KEY_SOAKING_TIME, "")
            params["Changeover_time"] = reportData.optString(AppConstants.KEY_CHANGEOVER_TIME, "")
            params["Cooling_time"] = reportData.optString(AppConstants.KEY_COOLING_TIME, "")
            params["comment"] = reportData.optString(AppConstants.KEY_COMMENT, "")

            val stringBuilder = StringBuilder("https://weldreports.com/api.php?request=addreportdata&PLCNumber=$plcNumber&FileName=$fn")
            for ((key, value) in params) {
                stringBuilder.append("&").append(key).append("=").append(value)
            }

            val apiCallUrl = stringBuilder.toString().replace(" ", "%20")
            val requestedUrl = URL(apiCallUrl)

            // 3. Open connection via the specific Internet network if available
            val urlConnection = if (internetNetwork != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                internetNetwork.openConnection(requestedUrl) as HttpURLConnection
            } else {
                requestedUrl.openConnection() as HttpURLConnection
            }

            urlConnection.requestMethod = "POST"
            urlConnection.connectTimeout = 10000
            urlConnection.readTimeout = 10000

            val responseCode = urlConnection.responseCode
            if (responseCode == 200 || responseCode == 201) {
                val responseText = urlConnection.inputStream.bufferedReader().use { it.readText() }
                urlConnection.disconnect()
                markReportUploaded(fn)
                NetworkResult.Success(responseText, responseCode)
            } else {
                val errorText = urlConnection.errorStream?.bufferedReader()?.use { it.readText() } ?: "Server error $responseCode"
                urlConnection.disconnect()
                NetworkResult.Error(errorText, responseCode)
            }
        } catch (e: Exception) {
            NetworkResult.Error("Connection failed: ${e.localizedMessage}")
        } finally {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            val machineNetwork = ApplicationClass.boundNetwork
            if (machineNetwork != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && cm != null) {
                cm.bindProcessToNetwork(machineNetwork)
            }
        }
    }
}
