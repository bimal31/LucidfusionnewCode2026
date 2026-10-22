package com.org.lucidfusion.ui.calculation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.lucidfusion.domain.repository.FusionRepository
import com.org.lucidfusion.domain.usecase.CalculateFusionUseCase
import com.org.lucidfusion.domain.usecase.GetMachineModelsUseCase
import com.org.lucidfusion.domain.usecase.GetPipeGradesUseCase
import com.org.lucidfusion.domain.usecase.GetPipeSizesUseCase
import com.org.lucidfusion.domain.usecase.GetPressureRatingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculationViewModel @Inject constructor(
    private val getMachineModelsUseCase: GetMachineModelsUseCase,
    private val getPipeSizesUseCase: GetPipeSizesUseCase,
    private val getPipeGradesUseCase: GetPipeGradesUseCase,
    private val getPressureRatingsUseCase: GetPressureRatingsUseCase,
    private val calculateFusionUseCase: CalculateFusionUseCase,
    private val fusionRepository: FusionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CalculationUiState())
    val uiState: StateFlow<CalculationUiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<CalculationEffect>()
    val uiEffect: SharedFlow<CalculationEffect> = _uiEffect.asSharedFlow()

    fun handleIntent(intent: CalculationIntent) {
        when (intent) {
            is CalculationIntent.ChangeStandard -> loadStandardData(intent.standard)
            is CalculationIntent.SelectMachine -> onMachineSelected(intent.position)
            is CalculationIntent.SelectPipeSize -> onPipeSizeSelected(intent.position)
            is CalculationIntent.SelectPipeGrade -> onPipeGradeSelected(intent.position)
            is CalculationIntent.SelectPressureRating -> onPressureRatingSelected(intent.position)
            is CalculationIntent.DragPressureChanged -> onDragPressureChanged(intent.dragPressure)
            is CalculationIntent.CalculateClicked -> onCalculateClicked()
        }
    }

    fun initLanguage(isHindi: Boolean) {
        _uiState.update { it.copy(isHindi = isHindi) }
    }

    fun loadStandardData(standard: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, selectedStandard = standard) }
            val machines = getMachineModelsUseCase.execute(standard)
            val grades = getPipeGradesUseCase.execute(standard)
            val pressures = getPressureRatingsUseCase.execute(standard)
            val sizes = getPipeSizesUseCase.execute(0, standard)

            _uiState.update {
                it.copy(
                    isLoading = false,
                    machineModels = machines,
                    pipeGrades = grades,
                    pressureRatings = pressures,
                    pipeSizes = sizes,
                    selectedMachineId = 0,
                    selectedPipeSizeId = 0,
                    selectedPipeGradeId = 0,
                    selectedPressureId = 0,
                    jointingPressure = "",
                    calculationResult = null,
                    isCalculateCalled = false
                )
            }
        }
    }

    private fun onMachineSelected(position: Int) {
        val currentState = _uiState.value
        if (position == 0) {
            _uiState.update {
                it.copy(
                    selectedMachineId = 0,
                    pipeSizes = emptyList(),
                    jointingPressure = "",
                    calculationResult = null
                )
            }
            return
        }
        val machineId = currentState.machineModels.getOrNull(position)?.machine_id ?: 0
        viewModelScope.launch {
            val sizes = getPipeSizesUseCase.execute(machineId, currentState.selectedStandard)
            _uiState.update {
                it.copy(
                    selectedMachineId = machineId,
                    pipeSizes = sizes,
                    selectedPipeSizeId = 0,
                    jointingPressure = "",
                    calculationResult = null
                )
            }
        }
    }

    private fun onPipeSizeSelected(position: Int) {
        val currentState = _uiState.value
        if (position == 0) {
            _uiState.update { it.copy(selectedPipeSizeId = 0, calculationResult = null) }
            return
        }
        val pipeSizeId = currentState.pipeSizes.getOrNull(position)?.pipe_size_id ?: 0
        _uiState.update { it.copy(selectedPipeSizeId = pipeSizeId) }
        checkAndFetchCalcData()
    }

    private fun onPipeGradeSelected(position: Int) {
        val currentState = _uiState.value
        if (position == 0) {
            _uiState.update { it.copy(selectedPipeGradeId = 0, calculationResult = null) }
            return
        }
        val gradeId = currentState.pipeGrades.getOrNull(position)?.p_grade_id ?: 0
        _uiState.update { it.copy(selectedPipeGradeId = gradeId) }
        checkAndFetchCalcData()
    }

    private fun onPressureRatingSelected(position: Int) {
        val currentState = _uiState.value
        if (position == 0) {
            _uiState.update { it.copy(selectedPressureId = 0, calculationResult = null) }
            return
        }
        val pressureId = currentState.pressureRatings.getOrNull(position)?.pressure_id ?: 0
        _uiState.update { it.copy(selectedPressureId = pressureId) }
        checkAndFetchCalcData()
    }

    private fun checkAndFetchCalcData() {
        val state = _uiState.value
        if (state.selectedMachineId > 0 && state.selectedPipeSizeId > 0 &&
            state.selectedPipeGradeId > 0 && state.selectedPressureId > 0) {
            viewModelScope.launch {
                val dataMap = fusionRepository.getCalculationData(
                    state.selectedMachineId,
                    state.selectedPipeSizeId,
                    state.selectedPipeGradeId,
                    state.selectedPressureId
                )
                val jointingPress = dataMap["jointing_pressure"] ?: ""
                val avgThickness = dataMap["avg_thickness"] ?: ""
                val temp = dataMap["temprature"] ?: ""

                _uiState.update { it.copy(jointingPressure = jointingPress) }

                if (state.isCalculateCalled) {
                    performCalculation(avgThickness, jointingPress, temp)
                }
            }
        }
    }

    private fun onDragPressureChanged(dragPress: String) {
        _uiState.update { it.copy(dragPressure = dragPress) }
        val state = _uiState.value
        if (state.isCalculateCalled) {
            if (dragPress.isBlank()) {
                _uiState.update { it.copy(calculationResult = null) }
            } else {
                checkAndFetchCalcData()
            }
        }
    }

    private fun onCalculateClicked() {
        val state = _uiState.value
        viewModelScope.launch {
            if (state.selectedMachineId == 0) {
                _uiEffect.emit(CalculationEffect.ShowToast("Please select machine model."))
                return@launch
            }
            if (state.selectedPipeSizeId == 0) {
                _uiEffect.emit(CalculationEffect.ShowToast("Please select Pipe size."))
                return@launch
            }
            if (state.selectedPipeGradeId == 0) {
                _uiEffect.emit(CalculationEffect.ShowToast("Please select Pipe Grade."))
                return@launch
            }
            if (state.selectedPressureId == 0) {
                _uiEffect.emit(CalculationEffect.ShowToast("Please select Pressure Rating."))
                return@launch
            }
            if (state.dragPressure.trim().isEmpty()) {
                _uiEffect.emit(CalculationEffect.ShowToast("Please enter Drag Pressure."))
                return@launch
            }

            _uiState.update { it.copy(isCalculateCalled = true) }
            val dataMap = fusionRepository.getCalculationData(
                state.selectedMachineId,
                state.selectedPipeSizeId,
                state.selectedPipeGradeId,
                state.selectedPressureId
            )
            performCalculation(
                dataMap["avg_thickness"],
                dataMap["jointing_pressure"],
                dataMap["temprature"]
            )
            _uiEffect.emit(CalculationEffect.ScrollToBottom)
        }
    }

    private fun performCalculation(avgThickness: String?, jointingPress: String?, temperature: String?) {
        val state = _uiState.value
        val result = calculateFusionUseCase.execute(
            machineId = state.selectedMachineId,
            avgThicknessStr = avgThickness,
            jointingPressureStr = jointingPress ?: state.jointingPressure,
            dragPressureStr = state.dragPressure,
            temperatureStr = temperature
        )
        _uiState.update { it.copy(calculationResult = result) }
    }
}
