package com.org.lucidfusion.ui.calculation;

import com.org.lucidfusion.domain.usecase.FusionCalculationResult;
import com.org.lucidfusion.dtos.MachineModel;
import com.org.lucidfusion.dtos.PipeGrade;
import com.org.lucidfusion.dtos.PipeSize;
import com.org.lucidfusion.dtos.PressureSize;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b(\b\u0086\b\u0018\u00002\u00020\u0001B\u00b5\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0015\u00a2\u0006\u0002\u0010\u001aJ\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0015H\u00c6\u0003J\t\u00100\u001a\u00020\u0015H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0018H\u00c6\u0003J\t\u00102\u001a\u00020\u0015H\u00c6\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\u00c6\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\n0\u0005H\u00c6\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\f0\u0005H\u00c6\u0003J\t\u00107\u001a\u00020\u000eH\u00c6\u0003J\t\u00108\u001a\u00020\u000eH\u00c6\u0003J\t\u00109\u001a\u00020\u000eH\u00c6\u0003J\t\u0010:\u001a\u00020\u000eH\u00c6\u0003J\u00b9\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0015H\u00c6\u0001J\u0013\u0010<\u001a\u00020\u00152\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010>\u001a\u00020\u000eH\u00d6\u0001J\t\u0010?\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u001fR\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u001fR\u0011\u0010\u0019\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001fR\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\"R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0011\u0010\u0010\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\'R\u0011\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\'R\u0011\u0010\u0011\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\'R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001e\u00a8\u0006@"}, d2 = {"Lcom/org/lucidfusion/ui/calculation/CalculationUiState;", "", "selectedStandard", "", "machineModels", "", "Lcom/org/lucidfusion/dtos/MachineModel;", "pipeSizes", "Lcom/org/lucidfusion/dtos/PipeSize;", "pipeGrades", "Lcom/org/lucidfusion/dtos/PipeGrade;", "pressureRatings", "Lcom/org/lucidfusion/dtos/PressureSize;", "selectedMachineId", "", "selectedPipeSizeId", "selectedPipeGradeId", "selectedPressureId", "jointingPressure", "dragPressure", "isHindi", "", "isCalculateCalled", "calculationResult", "Lcom/org/lucidfusion/domain/usecase/FusionCalculationResult;", "isLoading", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;IIIILjava/lang/String;Ljava/lang/String;ZZLcom/org/lucidfusion/domain/usecase/FusionCalculationResult;Z)V", "getCalculationResult", "()Lcom/org/lucidfusion/domain/usecase/FusionCalculationResult;", "getDragPressure", "()Ljava/lang/String;", "()Z", "getJointingPressure", "getMachineModels", "()Ljava/util/List;", "getPipeGrades", "getPipeSizes", "getPressureRatings", "getSelectedMachineId", "()I", "getSelectedPipeGradeId", "getSelectedPipeSizeId", "getSelectedPressureId", "getSelectedStandard", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class CalculationUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String selectedStandard = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.org.lucidfusion.dtos.MachineModel> machineModels = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.org.lucidfusion.dtos.PipeSize> pipeSizes = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.org.lucidfusion.dtos.PipeGrade> pipeGrades = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.org.lucidfusion.dtos.PressureSize> pressureRatings = null;
    private final int selectedMachineId = 0;
    private final int selectedPipeSizeId = 0;
    private final int selectedPipeGradeId = 0;
    private final int selectedPressureId = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String jointingPressure = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dragPressure = null;
    private final boolean isHindi = false;
    private final boolean isCalculateCalled = false;
    @org.jetbrains.annotations.Nullable()
    private final com.org.lucidfusion.domain.usecase.FusionCalculationResult calculationResult = null;
    private final boolean isLoading = false;
    
    public CalculationUiState(@org.jetbrains.annotations.NotNull()
    java.lang.String selectedStandard, @org.jetbrains.annotations.NotNull()
    java.util.List<com.org.lucidfusion.dtos.MachineModel> machineModels, @org.jetbrains.annotations.NotNull()
    java.util.List<com.org.lucidfusion.dtos.PipeSize> pipeSizes, @org.jetbrains.annotations.NotNull()
    java.util.List<com.org.lucidfusion.dtos.PipeGrade> pipeGrades, @org.jetbrains.annotations.NotNull()
    java.util.List<com.org.lucidfusion.dtos.PressureSize> pressureRatings, int selectedMachineId, int selectedPipeSizeId, int selectedPipeGradeId, int selectedPressureId, @org.jetbrains.annotations.NotNull()
    java.lang.String jointingPressure, @org.jetbrains.annotations.NotNull()
    java.lang.String dragPressure, boolean isHindi, boolean isCalculateCalled, @org.jetbrains.annotations.Nullable()
    com.org.lucidfusion.domain.usecase.FusionCalculationResult calculationResult, boolean isLoading) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelectedStandard() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.org.lucidfusion.dtos.MachineModel> getMachineModels() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.org.lucidfusion.dtos.PipeSize> getPipeSizes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.org.lucidfusion.dtos.PipeGrade> getPipeGrades() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.org.lucidfusion.dtos.PressureSize> getPressureRatings() {
        return null;
    }
    
    public final int getSelectedMachineId() {
        return 0;
    }
    
    public final int getSelectedPipeSizeId() {
        return 0;
    }
    
    public final int getSelectedPipeGradeId() {
        return 0;
    }
    
    public final int getSelectedPressureId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJointingPressure() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDragPressure() {
        return null;
    }
    
    public final boolean isHindi() {
        return false;
    }
    
    public final boolean isCalculateCalled() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.org.lucidfusion.domain.usecase.FusionCalculationResult getCalculationResult() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public CalculationUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    public final boolean component12() {
        return false;
    }
    
    public final boolean component13() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.org.lucidfusion.domain.usecase.FusionCalculationResult component14() {
        return null;
    }
    
    public final boolean component15() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.org.lucidfusion.dtos.MachineModel> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.org.lucidfusion.dtos.PipeSize> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.org.lucidfusion.dtos.PipeGrade> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.org.lucidfusion.dtos.PressureSize> component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.org.lucidfusion.ui.calculation.CalculationUiState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String selectedStandard, @org.jetbrains.annotations.NotNull()
    java.util.List<com.org.lucidfusion.dtos.MachineModel> machineModels, @org.jetbrains.annotations.NotNull()
    java.util.List<com.org.lucidfusion.dtos.PipeSize> pipeSizes, @org.jetbrains.annotations.NotNull()
    java.util.List<com.org.lucidfusion.dtos.PipeGrade> pipeGrades, @org.jetbrains.annotations.NotNull()
    java.util.List<com.org.lucidfusion.dtos.PressureSize> pressureRatings, int selectedMachineId, int selectedPipeSizeId, int selectedPipeGradeId, int selectedPressureId, @org.jetbrains.annotations.NotNull()
    java.lang.String jointingPressure, @org.jetbrains.annotations.NotNull()
    java.lang.String dragPressure, boolean isHindi, boolean isCalculateCalled, @org.jetbrains.annotations.Nullable()
    com.org.lucidfusion.domain.usecase.FusionCalculationResult calculationResult, boolean isLoading) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}