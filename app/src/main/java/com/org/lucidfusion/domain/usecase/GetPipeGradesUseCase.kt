package com.org.lucidfusion.domain.usecase

import com.org.lucidfusion.domain.repository.FusionRepository
import com.org.lucidfusion.dtos.PipeGrade
import javax.inject.Inject

class GetPipeGradesUseCase @Inject constructor(
    private val repository: FusionRepository
) {
    suspend fun execute(standard: String): List<PipeGrade> {
        val list = repository.getPipeGrades(standard).toMutableList()
        val placeholder = PipeGrade(p_grade_id = 0, p_grade_name = "Select Pipe Grade")
        list.add(0, placeholder)
        return list
    }
}
