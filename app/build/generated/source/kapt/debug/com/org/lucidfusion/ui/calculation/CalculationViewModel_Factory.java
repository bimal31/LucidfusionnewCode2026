package com.org.lucidfusion.ui.calculation;

import com.org.lucidfusion.domain.repository.FusionRepository;
import com.org.lucidfusion.domain.usecase.CalculateFusionUseCase;
import com.org.lucidfusion.domain.usecase.GetMachineModelsUseCase;
import com.org.lucidfusion.domain.usecase.GetPipeGradesUseCase;
import com.org.lucidfusion.domain.usecase.GetPipeSizesUseCase;
import com.org.lucidfusion.domain.usecase.GetPressureRatingsUseCase;
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
public final class CalculationViewModel_Factory implements Factory<CalculationViewModel> {
  private final Provider<GetMachineModelsUseCase> getMachineModelsUseCaseProvider;

  private final Provider<GetPipeSizesUseCase> getPipeSizesUseCaseProvider;

  private final Provider<GetPipeGradesUseCase> getPipeGradesUseCaseProvider;

  private final Provider<GetPressureRatingsUseCase> getPressureRatingsUseCaseProvider;

  private final Provider<CalculateFusionUseCase> calculateFusionUseCaseProvider;

  private final Provider<FusionRepository> fusionRepositoryProvider;

  public CalculationViewModel_Factory(
      Provider<GetMachineModelsUseCase> getMachineModelsUseCaseProvider,
      Provider<GetPipeSizesUseCase> getPipeSizesUseCaseProvider,
      Provider<GetPipeGradesUseCase> getPipeGradesUseCaseProvider,
      Provider<GetPressureRatingsUseCase> getPressureRatingsUseCaseProvider,
      Provider<CalculateFusionUseCase> calculateFusionUseCaseProvider,
      Provider<FusionRepository> fusionRepositoryProvider) {
    this.getMachineModelsUseCaseProvider = getMachineModelsUseCaseProvider;
    this.getPipeSizesUseCaseProvider = getPipeSizesUseCaseProvider;
    this.getPipeGradesUseCaseProvider = getPipeGradesUseCaseProvider;
    this.getPressureRatingsUseCaseProvider = getPressureRatingsUseCaseProvider;
    this.calculateFusionUseCaseProvider = calculateFusionUseCaseProvider;
    this.fusionRepositoryProvider = fusionRepositoryProvider;
  }

  @Override
  public CalculationViewModel get() {
    return newInstance(getMachineModelsUseCaseProvider.get(), getPipeSizesUseCaseProvider.get(), getPipeGradesUseCaseProvider.get(), getPressureRatingsUseCaseProvider.get(), calculateFusionUseCaseProvider.get(), fusionRepositoryProvider.get());
  }

  public static CalculationViewModel_Factory create(
      Provider<GetMachineModelsUseCase> getMachineModelsUseCaseProvider,
      Provider<GetPipeSizesUseCase> getPipeSizesUseCaseProvider,
      Provider<GetPipeGradesUseCase> getPipeGradesUseCaseProvider,
      Provider<GetPressureRatingsUseCase> getPressureRatingsUseCaseProvider,
      Provider<CalculateFusionUseCase> calculateFusionUseCaseProvider,
      Provider<FusionRepository> fusionRepositoryProvider) {
    return new CalculationViewModel_Factory(getMachineModelsUseCaseProvider, getPipeSizesUseCaseProvider, getPipeGradesUseCaseProvider, getPressureRatingsUseCaseProvider, calculateFusionUseCaseProvider, fusionRepositoryProvider);
  }

  public static CalculationViewModel newInstance(GetMachineModelsUseCase getMachineModelsUseCase,
      GetPipeSizesUseCase getPipeSizesUseCase, GetPipeGradesUseCase getPipeGradesUseCase,
      GetPressureRatingsUseCase getPressureRatingsUseCase,
      CalculateFusionUseCase calculateFusionUseCase, FusionRepository fusionRepository) {
    return new CalculationViewModel(getMachineModelsUseCase, getPipeSizesUseCase, getPipeGradesUseCase, getPressureRatingsUseCase, calculateFusionUseCase, fusionRepository);
  }
}
