package com.popspot.app.data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
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
public final class NetworkModule_ProvideTourRetrofitFactory implements Factory<Retrofit> {
  private final Provider<OkHttpClient> clientProvider;

  public NetworkModule_ProvideTourRetrofitFactory(Provider<OkHttpClient> clientProvider) {
    this.clientProvider = clientProvider;
  }

  @Override
  public Retrofit get() {
    return provideTourRetrofit(clientProvider.get());
  }

  public static NetworkModule_ProvideTourRetrofitFactory create(
      Provider<OkHttpClient> clientProvider) {
    return new NetworkModule_ProvideTourRetrofitFactory(clientProvider);
  }

  public static Retrofit provideTourRetrofit(OkHttpClient client) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTourRetrofit(client));
  }
}
