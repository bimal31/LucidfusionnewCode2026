package com.org.lucidfusion.di;

import android.content.Context;
import com.org.lucidfusion.dbhelper.DBAdapter;
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
public final class DatabaseModule_ProvideDBAdapterFactory implements Factory<DBAdapter> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideDBAdapterFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DBAdapter get() {
    return provideDBAdapter(contextProvider.get());
  }

  public static DatabaseModule_ProvideDBAdapterFactory create(Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideDBAdapterFactory(contextProvider);
  }

  public static DBAdapter provideDBAdapter(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDBAdapter(context));
  }
}
