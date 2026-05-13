package com.popspot.app.data.di;

import com.popspot.app.data.repository.PublicDataRepositoryImpl;
import com.popspot.app.domain.repository.PublicDataRepository;
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
public final class NetworkModule_ProvidePublicDataRepositoryFactory implements Factory<PublicDataRepository> {
  private final Provider<PublicDataRepositoryImpl> implProvider;

  public NetworkModule_ProvidePublicDataRepositoryFactory(
      Provider<PublicDataRepositoryImpl> implProvider) {
    this.implProvider = implProvider;
  }

  @Override
  public PublicDataRepository get() {
    return providePublicDataRepository(implProvider.get());
  }

  public static NetworkModule_ProvidePublicDataRepositoryFactory create(
      Provider<PublicDataRepositoryImpl> implProvider) {
    return new NetworkModule_ProvidePublicDataRepositoryFactory(implProvider);
  }

  public static PublicDataRepository providePublicDataRepository(PublicDataRepositoryImpl impl) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providePublicDataRepository(impl));
  }
}
