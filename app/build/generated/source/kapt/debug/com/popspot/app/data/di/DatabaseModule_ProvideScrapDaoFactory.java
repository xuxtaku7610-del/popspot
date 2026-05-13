package com.popspot.app.data.di;

import com.popspot.app.data.local.PopSpotDatabase;
import com.popspot.app.data.local.ScrapDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideScrapDaoFactory implements Factory<ScrapDao> {
  private final Provider<PopSpotDatabase> dbProvider;

  public DatabaseModule_ProvideScrapDaoFactory(Provider<PopSpotDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ScrapDao get() {
    return provideScrapDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideScrapDaoFactory create(Provider<PopSpotDatabase> dbProvider) {
    return new DatabaseModule_ProvideScrapDaoFactory(dbProvider);
  }

  public static ScrapDao provideScrapDao(PopSpotDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideScrapDao(db));
  }
}
