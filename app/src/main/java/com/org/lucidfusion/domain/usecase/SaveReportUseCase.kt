package com.org.lucidfusion.domain.usecase

import com.org.lucidfusion.domain.repository.ReportRepository
import org.json.JSONObject
import javax.inject.Inject

class SaveReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    suspend fun execute(fn: String, reportData: JSONObject): Long {
        return if (reportRepository.isReportExist(fn)) {
            reportRepository.insertReport(fn, reportData)
        } else {
            reportRepository.insertReport(fn, reportData)
        }
    }
}
