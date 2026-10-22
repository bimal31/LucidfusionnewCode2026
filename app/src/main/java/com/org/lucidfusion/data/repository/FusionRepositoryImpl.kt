package com.org.lucidfusion.data.repository

import com.org.lucidfusion.dbhelper.DBQueries
import com.org.lucidfusion.domain.repository.FusionRepository
import com.org.lucidfusion.dtos.MachineModel
import com.org.lucidfusion.dtos.PipeGrade
import com.org.lucidfusion.dtos.PipeSize
import com.org.lucidfusion.dtos.PressureSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FusionRepositoryImpl @Inject constructor(
    private val dbQueries: DBQueries
) : FusionRepository {

    override suspend fun getMachineModels(standard: String): List<MachineModel> = withContext(Dispatchers.IO) {
        dbQueries.getMachineModels(standard)
    }

    override suspend fun getPipeSizesForMachine(machineId: Int, standard: String): List<PipeSize> = withContext(Dispatchers.IO) {
        dbQueries.getPipeSizeForMachine(machineId, standard)
    }

    override suspend fun getPipeGrades(standard: String): List<PipeGrade> = withContext(Dispatchers.IO) {
        dbQueries.getPipeGrades(standard)
    }

    override suspend fun getPressureRatings(standard: String): List<PressureSize> = withContext(Dispatchers.IO) {
        dbQueries.getPressureSize(standard)
    }

    override suspend fun getCalculationData(
        machineId: Int,
        pipeSizeId: Int,
        pipeGradeId: Int,
        pressureId: Int
    ): Map<String, String> = withContext(Dispatchers.IO) {
        dbQueries.getCalcData(machineId, pipeSizeId, pipeGradeId, pressureId)
    }
}
