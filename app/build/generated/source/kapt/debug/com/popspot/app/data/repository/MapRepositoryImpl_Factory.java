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
public final class MapRepositoryImpl_Factory implements Factory<MapRepositoryImpl> {
  @Override
  public MapRepositoryImpl get() {
    return newInstance();
  }

  public static MapRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MapRepositoryImpl newInstance() {
    return new MapRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final MapRepositoryImpl_Factory INSTANCE = new MapRepositoryImpl_Factory();
  }
}
