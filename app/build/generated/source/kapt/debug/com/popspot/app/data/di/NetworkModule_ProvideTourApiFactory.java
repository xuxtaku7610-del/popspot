package com.popspot.app.data.di;

import com.popspot.app.data.remote.api.TourApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
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
public final class NetworkModule_ProvideTourApiFactory implements Factory<TourApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideTourApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public TourApi get() {
    return provideTourApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideTourApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideTourApiFactory(retrofitProvider);
  }

  public static TourApi provideTourApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTourApi(retrofit));
  }
}
