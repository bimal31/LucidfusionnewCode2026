package com.org.lucidfusion.fragment;

import com.org.lucidfusion.domain.usecase.GetPastReportsUseCase;
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
public final class FragmentSendToServer_MembersInjector implements MembersInjector<FragmentSendToServer> {
  private final Provider<GetPastReportsUseCase> getPastReportsUseCaseProvider;

  private final Provider<UploadReportUseCase> uploadReportUseCaseProvider;

  public FragmentSendToServer_MembersInjector(
      Provider<GetPastReportsUseCase> getPastReportsUseCaseProvider,
      Provider<UploadReportUseCase> uploadReportUseCaseProvider) {
    this.getPastReportsUseCaseProvider = getPastReportsUseCaseProvider;
    this.uploadReportUseCaseProvider = uploadReportUseCaseProvider;
  }

  public static MembersInjector<FragmentSendToServer> create(
      Provider<GetPastReportsUseCase> getPastReportsUseCaseProvider,
      Provider<UploadReportUseCase> uploadReportUseCaseProvider) {
    return new FragmentSendToServer_MembersInjector(getPastReportsUseCaseProvider, uploadReportUseCaseProvider);
  }

  @Override
  public void injectMembers(FragmentSendToServer instance) {
    injectGetPastReportsUseCase(instance, getPastReportsUseCaseProvider.get());
    injectUploadReportUseCase(instance, uploadReportUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.org.lucidfusion.fragment.FragmentSendToServer.getPastReportsUseCase")
  public static void injectGetPastReportsUseCase(FragmentSendToServer instance,
      GetPastReportsUseCase getPastReportsUseCase) {
    instance.getPastReportsUseCase = getPastReportsUseCase;
  }

  @InjectedFieldSignature("com.org.lucidfusion.fragment.FragmentSendToServer.uploadReportUseCase")
  public static void injectUploadReportUseCase(FragmentSendToServer instance,
      UploadReportUseCase uploadReportUseCase) {
    instance.uploadReportUseCase = uploadReportUseCase;
  }
}
