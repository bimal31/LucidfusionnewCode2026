package com.org.lucidfusion.domain.usecase;

import com.org.lucidfusion.data.api.NetworkResult;
import com.org.lucidfusion.domain.repository.ReportRepository;
import org.json.JSONObject;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J,\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/org/lucidfusion/domain/usecase/UploadReportUseCase;", "", "reportRepository", "Lcom/org/lucidfusion/domain/repository/ReportRepository;", "(Lcom/org/lucidfusion/domain/repository/ReportRepository;)V", "execute", "Lcom/org/lucidfusion/data/api/NetworkResult;", "", "plcNumber", "fn", "reportData", "Lorg/json/JSONObject;", "(Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UploadReportUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.org.lucidfusion.domain.repository.ReportRepository reportRepository = null;
    
    @javax.inject.Inject()
    public UploadReportUseCase(@org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.domain.repository.ReportRepository reportRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object execute(@org.jetbrains.annotations.NotNull()
    java.lang.String plcNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String fn, @org.jetbrains.annotations.NotNull()
    org.json.JSONObject reportData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.org.lucidfusion.data.api.NetworkResult<java.lang.String>> $completion) {
        return null;
    }
}