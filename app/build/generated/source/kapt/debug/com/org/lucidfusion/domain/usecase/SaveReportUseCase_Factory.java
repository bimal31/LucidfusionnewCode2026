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
public final class SaveReportUseCase_Factory implements Factory<SaveReportUseCase> {
  private final Provider<ReportRepository> reportRepositoryProvider;

  public SaveReportUseCase_Factory(Provider<ReportRepository> reportRepositoryProvider) {
    this.reportRepositoryProvider = reportRepositoryProvider;
  }

  @Override
  public SaveReportUseCase get() {
    return newInstance(reportRepositoryProvider.get());
  }

  public static SaveReportUseCase_Factory create(
      Provider<ReportRepository> reportRepositoryProvider) {
    return new SaveReportUseCase_Factory(reportRepositoryProvider);
  }

  public static SaveReportUseCase newInstance(ReportRepository reportRepository) {
    return new SaveReportUseCase(reportRepository);
  }
}
