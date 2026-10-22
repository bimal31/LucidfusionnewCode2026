package com.org.lucidfusion.fragment;

import com.org.lucidfusion.domain.usecase.SaveReportUseCase;
import com.org.lucidfusion.domain.usecase.UploadReportUseCase;
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
public final class FragmentNewReport_MembersInjector implements MembersInjector<FragmentNewReport> {
  private final Provider<SaveReportUseCase> saveReportUseCaseProvider;

  private final Provider<UploadReportUseCase> uploadReportUseCaseProvider;

  public FragmentNewReport_MembersInjector(Provider<SaveReportUseCase> saveReportUseCaseProvider,
      Provider<UploadReportUseCase> uploadReportUseCaseProvider) {
    this.saveReportUseCaseProvider = saveReportUseCaseProvider;
    this.uploadReportUseCaseProvider = uploadReportUseCaseProvider;
  }

  public static MembersInjector<FragmentNewReport> create(
      Provider<SaveReportUseCase> saveReportUseCaseProvider,
      Provider<UploadReportUseCase> uploadReportUseCaseProvider) {
    return new FragmentNewReport_MembersInjector(saveReportUseCaseProvider, uploadReportUseCaseProvider);
  }

  @Override
  public void injectMembers(FragmentNewReport instance) {
    injectSaveReportUseCase(instance, saveReportUseCaseProvider.get());
    injectUploadReportUseCase(instance, uploadReportUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.org.lucidfusion.fragment.FragmentNewReport.saveReportUseCase")
  public static void injectSaveReportUseCase(FragmentNewReport instance,
      SaveReportUseCase saveReportUseCase) {
    instance.saveReportUseCase = saveReportUseCase;
  }

  @InjectedFieldSignature("com.org.lucidfusion.fragment.FragmentNewReport.uploadReportUseCase")
  public static void injectUploadReportUseCase(FragmentNewReport instance,
      UploadReportUseCase uploadReportUseCase) {
    instance.uploadReportUseCase = uploadReportUseCase;
  }
}
