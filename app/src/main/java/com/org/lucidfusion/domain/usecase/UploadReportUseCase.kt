package com.org.lucidfusion.domain.usecase

import com.org.lucidfusion.data.api.NetworkResult
import com.org.lucidfusion.domain.repository.ReportRepository
import org.json.JSONObject
import javax.inject.Inject

class UploadReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    suspend fun execute(plcNumber: String, fn: String, reportData: JSONObject): NetworkResult<String> {
        return reportRepository.uploadReport(plcNumber, fn, reportData)
    }
}
