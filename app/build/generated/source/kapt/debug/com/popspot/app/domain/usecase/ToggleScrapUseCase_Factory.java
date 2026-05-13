package com.popspot.app.domain.usecase;

import com.popspot.app.domain.repository.ScrapRepository;
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
public final class ToggleScrapUseCase_Factory implements Factory<ToggleScrapUseCase> {
  private final Provider<ScrapRepository> scrapRepositoryProvider;

  public ToggleScrapUseCase_Factory(Provider<ScrapRepository> scrapRepositoryProvider) {
    this.scrapRepositoryProvider = scrapRepositoryProvider;
  }

  @Override
  public ToggleScrapUseCase get() {
    return newInstance(scrapRepositoryProvider.get());
  }

  public static ToggleScrapUseCase_Factory create(
      Provider<ScrapRepository> scrapRepositoryProvider) {
    return new ToggleScrapUseCase_Factory(scrapRepositoryProvider);
  }

  public static ToggleScrapUseCase newInstance(ScrapRepository scrapRepository) {
    return new ToggleScrapUseCase(scrapRepository);
  }
}
