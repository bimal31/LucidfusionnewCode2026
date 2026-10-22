package com.org.lucidfusion.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import com.org.lucidfusion.AppConstants;
import com.org.lucidfusion.ApplicationClass;
import com.org.lucidfusion.data.api.NetworkResult;
import com.org.lucidfusion.data.api.WeldReportApiService;
import com.org.lucidfusion.dbhelper.DBConstant;
import com.org.lucidfusion.dbhelper.DBQueries;
import com.org.lucidfusion.domain.repository.ReportRepository;
import com.org.lucidfusion.dtos.ReportDTO;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ,\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u001a2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/org/lucidfusion/data/repository/ReportRepositoryImpl;", "Lcom/org/lucidfusion/domain/repository/ReportRepository;", "context", "Landroid/content/Context;", "dbQueries", "Lcom/org/lucidfusion/dbhelper/DBQueries;", "apiService", "Lcom/org/lucidfusion/data/api/WeldReportApiService;", "(Landroid/content/Context;Lcom/org/lucidfusion/dbhelper/DBQueries;Lcom/org/lucidfusion/data/api/WeldReportApiService;)V", "getReport", "Lcom/org/lucidfusion/dtos/ReportDTO;", "fn", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReports", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertReport", "", "reportData", "Lorg/json/JSONObject;", "(Ljava/lang/String;Lorg/json/JSONObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isReportExist", "", "markReportUploaded", "uploadReport", "Lcom/org/lucidfusion/data/api/NetworkResult;", "plcNumber", "(Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ReportRepositoryImpl implements com.org.lucidfusion.domain.repository.ReportRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.org.lucidfusion.dbhelper.DBQueries dbQueries = null;
    @org.jetbrains.annotations.NotNull()
    private final com.org.lucidfusion.data.api.WeldReportApiService apiService = null;
    
    @javax.inject.Inject()
    public ReportRepositoryImpl(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.dbhelper.DBQueries dbQueries, @org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.data.api.WeldReportApiService apiService) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getReports(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.org.lucidfusion.dtos.ReportDTO>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getReport(@org.jetbrains.annotations.NotNull()
    java.lang.String fn, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.org.lucidfusion.dtos.ReportDTO> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object isReportExist(@org.jetbrains.annotations.NotNull()
    java.lang.String fn, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insertReport(@org.jetbrains.annotations.NotNull()
    java.lang.String fn, @org.jetbrains.annotations.NotNull()
    org.json.JSONObject reportData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object markReportUploaded(@org.jetbrains.annotations.NotNull()
    java.lang.String fn, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object uploadReport(@org.jetbrains.annotations.NotNull()
    java.lang.String plcNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String fn, @org.jetbrains.annotations.NotNull()
    org.json.JSONObject reportData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.org.lucidfusion.data.api.NetworkResult<java.lang.String>> $completion) {
        return null;
    }
}