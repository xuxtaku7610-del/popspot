package com.popspot.app.data.repository;

import com.popspot.app.data.local.ScrapDao;
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
public final class ScrapRepositoryImpl_Factory implements Factory<ScrapRepositoryImpl> {
  private final Provider<ScrapDao> scrapDaoProvider;

  public ScrapRepositoryImpl_Factory(Provider<ScrapDao> scrapDaoProvider) {
    this.scrapDaoProvider = scrapDaoProvider;
  }

  @Override
  public ScrapRepositoryImpl get() {
    return newInstance(scrapDaoProvider.get());
  }

  public static ScrapRepositoryImpl_Factory create(Provider<ScrapDao> scrapDaoProvider) {
    return new ScrapRepositoryImpl_Factory(scrapDaoProvider);
  }

  public static ScrapRepositoryImpl newInstance(ScrapDao scrapDao) {
    return new ScrapRepositoryImpl(scrapDao);
  }
}
