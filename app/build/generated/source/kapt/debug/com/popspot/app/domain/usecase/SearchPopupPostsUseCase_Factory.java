package com.popspot.app.domain.usecase;

import com.popspot.app.domain.repository.PopupRepository;
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
public final class SearchPopupPostsUseCase_Factory implements Factory<SearchPopupPostsUseCase> {
  private final Provider<PopupRepository> popupRepositoryProvider;

  public SearchPopupPostsUseCase_Factory(Provider<PopupRepository> popupRepositoryProvider) {
    this.popupRepositoryProvider = popupRepositoryProvider;
  }

  @Override
  public SearchPopupPostsUseCase get() {
    return newInstance(popupRepositoryProvider.get());
  }

  public static SearchPopupPostsUseCase_Factory create(
      Provider<PopupRepository> popupRepositoryProvider) {
    return new SearchPopupPostsUseCase_Factory(popupRepositoryProvider);
  }

  public static SearchPopupPostsUseCase newInstance(PopupRepository popupRepository) {
    return new SearchPopupPostsUseCase(popupRepository);
  }
}
