package com.org.lucidfusion.domain.repository

import android.content.ContentValues
import com.org.lucidfusion.data.api.NetworkResult
import com.org.lucidfusion.dtos.ReportDTO
import org.json.JSONObject

interface ReportRepository {
    suspend fun getReports(): List<ReportDTO>
    suspend fun getReport(fn: String): ReportDTO?
    suspend fun isReportExist(fn: String): Boolean
    suspend fun insertReport(fn: String, reportData: JSONObject): Long
    suspend fun markReportUploaded(fn: String): Long
    suspend fun uploadReport(plcNumber: String, fn: String, reportData: JSONObject): NetworkResult<String>
}
