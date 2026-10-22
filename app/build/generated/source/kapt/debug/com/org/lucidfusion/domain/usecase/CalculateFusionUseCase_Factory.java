package com.org.lucidfusion.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class CalculateFusionUseCase_Factory implements Factory<CalculateFusionUseCase> {
  @Override
  public CalculateFusionUseCase get() {
    return newInstance();
  }

  public static CalculateFusionUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CalculateFusionUseCase newInstance() {
    return new CalculateFusionUseCase();
  }

  private static final class InstanceHolder {
    private static final CalculateFusionUseCase_Factory INSTANCE = new CalculateFusionUseCase_Factory();
  }
}
