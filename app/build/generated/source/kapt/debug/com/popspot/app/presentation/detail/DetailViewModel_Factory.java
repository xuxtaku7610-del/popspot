package com.popspot.app.presentation.detail;

import androidx.lifecycle.SavedStateHandle;
import com.popspot.app.domain.repository.ScrapRepository;
import com.popspot.app.domain.usecase.GetPopupStoresUseCase;
import com.popspot.app.domain.usecase.ToggleScrapUseCase;
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
public final class DetailViewModel_Factory implements Factory<DetailViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetPopupStoresUseCase> getPopupStoresUseCaseProvider;

  private final Provider<ScrapRepository> scrapRepositoryProvider;

  private final Provider<ToggleScrapUseCase> toggleScrapUseCaseProvider;

  public DetailViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetPopupStoresUseCase> getPopupStoresUseCaseProvider,
      Provider<ScrapRepository> scrapRepositoryProvider,
      Provider<ToggleScrapUseCase> toggleScrapUseCaseProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getPopupStoresUseCaseProvider = getPopupStoresUseCaseProvider;
    this.scrapRepositoryProvider = scrapRepositoryProvider;
    this.toggleScrapUseCaseProvider = toggleScrapUseCaseProvider;
  }

  @Override
  public DetailViewModel get() {
    return newInstance(savedStateHandleProvider.get(), getPopupStoresUseCaseProvider.get(), scrapRepositoryProvider.get(), toggleScrapUseCaseProvider.get());
  }

  public static DetailViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetPopupStoresUseCase> getPopupStoresUseCaseProvider,
      Provider<ScrapRepository> scrapRepositoryProvider,
      Provider<ToggleScrapUseCase> toggleScrapUseCaseProvider) {
    return new DetailViewModel_Factory(savedStateHandleProvider, getPopupStoresUseCaseProvider, scrapRepositoryProvider, toggleScrapUseCaseProvider);
  }

  public static DetailViewModel newInstance(SavedStateHandle savedStateHandle,
      GetPopupStoresUseCase getPopupStoresUseCase, ScrapRepository scrapRepository,
      ToggleScrapUseCase toggleScrapUseCase) {
    return new DetailViewModel(savedStateHandle, getPopupStoresUseCase, scrapRepository, toggleScrapUseCase);
  }
}
