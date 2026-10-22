package com.org.lucidfusion.di;

import android.content.Context;
import com.org.lucidfusion.dbhelper.DBQueries;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideDBQueriesFactory implements Factory<DBQueries> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideDBQueriesFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DBQueries get() {
    return provideDBQueries(contextProvider.get());
  }

  public static DatabaseModule_ProvideDBQueriesFactory create(Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideDBQueriesFactory(contextProvider);
  }

  public static DBQueries provideDBQueries(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDBQueries(context));
  }
}
