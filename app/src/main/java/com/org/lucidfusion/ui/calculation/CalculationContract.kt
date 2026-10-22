package com.org.lucidfusion.ui.calculation

import com.org.lucidfusion.domain.usecase.FusionCalculationResult
import com.org.lucidfusion.dtos.MachineModel
import com.org.lucidfusion.dtos.PipeGrade
import com.org.lucidfusion.dtos.PipeSize
import com.org.lucidfusion.dtos.PressureSize

data class CalculationUiState(
    val selectedStandard: String = "4984",
    val machineModels: List<MachineModel> = emptyList(),
    val pipeSizes: List<PipeSize> = emptyList(),
    val pipeGrades: List<PipeGrade> = emptyList(),
    val pressureRatings: List<PressureSize> = emptyList(),
    val selectedMachineId: Int = 0,
    val selectedPipeSizeId: Int = 0,
    val selectedPipeGradeId: Int = 0,
    val selectedPressureId: Int = 0,
    val jointingPressure: String = "",
    val dragPressure: String = "",
    val isHindi: Boolean = false,
    val isCalculateCalled: Boolean = false,
    val calculationResult: FusionCalculationResult? = null,
    val isLoading: Boolean = false
)

sealed interface CalculationIntent {
    data class ChangeStandard(val standard: String) : CalculationIntent
    data class SelectMachine(val position: Int) : CalculationIntent
    data class SelectPipeSize(val position: Int) : CalculationIntent
    data class SelectPipeGrade(val position: Int) : CalculationIntent
    data class SelectPressureRating(val position: Int) : CalculationIntent
    data class DragPressureChanged(val dragPressure: String) : CalculationIntent
    object CalculateClicked : CalculationIntent
}

sealed interface CalculationEffect {
    data class ShowToast(val message: String) : CalculationEffect
    object ScrollToBottom : CalculationEffect
}
