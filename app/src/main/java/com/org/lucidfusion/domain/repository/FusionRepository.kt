package com.org.lucidfusion.domain.repository

import com.org.lucidfusion.dtos.MachineModel
import com.org.lucidfusion.dtos.PipeGrade
import com.org.lucidfusion.dtos.PipeSize
import com.org.lucidfusion.dtos.PressureSize

interface FusionRepository {
    suspend fun getMachineModels(standard: String): List<MachineModel>
    suspend fun getPipeSizesForMachine(machineId: Int, standard: String): List<PipeSize>
    suspend fun getPipeGrades(standard: String): List<PipeGrade>
    suspend fun getPressureRatings(standard: String): List<PressureSize>
    suspend fun getCalculationData(machineId: Int, pipeSizeId: Int, pipeGradeId: Int, pressureId: Int): Map<String, String>
}
