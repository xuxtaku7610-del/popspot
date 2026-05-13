package com.popspot.app.domain.usecase;

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
public final class GetPopupStoresUseCase_Factory implements Factory<GetPopupStoresUseCase> {
  @Override
  public GetPopupStoresUseCase get() {
    return newInstance();
  }

  public static GetPopupStoresUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GetPopupStoresUseCase newInstance() {
    return new GetPopupStoresUseCase();
  }

  private static final class InstanceHolder {
    private static final GetPopupStoresUseCase_Factory INSTANCE = new GetPopupStoresUseCase_Factory();
  }
}
