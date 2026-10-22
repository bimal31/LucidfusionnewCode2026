package com.org.lucidfusion.di

import com.org.lucidfusion.data.repository.FusionRepositoryImpl
import com.org.lucidfusion.data.repository.ReportRepositoryImpl
import com.org.lucidfusion.domain.repository.FusionRepository
import com.org.lucidfusion.domain.repository.ReportRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFusionRepository(
        fusionRepositoryImpl: FusionRepositoryImpl
    ): FusionRepository

    @Binds
    @Singleton
    abstract fun bindReportRepository(
        reportRepositoryImpl: ReportRepositoryImpl
    ): ReportRepository
}
