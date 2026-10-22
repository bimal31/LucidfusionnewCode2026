package com.org.lucidfusion.dbhelper

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.org.lucidfusion.ApplicationClass
import com.org.lucidfusion.MainActivity
import com.org.lucidfusion.dtos.MachineModel
import com.org.lucidfusion.dtos.PipeGrade
import com.org.lucidfusion.dtos.PipeSize
import com.org.lucidfusion.dtos.PressureSize
import com.org.lucidfusion.dtos.ReportDTO
import org.json.JSONObject
import java.util.Collections

class DBQueries(val context: Context) : DBConstant {

    fun isReportExist(fn: String): Boolean {
        var isExist = false
        try {
            val qry = "select * from ${DBConstant.TBL_REPORT} where ${DBConstant._FILENAME} = '$fn'"
            val cursor = ApplicationClass.dbAdapter?.executeRawQuery(qry)
            if (cursor != null) {
                isExist = cursor.count > 0
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return isExist
    }

    fun getMachineModels(machine_standard: String): ArrayList<MachineModel> {
        val machineModels = ArrayList<MachineModel>()
        try {
            val qry = "select * from ${DBConstant.TBL_MACHINE} where ${DBConstant.MACHINE_STANDARD} = '$machine_standard' or ${DBConstant.MACHINE_STANDARD} = $machine_standard order by ${DBConstant._ID}"
            val cursor = ApplicationClass.dbAdapter?.executeRawQuery(qry)
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val machineModel = MachineModel()
                    machineModel.machine_id = cursor.getInt(0)
                    machineModel.machine_name = cursor.getString(1).trim()
                    machineModel.machine_desc = cursor.getString(2)
                    val nameParts = machineModel.machine_name?.split(" ")
                    machineModel.sort_value = nameParts?.getOrNull(0)?.trim()?.toIntOrNull() ?: 0
                    machineModels.add(machineModel)
                } while (cursor.moveToNext())
                cursor.close()

                if (machineModels.size >= 3) {
                    machineModels.add(1, machineModels.get(machineModels.size - 1))
                    machineModels.removeAt(machineModels.size - 1)
                    machineModels.add(2, machineModels.get(machineModels.size - 1))
                    machineModels.removeAt(machineModels.size - 1)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        machineModels.sortWith(Comparator { lhs, rhs ->
            if (lhs.sort_value > rhs.sort_value) 1 else -1
        })
        return machineModels
    }

    fun getPipeSizeForMachine(machine_id: Int, standard: String): ArrayList<PipeSize> {
        val pipeSizes = ArrayList<PipeSize>()
        try {
            val qry = "select * from ${DBConstant.TBL_PIPESIZE} where ${DBConstant._PIPESIZE_MACHINEID} = $machine_id and (${DBConstant.PIPESIZE_STANDARD} = '$standard' or ${DBConstant.PIPESIZE_STANDARD} = $standard)"
            val cursor = ApplicationClass.dbAdapter?.executeRawQuery(qry)
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val pipeSize = PipeSize()
                    pipeSize.pipe_size_id = cursor.getInt(0)
                    pipeSize.machine_id = cursor.getInt(1)
                    pipeSize.pipe_size_name = cursor.getString(2)
                    pipeSize.pipe_size_desc = cursor.getString(3)
                    pipeSizes.add(pipeSize)
                } while (cursor.moveToNext())
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return pipeSizes
    }

    fun getPipeGrades(standard: String): ArrayList<PipeGrade> {
        val pipeGrades = ArrayList<PipeGrade>()
        try {
            val qry = "select * from ${DBConstant.TBL_PIPEGRADE} where ${DBConstant.GRADE_STANDARD} = '$standard' or ${DBConstant.GRADE_STANDARD} = $standard"
            val cursor = ApplicationClass.dbAdapter?.executeRawQuery(qry)
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val pipeGrade = PipeGrade()
                    pipeGrade.p_grade_id = cursor.getInt(0)
                    pipeGrade.p_grade_name = cursor.getString(1)
                    pipeGrade.p_grade_desc = cursor.getString(2)
                    pipeGrades.add(pipeGrade)
                } while (cursor.moveToNext())
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return pipeGrades
    }

    fun getPressureSize(standard: String): ArrayList<PressureSize> {
        val pressureSizes = ArrayList<PressureSize>()
        try {
            val qry = if (standard.contains("4984")) {
                "select * from ${DBConstant.TBL_PRESSURESIZE} where Id < 8"
            } else {
                "select * from ${DBConstant.TBL_PRESSURESIZE}"
            }
            val cursor = ApplicationClass.dbAdapter?.executeRawQuery(qry)
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val pressureSize = PressureSize()
                    pressureSize.pressure_id = cursor.getInt(0)
                    pressureSize.pressure_size_name = cursor.getString(1)
                    pressureSizes.add(pressureSize)
                } while (cursor.moveToNext())
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return pressureSizes
    }

    fun getCalcData(machine_id: Int, pipe_size_id: Int, pipe_grade_id: Int, pressure_id: Int): HashMap<String, String> {
        val dataMap = HashMap<String, String>()
        try {
            val qry = "select ${DBConstant._TEMPERATURE}, ${DBConstant._JOINTINGPRESSURE}, ${DBConstant._AVERAGETHICKNESS} from ${DBConstant.TBL_CALCDATA} where ${DBConstant._MACHINE_ID} = $machine_id and ${DBConstant._PIPESIZEID} = $pipe_size_id and ${DBConstant._PIPEGRADEID} = $pipe_grade_id and ${DBConstant._PRESSURREID} = $pressure_id"
            val cursor = ApplicationClass.dbAdapter?.executeRawQuery(qry)
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                dataMap["temprature"] = cursor.getString(0) ?: ""
                dataMap["jointing_pressure"] = cursor.getString(1) ?: ""
                dataMap["avg_thickness"] = cursor.getString(2) ?: ""
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataMap
    }

    fun getReports(): ArrayList<ReportDTO> {
        val reportList = ArrayList<ReportDTO>()
        try {
            val qry = "select * from ${DBConstant.TBL_REPORT}"
            val cursor = ApplicationClass.dbAdapter?.executeRawQuery(qry)
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                val fnCol = cursor.getColumnIndex(DBConstant._FILENAME)
                val dataCol = cursor.getColumnIndex(DBConstant._REPORT_DATA)
                val uploadedCol = cursor.getColumnIndex(DBConstant._IS_UPLOADED)
                do {
                    val reportDTO = ReportDTO()
                    reportDTO.fn = if (fnCol >= 0) cursor.getString(fnCol) ?: "" else ""
                    val dataStr = if (dataCol >= 0) cursor.getString(dataCol) else null
                    reportDTO.dataObject = dataStr?.let { try { JSONObject(it) } catch (e: Exception) { null } }
                    reportDTO.isUploaded = if (uploadedCol >= 0) cursor.getString(uploadedCol) ?: "0" else "0"
                    reportList.add(reportDTO)
                } while (cursor.moveToNext())
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return reportList
    }

    fun getReport(fn: String): ReportDTO? {
        var report: ReportDTO? = null
        try {
            val qry = "select * from ${DBConstant.TBL_REPORT} where ${DBConstant._FILENAME} = '$fn'"
            val cursor = ApplicationClass.dbAdapter?.executeRawQuery(qry)
            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                report = ReportDTO()
                val fnCol = cursor.getColumnIndex(DBConstant._FILENAME)
                val dataCol = cursor.getColumnIndex(DBConstant._REPORT_DATA)
                val uploadedCol = cursor.getColumnIndex(DBConstant._IS_UPLOADED)
                report.fn = if (fnCol >= 0) cursor.getString(fnCol) ?: "" else ""
                val dataStr = if (dataCol >= 0) cursor.getString(dataCol) else null
                report.dataObject = dataStr?.let { try { JSONObject(it) } catch (e: Exception) { null } }
                report.isUploaded = if (uploadedCol >= 0) cursor.getString(uploadedCol) ?: "0" else "0"
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return report
    }

    fun insert(table: String, contentValues: ContentValues): Long {
        val result = ApplicationClass.dbAdapter?.insertintoTable(table, contentValues) ?: -1
        Log.e(MainActivity.TAG, "Report Insert Result: $result")
        return result
    }

    fun update(table: String, contentValues: ContentValues, whereClause: String?, whereArgs: Array<String>?): Long {
        val result = ApplicationClass.dbAdapter?.updateTable(table, contentValues, whereClause, whereArgs) ?: -1
        Log.e(MainActivity.TAG, "Report Update Result: $result")
        return result
    }
}
