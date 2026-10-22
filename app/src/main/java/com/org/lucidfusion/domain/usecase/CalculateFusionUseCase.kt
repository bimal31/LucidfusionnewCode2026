package com.org.lucidfusion.domain.usecase

import javax.inject.Inject

data class FusionCalculationResult(
    val beadUpMin: Int,
    val beadUpSec: Int,
    val soakingMin: Int,
    val soakingSec: Int,
    val dragPressure: String,
    val weldingPressure: Float,
    val coolingTimeMin: Int,
    val temperature: String
)

class CalculateFusionUseCase @Inject constructor() {

    fun execute(
        machineId: Int,
        avgThicknessStr: String?,
        jointingPressureStr: String?,
        dragPressureStr: String,
        temperatureStr: String?
    ): FusionCalculationResult {
        val avgThickness = avgThicknessStr?.toFloatOrNull() ?: 0f
        val jointingPressure = jointingPressureStr?.toFloatOrNull() ?: 0f
        val dragPressure = dragPressureStr.toFloatOrNull() ?: 0f

        val divider = if (machineId == 1 || machineId == 7) 1 else 60
        val beadUpTime = (6 * avgThickness) / divider
        val soakingTime = (15 * avgThickness) / divider
        val coolingTime = 1.5f * avgThickness
        val weldingPressure = jointingPressure + dragPressure

        val (beadMin, beadSec) = if (machineId == 1 || machineId == 7) {
            Pair((beadUpTime / 60).toInt(), Math.round(beadUpTime % 60))
        } else {
            Pair(Math.round(beadUpTime), 0)
        }

        val (soakMin, soakSec) = if (machineId == 1 || machineId == 7) {
            Pair((soakingTime / 60).toInt(), Math.round(soakingTime % 60))
        } else {
            Pair(Math.round(soakingTime), 0)
        }

        return FusionCalculationResult(
            beadUpMin = beadMin,
            beadUpSec = beadSec,
            soakingMin = soakMin,
            soakingSec = soakSec,
            dragPressure = dragPressureStr,
            weldingPressure = weldingPressure,
            coolingTimeMin = Math.round(coolingTime),
            temperature = temperatureStr ?: ""
        )
    }
}
