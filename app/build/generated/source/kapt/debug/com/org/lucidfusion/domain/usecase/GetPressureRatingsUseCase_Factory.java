package com.org.lucidfusion.domain.usecase;

import com.org.lucidfusion.domain.repository.FusionRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class GetPressureRatingsUseCase_Factory implements Factory<GetPressureRatingsUseCase> {
  private final Provider<FusionRepository> repositoryProvider;

  public GetPressureRatingsUseCase_Factory(Provider<FusionRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetPressureRatingsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetPressureRatingsUseCase_Factory create(
      Provider<FusionRepository> repositoryProvider) {
    return new GetPressureRatingsUseCase_Factory(repositoryProvider);
  }

  public static GetPressureRatingsUseCase newInstance(FusionRepository repository) {
    return new GetPressureRatingsUseCase(repository);
  }
}
