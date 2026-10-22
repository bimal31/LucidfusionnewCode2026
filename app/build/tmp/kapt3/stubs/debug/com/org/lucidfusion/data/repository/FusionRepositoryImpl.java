package com.org.lucidfusion.data.repository;

import com.org.lucidfusion.dbhelper.DBQueries;
import com.org.lucidfusion.domain.repository.FusionRepository;
import com.org.lucidfusion.dtos.MachineModel;
import com.org.lucidfusion.dtos.PipeGrade;
import com.org.lucidfusion.dtos.PipeSize;
import com.org.lucidfusion.dtos.PressureSize;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J:\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000f2\u0006\u0010\u0011\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u0012J$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u000f2\u0006\u0010\u0011\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/org/lucidfusion/data/repository/FusionRepositoryImpl;", "Lcom/org/lucidfusion/domain/repository/FusionRepository;", "dbQueries", "Lcom/org/lucidfusion/dbhelper/DBQueries;", "(Lcom/org/lucidfusion/dbhelper/DBQueries;)V", "getCalculationData", "", "", "machineId", "", "pipeSizeId", "pipeGradeId", "pressureId", "(IIIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMachineModels", "", "Lcom/org/lucidfusion/dtos/MachineModel;", "standard", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPipeGrades", "Lcom/org/lucidfusion/dtos/PipeGrade;", "getPipeSizesForMachine", "Lcom/org/lucidfusion/dtos/PipeSize;", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPressureRatings", "Lcom/org/lucidfusion/dtos/PressureSize;", "app_debug"})
public final class FusionRepositoryImpl implements com.org.lucidfusion.domain.repository.FusionRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.org.lucidfusion.dbhelper.DBQueries dbQueries = null;
    
    @javax.inject.Inject()
    public FusionRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.dbhelper.DBQueries dbQueries) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getMachineModels(@org.jetbrains.annotations.NotNull()
    java.lang.String standard, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.org.lucidfusion.dtos.MachineModel>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPipeSizesForMachine(int machineId, @org.jetbrains.annotations.NotNull()
    java.lang.String standard, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.org.lucidfusion.dtos.PipeSize>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPipeGrades(@org.jetbrains.annotations.NotNull()
    java.lang.String standard, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.org.lucidfusion.dtos.PipeGrade>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPressureRatings(@org.jetbrains.annotations.NotNull()
    java.lang.String standard, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.org.lucidfusion.dtos.PressureSize>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getCalculationData(int machineId, int pipeSizeId, int pipeGradeId, int pressureId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, java.lang.String>> $completion) {
        return null;
    }
}