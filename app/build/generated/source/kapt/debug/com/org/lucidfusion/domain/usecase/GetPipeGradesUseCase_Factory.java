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
public final class GetPipeGradesUseCase_Factory implements Factory<GetPipeGradesUseCase> {
  private final Provider<FusionRepository> repositoryProvider;

  public GetPipeGradesUseCase_Factory(Provider<FusionRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetPipeGradesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetPipeGradesUseCase_Factory create(Provider<FusionRepository> repositoryProvider) {
    return new GetPipeGradesUseCase_Factory(repositoryProvider);
  }

  public static GetPipeGradesUseCase newInstance(FusionRepository repository) {
    return new GetPipeGradesUseCase(repository);
  }
}
