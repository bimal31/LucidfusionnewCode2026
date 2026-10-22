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
public final class UploadReportUseCase_Factory implements Factory<UploadReportUseCase> {
  private final Provider<ReportRepository> reportRepositoryProvider;

  public UploadReportUseCase_Factory(Provider<ReportRepository> reportRepositoryProvider) {
    this.reportRepositoryProvider = reportRepositoryProvider;
  }

  @Override
  public UploadReportUseCase get() {
    return newInstance(reportRepositoryProvider.get());
  }

  public static UploadReportUseCase_Factory create(
      Provider<ReportRepository> reportRepositoryProvider) {
    return new UploadReportUseCase_Factory(reportRepositoryProvider);
  }

  public static UploadReportUseCase newInstance(ReportRepository reportRepository) {
    return new UploadReportUseCase(reportRepository);
  }
}
