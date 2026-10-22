package com.org.lucidfusion.domain.usecase

import com.org.lucidfusion.domain.repository.FusionRepository
import com.org.lucidfusion.dtos.PressureSize
import javax.inject.Inject

class GetPressureRatingsUseCase @Inject constructor(
    private val repository: FusionRepository
) {
    suspend fun execute(standard: String): List<PressureSize> {
        val list = repository.getPressureRatings(standard).toMutableList()
        val placeholder = PressureSize(pressure_id = 0, pressure_size_name = "Select Pressure Rating")
        list.add(0, placeholder)
        return list
    }
}
