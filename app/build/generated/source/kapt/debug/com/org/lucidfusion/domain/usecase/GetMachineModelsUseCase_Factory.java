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
public final class GetMachineModelsUseCase_Factory implements Factory<GetMachineModelsUseCase> {
  private final Provider<FusionRepository> repositoryProvider;

  public GetMachineModelsUseCase_Factory(Provider<FusionRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetMachineModelsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetMachineModelsUseCase_Factory create(
      Provider<FusionRepository> repositoryProvider) {
    return new GetMachineModelsUseCase_Factory(repositoryProvider);
  }

  public static GetMachineModelsUseCase newInstance(FusionRepository repository) {
    return new GetMachineModelsUseCase(repository);
  }
}
