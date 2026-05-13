package com.popspot.app.data.di;

import com.popspot.app.data.repository.TourRepositoryImpl;
import com.popspot.app.domain.repository.TourRepository;
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
public final class NetworkModule_ProvideTourRepositoryFactory implements Factory<TourRepository> {
  private final Provider<TourRepositoryImpl> implProvider;

  public NetworkModule_ProvideTourRepositoryFactory(Provider<TourRepositoryImpl> implProvider) {
    this.implProvider = implProvider;
  }

  @Override
  public TourRepository get() {
    return provideTourRepository(implProvider.get());
  }

  public static NetworkModule_ProvideTourRepositoryFactory create(
      Provider<TourRepositoryImpl> implProvider) {
    return new NetworkModule_ProvideTourRepositoryFactory(implProvider);
  }

  public static TourRepository provideTourRepository(TourRepositoryImpl impl) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTourRepository(impl));
  }
}
