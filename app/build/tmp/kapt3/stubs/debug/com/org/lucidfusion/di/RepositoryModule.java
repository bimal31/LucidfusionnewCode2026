package com.org.lucidfusion.di;

import com.org.lucidfusion.data.repository.FusionRepositoryImpl;
import com.org.lucidfusion.data.repository.ReportRepositoryImpl;
import com.org.lucidfusion.domain.repository.FusionRepository;
import com.org.lucidfusion.domain.repository.ReportRepository;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\'\u00a8\u0006\u000b"}, d2 = {"Lcom/org/lucidfusion/di/RepositoryModule;", "", "()V", "bindFusionRepository", "Lcom/org/lucidfusion/domain/repository/FusionRepository;", "fusionRepositoryImpl", "Lcom/org/lucidfusion/data/repository/FusionRepositoryImpl;", "bindReportRepository", "Lcom/org/lucidfusion/domain/repository/ReportRepository;", "reportRepositoryImpl", "Lcom/org/lucidfusion/data/repository/ReportRepositoryImpl;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.org.lucidfusion.domain.repository.FusionRepository bindFusionRepository(@org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.data.repository.FusionRepositoryImpl fusionRepositoryImpl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.org.lucidfusion.domain.repository.ReportRepository bindReportRepository(@org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.data.repository.ReportRepositoryImpl reportRepositoryImpl);
}