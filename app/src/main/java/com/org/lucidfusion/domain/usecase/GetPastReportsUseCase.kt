package com.org.lucidfusion.domain.usecase

import com.org.lucidfusion.domain.repository.ReportRepository
import com.org.lucidfusion.dtos.ReportDTO
import javax.inject.Inject

class GetPastReportsUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    suspend fun execute(): List<ReportDTO> {
        return reportRepository.getReports()
    }
}
