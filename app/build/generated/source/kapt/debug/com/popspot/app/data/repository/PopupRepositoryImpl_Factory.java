package com.popspot.app.data.repository;

import com.popspot.app.data.remote.api.NaverBlogApi;
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
public final class PopupRepositoryImpl_Factory implements Factory<PopupRepositoryImpl> {
  private final Provider<NaverBlogApi> naverBlogApiProvider;

  public PopupRepositoryImpl_Factory(Provider<NaverBlogApi> naverBlogApiProvider) {
    this.naverBlogApiProvider = naverBlogApiProvider;
  }

  @Override
  public PopupRepositoryImpl get() {
    return newInstance(naverBlogApiProvider.get());
  }

  public static PopupRepositoryImpl_Factory create(Provider<NaverBlogApi> naverBlogApiProvider) {
    return new PopupRepositoryImpl_Factory(naverBlogApiProvider);
  }

  public static PopupRepositoryImpl newInstance(NaverBlogApi naverBlogApi) {
    return new PopupRepositoryImpl(naverBlogApi);
  }
}
