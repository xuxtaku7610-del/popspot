package com.popspot.app.domain.usecase;

import com.popspot.app.domain.repository.PublicDataRepository;
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
public final class GetEventsUseCase_Factory implements Factory<GetEventsUseCase> {
  private final Provider<PublicDataRepository> repositoryProvider;

  public GetEventsUseCase_Factory(Provider<PublicDataRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetEventsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetEventsUseCase_Factory create(Provider<PublicDataRepository> repositoryProvider) {
    return new GetEventsUseCase_Factory(repositoryProvider);
  }

  public static GetEventsUseCase newInstance(PublicDataRepository repository) {
    return new GetEventsUseCase(repository);
  }
}
