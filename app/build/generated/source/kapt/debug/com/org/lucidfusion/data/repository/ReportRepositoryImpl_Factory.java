package com.org.lucidfusion.data.repository;

import android.content.Context;
import com.org.lucidfusion.data.api.WeldReportApiService;
import com.org.lucidfusion.dbhelper.DBQueries;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class ReportRepositoryImpl_Factory implements Factory<ReportRepositoryImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<DBQueries> dbQueriesProvider;

  private final Provider<WeldReportApiService> apiServiceProvider;

  public ReportRepositoryImpl_Factory(Provider<Context> contextProvider,
      Provider<DBQueries> dbQueriesProvider, Provider<WeldReportApiService> apiServiceProvider) {
    this.contextProvider = contextProvider;
    this.dbQueriesProvider = dbQueriesProvider;
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public ReportRepositoryImpl get() {
    return newInstance(contextProvider.get(), dbQueriesProvider.get(), apiServiceProvider.get());
  }

  public static ReportRepositoryImpl_Factory create(Provider<Context> contextProvider,
      Provider<DBQueries> dbQueriesProvider, Provider<WeldReportApiService> apiServiceProvider) {
    return new ReportRepositoryImpl_Factory(contextProvider, dbQueriesProvider, apiServiceProvider);
  }

  public static ReportRepositoryImpl newInstance(Context context, DBQueries dbQueries,
      WeldReportApiService apiService) {
    return new ReportRepositoryImpl(context, dbQueries, apiService);
  }
}
