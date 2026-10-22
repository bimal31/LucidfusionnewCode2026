package com.org.lucidfusion.di;

import com.org.lucidfusion.data.api.WeldReportApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideWeldReportApiServiceFactory implements Factory<WeldReportApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideWeldReportApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public WeldReportApiService get() {
    return provideWeldReportApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideWeldReportApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideWeldReportApiServiceFactory(retrofitProvider);
  }

  public static WeldReportApiService provideWeldReportApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideWeldReportApiService(retrofit));
  }
}
