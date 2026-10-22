package com.org.lucidfusion.data.repository;

import com.org.lucidfusion.dbhelper.DBQueries;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class FusionRepositoryImpl_Factory implements Factory<FusionRepositoryImpl> {
  private final Provider<DBQueries> dbQueriesProvider;

  public FusionRepositoryImpl_Factory(Provider<DBQueries> dbQueriesProvider) {
    this.dbQueriesProvider = dbQueriesProvider;
  }

  @Override
  public FusionRepositoryImpl get() {
    return newInstance(dbQueriesProvider.get());
  }

  public static FusionRepositoryImpl_Factory create(Provider<DBQueries> dbQueriesProvider) {
    return new FusionRepositoryImpl_Factory(dbQueriesProvider);
  }

  public static FusionRepositoryImpl newInstance(DBQueries dbQueries) {
    return new FusionRepositoryImpl(dbQueries);
  }
}
