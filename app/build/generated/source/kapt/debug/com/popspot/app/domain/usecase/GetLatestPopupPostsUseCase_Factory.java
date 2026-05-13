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
public final class GetLatestPopupPostsUseCase_Factory implements Factory<GetLatestPopupPostsUseCase> {
  private final Provider<PopupRepository> popupRepositoryProvider;

  public GetLatestPopupPostsUseCase_Factory(Provider<PopupRepository> popupRepositoryProvider) {
    this.popupRepositoryProvider = popupRepositoryProvider;
  }

  @Override
  public GetLatestPopupPostsUseCase get() {
    return newInstance(popupRepositoryProvider.get());
  }

  public static GetLatestPopupPostsUseCase_Factory create(
      Provider<PopupRepository> popupRepositoryProvider) {
    return new GetLatestPopupPostsUseCase_Factory(popupRepositoryProvider);
  }

  public static GetLatestPopupPostsUseCase newInstance(PopupRepository popupRepository) {
    return new GetLatestPopupPostsUseCase(popupRepository);
  }
}
