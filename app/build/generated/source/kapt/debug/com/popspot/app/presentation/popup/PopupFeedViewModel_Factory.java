package com.popspot.app.presentation.popup;

import com.popspot.app.domain.usecase.GetLatestPopupPostsUseCase;
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
public final class PopupFeedViewModel_Factory implements Factory<PopupFeedViewModel> {
  private final Provider<GetLatestPopupPostsUseCase> getLatestPopupPostsUseCaseProvider;

  public PopupFeedViewModel_Factory(
      Provider<GetLatestPopupPostsUseCase> getLatestPopupPostsUseCaseProvider) {
    this.getLatestPopupPostsUseCaseProvider = getLatestPopupPostsUseCaseProvider;
  }

  @Override
  public PopupFeedViewModel get() {
    return newInstance(getLatestPopupPostsUseCaseProvider.get());
  }

  public static PopupFeedViewModel_Factory create(
      Provider<GetLatestPopupPostsUseCase> getLatestPopupPostsUseCaseProvider) {
    return new PopupFeedViewModel_Factory(getLatestPopupPostsUseCaseProvider);
  }

  public static PopupFeedViewModel newInstance(
      GetLatestPopupPostsUseCase getLatestPopupPostsUseCase) {
    return new PopupFeedViewModel(getLatestPopupPostsUseCase);
  }
}
