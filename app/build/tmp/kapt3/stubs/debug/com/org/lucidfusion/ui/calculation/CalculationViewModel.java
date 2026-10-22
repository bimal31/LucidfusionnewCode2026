package com.org.lucidfusion.ui.calculation;

import androidx.lifecycle.ViewModel;
import com.org.lucidfusion.domain.repository.FusionRepository;
import com.org.lucidfusion.domain.usecase.CalculateFusionUseCase;
import com.org.lucidfusion.domain.usecase.GetMachineModelsUseCase;
import com.org.lucidfusion.domain.usecase.GetPipeGradesUseCase;
import com.org.lucidfusion.domain.usecase.GetPipeSizesUseCase;
import com.org.lucidfusion.domain.usecase.GetPressureRatingsUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\'J\b\u0010(\u001a\u00020\u001eH\u0002J\u0010\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\'H\u0002J\u0010\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010.\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010/\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u00100\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020-H\u0002J&\u00101\u001a\u00020\u001e2\b\u00102\u001a\u0004\u0018\u00010\'2\b\u00103\u001a\u0004\u0018\u00010\'2\b\u00104\u001a\u0004\u0018\u00010\'H\u0002R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u00065"}, d2 = {"Lcom/org/lucidfusion/ui/calculation/CalculationViewModel;", "Landroidx/lifecycle/ViewModel;", "getMachineModelsUseCase", "Lcom/org/lucidfusion/domain/usecase/GetMachineModelsUseCase;", "getPipeSizesUseCase", "Lcom/org/lucidfusion/domain/usecase/GetPipeSizesUseCase;", "getPipeGradesUseCase", "Lcom/org/lucidfusion/domain/usecase/GetPipeGradesUseCase;", "getPressureRatingsUseCase", "Lcom/org/lucidfusion/domain/usecase/GetPressureRatingsUseCase;", "calculateFusionUseCase", "Lcom/org/lucidfusion/domain/usecase/CalculateFusionUseCase;", "fusionRepository", "Lcom/org/lucidfusion/domain/repository/FusionRepository;", "(Lcom/org/lucidfusion/domain/usecase/GetMachineModelsUseCase;Lcom/org/lucidfusion/domain/usecase/GetPipeSizesUseCase;Lcom/org/lucidfusion/domain/usecase/GetPipeGradesUseCase;Lcom/org/lucidfusion/domain/usecase/GetPressureRatingsUseCase;Lcom/org/lucidfusion/domain/usecase/CalculateFusionUseCase;Lcom/org/lucidfusion/domain/repository/FusionRepository;)V", "_uiEffect", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/org/lucidfusion/ui/calculation/CalculationEffect;", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/org/lucidfusion/ui/calculation/CalculationUiState;", "uiEffect", "Lkotlinx/coroutines/flow/SharedFlow;", "getUiEffect", "()Lkotlinx/coroutines/flow/SharedFlow;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "checkAndFetchCalcData", "", "handleIntent", "intent", "Lcom/org/lucidfusion/ui/calculation/CalculationIntent;", "initLanguage", "isHindi", "", "loadStandardData", "standard", "", "onCalculateClicked", "onDragPressureChanged", "dragPress", "onMachineSelected", "position", "", "onPipeGradeSelected", "onPipeSizeSelected", "onPressureRatingSelected", "performCalculation", "avgThickness", "jointingPress", "temperature", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CalculationViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.org.lucidfusion.domain.usecase.GetMachineModelsUseCase getMachineModelsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.org.lucidfusion.domain.usecase.GetPipeSizesUseCase getPipeSizesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.org.lucidfusion.domain.usecase.GetPipeGradesUseCase getPipeGradesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.org.lucidfusion.domain.usecase.GetPressureRatingsUseCase getPressureRatingsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.org.lucidfusion.domain.usecase.CalculateFusionUseCase calculateFusionUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.org.lucidfusion.domain.repository.FusionRepository fusionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.org.lucidfusion.ui.calculation.CalculationUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.org.lucidfusion.ui.calculation.CalculationUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.org.lucidfusion.ui.calculation.CalculationEffect> _uiEffect = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.org.lucidfusion.ui.calculation.CalculationEffect> uiEffect = null;
    
    @javax.inject.Inject()
    public CalculationViewModel(@org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.domain.usecase.GetMachineModelsUseCase getMachineModelsUseCase, @org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.domain.usecase.GetPipeSizesUseCase getPipeSizesUseCase, @org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.domain.usecase.GetPipeGradesUseCase getPipeGradesUseCase, @org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.domain.usecase.GetPressureRatingsUseCase getPressureRatingsUseCase, @org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.domain.usecase.CalculateFusionUseCase calculateFusionUseCase, @org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.domain.repository.FusionRepository fusionRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.org.lucidfusion.ui.calculation.CalculationUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.org.lucidfusion.ui.calculation.CalculationEffect> getUiEffect() {
        return null;
    }
    
    public final void handleIntent(@org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.ui.calculation.CalculationIntent intent) {
    }
    
    public final void initLanguage(boolean isHindi) {
    }
    
    public final void loadStandardData(@org.jetbrains.annotations.NotNull()
    java.lang.String standard) {
    }
    
    private final void onMachineSelected(int position) {
    }
    
    private final void onPipeSizeSelected(int position) {
    }
    
    private final void onPipeGradeSelected(int position) {
    }
    
    private final void onPressureRatingSelected(int position) {
    }
    
    private final void checkAndFetchCalcData() {
    }
    
    private final void onDragPressureChanged(java.lang.String dragPress) {
    }
    
    private final void onCalculateClicked() {
    }
    
    private final void performCalculation(java.lang.String avgThickness, java.lang.String jointingPress, java.lang.String temperature) {
    }
}