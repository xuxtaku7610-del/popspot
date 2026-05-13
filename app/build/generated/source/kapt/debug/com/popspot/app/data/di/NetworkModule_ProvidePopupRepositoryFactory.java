package com.popspot.app.data.di;

import com.popspot.app.data.repository.PopupRepositoryImpl;
import com.popspot.app.domain.repository.PopupRepository;
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
public final class NetworkModule_ProvidePopupRepositoryFactory implements Factory<PopupRepository> {
  private final Provider<PopupRepositoryImpl> implProvider;

  public NetworkModule_ProvidePopupRepositoryFactory(Provider<PopupRepositoryImpl> implProvider) {
    this.implProvider = implProvider;
  }

  @Override
  public PopupRepository get() {
    return providePopupRepository(implProvider.get());
  }

  public static NetworkModule_ProvidePopupRepositoryFactory create(
      Provider<PopupRepositoryImpl> implProvider) {
    return new NetworkModule_ProvidePopupRepositoryFactory(implProvider);
  }

  public static PopupRepository providePopupRepository(PopupRepositoryImpl impl) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providePopupRepository(impl));
  }
}
