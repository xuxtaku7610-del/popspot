package com.popspot.app.data.di;

import com.popspot.app.data.remote.api.NaverBlogApi;
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
public final class NetworkModule_ProvideNaverBlogApiFactory implements Factory<NaverBlogApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideNaverBlogApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public NaverBlogApi get() {
    return provideNaverBlogApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideNaverBlogApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideNaverBlogApiFactory(retrofitProvider);
  }

  public static NaverBlogApi provideNaverBlogApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideNaverBlogApi(retrofit));
  }
}
