package com.popspot.app.data.repository;

import com.popspot.app.data.remote.api.TourApi;
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
public final class TourRepositoryImpl_Factory implements Factory<TourRepositoryImpl> {
  private final Provider<TourApi> tourApiProvider;

  public TourRepositoryImpl_Factory(Provider<TourApi> tourApiProvider) {
    this.tourApiProvider = tourApiProvider;
  }

  @Override
  public TourRepositoryImpl get() {
    return newInstance(tourApiProvider.get());
  }

  public static TourRepositoryImpl_Factory create(Provider<TourApi> tourApiProvider) {
    return new TourRepositoryImpl_Factory(tourApiProvider);
  }

  public static TourRepositoryImpl newInstance(TourApi tourApi) {
    return new TourRepositoryImpl(tourApi);
  }
}
