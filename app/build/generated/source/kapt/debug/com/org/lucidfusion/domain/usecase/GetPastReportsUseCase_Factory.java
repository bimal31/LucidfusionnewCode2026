package com.org.lucidfusion.domain.usecase;

import com.org.lucidfusion.domain.repository.ReportRepository;
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
public final class GetPastReportsUseCase_Factory implements Factory<GetPastReportsUseCase> {
  private final Provider<ReportRepository> reportRepositoryProvider;

  public GetPastReportsUseCase_Factory(Provider<ReportRepository> reportRepositoryProvider) {
    this.reportRepositoryProvider = reportRepositoryProvider;
  }

  @Override
  public GetPastReportsUseCase get() {
    return newInstance(reportRepositoryProvider.get());
  }

  public static GetPastReportsUseCase_Factory create(
      Provider<ReportRepository> reportRepositoryProvider) {
    return new GetPastReportsUseCase_Factory(reportRepositoryProvider);
  }

  public static GetPastReportsUseCase newInstance(ReportRepository reportRepository) {
    return new GetPastReportsUseCase(reportRepository);
  }
}
