package com.popspot.app.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class PublicDataRepositoryImpl_Factory implements Factory<PublicDataRepositoryImpl> {
  @Override
  public PublicDataRepositoryImpl get() {
    return newInstance();
  }

  public static PublicDataRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PublicDataRepositoryImpl newInstance() {
    return new PublicDataRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final PublicDataRepositoryImpl_Factory INSTANCE = new PublicDataRepositoryImpl_Factory();
  }
}
