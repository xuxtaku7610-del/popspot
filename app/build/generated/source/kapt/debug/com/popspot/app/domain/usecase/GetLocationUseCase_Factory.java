package com.popspot.app.domain.usecase;

import com.popspot.app.domain.repository.MapRepository;
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
public final class GetLocationUseCase_Factory implements Factory<GetLocationUseCase> {
  private final Provider<MapRepository> repositoryProvider;

  public GetLocationUseCase_Factory(Provider<MapRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetLocationUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetLocationUseCase_Factory create(Provider<MapRepository> repositoryProvider) {
    return new GetLocationUseCase_Factory(repositoryProvider);
  }

  public static GetLocationUseCase newInstance(MapRepository repository) {
    return new GetLocationUseCase(repository);
  }
}
