package com.org.lucidfusion;

import com.org.lucidfusion.dbhelper.DBQueries;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<DBQueries> injectedDbQueriesProvider;

  public MainActivity_MembersInjector(Provider<DBQueries> injectedDbQueriesProvider) {
    this.injectedDbQueriesProvider = injectedDbQueriesProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<DBQueries> injectedDbQueriesProvider) {
    return new MainActivity_MembersInjector(injectedDbQueriesProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectInjectedDbQueries(instance, injectedDbQueriesProvider.get());
  }

  @InjectedFieldSignature("com.org.lucidfusion.MainActivity.injectedDbQueries")
  public static void injectInjectedDbQueries(MainActivity instance, DBQueries injectedDbQueries) {
    instance.injectedDbQueries = injectedDbQueries;
  }
}
