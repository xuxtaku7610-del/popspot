package com.popspot.app.presentation.home;

import com.popspot.app.domain.usecase.GetEventsUseCase;
import com.popspot.app.domain.usecase.GetPopupStoresUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetPopupStoresUseCase> getPopupStoresUseCaseProvider;

  private final Provider<GetEventsUseCase> getEventsUseCaseProvider;

  public HomeViewModel_Factory(Provider<GetPopupStoresUseCase> getPopupStoresUseCaseProvider,
      Provider<GetEventsUseCase> getEventsUseCaseProvider) {
    this.getPopupStoresUseCaseProvider = getPopupStoresUseCaseProvider;
    this.getEventsUseCaseProvider = getEventsUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getPopupStoresUseCaseProvider.get(), getEventsUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetPopupStoresUseCase> getPopupStoresUseCaseProvider,
      Provider<GetEventsUseCase> getEventsUseCaseProvider) {
    return new HomeViewModel_Factory(getPopupStoresUseCaseProvider, getEventsUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetPopupStoresUseCase getPopupStoresUseCase,
      GetEventsUseCase getEventsUseCase) {
    return new HomeViewModel(getPopupStoresUseCase, getEventsUseCase);
  }
}
