package com.org.lucidfusion.domain.repository;

import com.org.lucidfusion.dtos.MachineModel;
import com.org.lucidfusion.dtos.PipeGrade;
import com.org.lucidfusion.dtos.PipeSize;
import com.org.lucidfusion.dtos.PressureSize;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\u0006\u0010\u000e\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u000fJ$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\f2\u0006\u0010\u000e\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0017"}, d2 = {"Lcom/org/lucidfusion/domain/repository/FusionRepository;", "", "getCalculationData", "", "", "machineId", "", "pipeSizeId", "pipeGradeId", "pressureId", "(IIIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMachineModels", "", "Lcom/org/lucidfusion/dtos/MachineModel;", "standard", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPipeGrades", "Lcom/org/lucidfusion/dtos/PipeGrade;", "getPipeSizesForMachine", "Lcom/org/lucidfusion/dtos/PipeSize;", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPressureRatings", "Lcom/org/lucidfusion/dtos/PressureSize;", "app_debug"})
public abstract interface FusionRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMachineModels(@org.jetbrains.annotations.NotNull()
    java.lang.String standard, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.org.lucidfusion.dtos.MachineModel>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPipeSizesForMachine(int machineId, @org.jetbrains.annotations.NotNull()
    java.lang.String standard, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.org.lucidfusion.dtos.PipeSize>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPipeGrades(@org.jetbrains.annotations.NotNull()
    java.lang.String standard, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.org.lucidfusion.dtos.PipeGrade>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPressureRatings(@org.jetbrains.annotations.NotNull()
    java.lang.String standard, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.org.lucidfusion.dtos.PressureSize>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCalculationData(int machineId, int pipeSizeId, int pipeGradeId, int pressureId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, java.lang.String>> $completion);
}