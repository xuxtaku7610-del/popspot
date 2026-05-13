package com.popspot.app.presentation.scrap;

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
public final class ScrapViewModel_Factory implements Factory<ScrapViewModel> {
  private final Provider<ScrapRepository> scrapRepositoryProvider;

  public ScrapViewModel_Factory(Provider<ScrapRepository> scrapRepositoryProvider) {
    this.scrapRepositoryProvider = scrapRepositoryProvider;
  }

  @Override
  public ScrapViewModel get() {
    return newInstance(scrapRepositoryProvider.get());
  }

  public static ScrapViewModel_Factory create(Provider<ScrapRepository> scrapRepositoryProvider) {
    return new ScrapViewModel_Factory(scrapRepositoryProvider);
  }

  public static ScrapViewModel newInstance(ScrapRepository scrapRepository) {
    return new ScrapViewModel(scrapRepository);
  }
}
