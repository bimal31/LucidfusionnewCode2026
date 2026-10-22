package com.org.lucidfusion.domain.usecase

import com.org.lucidfusion.domain.repository.FusionRepository
import com.org.lucidfusion.dtos.MachineModel
import javax.inject.Inject

class GetMachineModelsUseCase @Inject constructor(
    private val repository: FusionRepository
) {
    suspend fun execute(standard: String): List<MachineModel> {
        val list = repository.getMachineModels(standard).toMutableList()
        val placeholder = MachineModel(machine_id = 0, machine_name = "Select Machine Model")
        list.add(0, placeholder)
        return list
    }
}
