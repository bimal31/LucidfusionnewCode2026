package com.org.lucidfusion.di

import android.content.Context
import com.org.lucidfusion.dbhelper.DBAdapter
import com.org.lucidfusion.dbhelper.DBQueries
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDBAdapter(@ApplicationContext context: Context): DBAdapter {
        return DBAdapter.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideDBQueries(@ApplicationContext context: Context): DBQueries {
        return DBQueries(context)
    }
}
