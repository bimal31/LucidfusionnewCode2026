package com.org.lucidfusion.domain.repository;

import android.content.ContentValues;
import com.org.lucidfusion.data.api.NetworkResult;
import com.org.lucidfusion.dtos.ReportDTO;
import org.json.JSONObject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u00a6@\u00a2\u0006\u0002\u0010\tJ\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J,\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/org/lucidfusion/domain/repository/ReportRepository;", "", "getReport", "Lcom/org/lucidfusion/dtos/ReportDTO;", "fn", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReports", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertReport", "", "reportData", "Lorg/json/JSONObject;", "(Ljava/lang/String;Lorg/json/JSONObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isReportExist", "", "markReportUploaded", "uploadReport", "Lcom/org/lucidfusion/data/api/NetworkResult;", "plcNumber", "(Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ReportRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReports(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.org.lucidfusion.dtos.ReportDTO>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReport(@org.jetbrains.annotations.NotNull()
    java.lang.String fn, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.org.lucidfusion.dtos.ReportDTO> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isReportExist(@org.jetbrains.annotations.NotNull()
    java.lang.String fn, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertReport(@org.jetbrains.annotations.NotNull()
    java.lang.String fn, @org.jetbrains.annotations.NotNull()
    org.json.JSONObject reportData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markReportUploaded(@org.jetbrains.annotations.NotNull()
    java.lang.String fn, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadReport(@org.jetbrains.annotations.NotNull()
    java.lang.String plcNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String fn, @org.jetbrains.annotations.NotNull()
    org.json.JSONObject reportData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.org.lucidfusion.data.api.NetworkResult<java.lang.String>> $completion);
}