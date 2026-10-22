package com.org.lucidfusion.domain.usecase

import com.org.lucidfusion.domain.repository.FusionRepository
import com.org.lucidfusion.dtos.PipeSize
import javax.inject.Inject

class GetPipeSizesUseCase @Inject constructor(
    private val repository: FusionRepository
) {
    suspend fun execute(machineId: Int, standard: String): List<PipeSize> {
        val list = if (machineId > 0) {
            repository.getPipeSizesForMachine(machineId, standard).toMutableList()
        } else {
            mutableListOf()
        }
        val placeholder = PipeSize(pipe_size_id = 0, pipe_size_name = "Select Pipe Size")
        list.add(0, placeholder)
        return list
    }
}
