package com.popspot.app.data.di;

import com.popspot.app.data.repository.MapRepositoryImpl;
import com.popspot.app.domain.repository.MapRepository;
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
public final class NetworkModule_ProvideMapRepositoryFactory implements Factory<MapRepository> {
  private final Provider<MapRepositoryImpl> implProvider;

  public NetworkModule_ProvideMapRepositoryFactory(Provider<MapRepositoryImpl> implProvider) {
    this.implProvider = implProvider;
  }

  @Override
  public MapRepository get() {
    return provideMapRepository(implProvider.get());
  }

  public static NetworkModule_ProvideMapRepositoryFactory create(
      Provider<MapRepositoryImpl> implProvider) {
    return new NetworkModule_ProvideMapRepositoryFactory(implProvider);
  }

  public static MapRepository provideMapRepository(MapRepositoryImpl impl) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideMapRepository(impl));
  }
}
