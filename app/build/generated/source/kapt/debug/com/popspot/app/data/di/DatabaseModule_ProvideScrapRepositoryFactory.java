package com.popspot.app.data.di;

import com.popspot.app.data.repository.ScrapRepositoryImpl;
import com.popspot.app.domain.repository.ScrapRepository;
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
public final class DatabaseModule_ProvideScrapRepositoryFactory implements Factory<ScrapRepository> {
  private final Provider<ScrapRepositoryImpl> implProvider;

  public DatabaseModule_ProvideScrapRepositoryFactory(Provider<ScrapRepositoryImpl> implProvider) {
    this.implProvider = implProvider;
  }

  @Override
  public ScrapRepository get() {
    return provideScrapRepository(implProvider.get());
  }

  public static DatabaseModule_ProvideScrapRepositoryFactory create(
      Provider<ScrapRepositoryImpl> implProvider) {
    return new DatabaseModule_ProvideScrapRepositoryFactory(implProvider);
  }

  public static ScrapRepository provideScrapRepository(ScrapRepositoryImpl impl) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideScrapRepository(impl));
  }
}
