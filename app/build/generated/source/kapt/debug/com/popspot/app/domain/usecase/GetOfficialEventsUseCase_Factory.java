package com.popspot.app.domain.usecase;

import com.popspot.app.domain.repository.TourRepository;
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
public final class GetOfficialEventsUseCase_Factory implements Factory<GetOfficialEventsUseCase> {
  private final Provider<TourRepository> tourRepositoryProvider;

  public GetOfficialEventsUseCase_Factory(Provider<TourRepository> tourRepositoryProvider) {
    this.tourRepositoryProvider = tourRepositoryProvider;
  }

  @Override
  public GetOfficialEventsUseCase get() {
    return newInstance(tourRepositoryProvider.get());
  }

  public static GetOfficialEventsUseCase_Factory create(
      Provider<TourRepository> tourRepositoryProvider) {
    return new GetOfficialEventsUseCase_Factory(tourRepositoryProvider);
  }

  public static GetOfficialEventsUseCase newInstance(TourRepository tourRepository) {
    return new GetOfficialEventsUseCase(tourRepository);
  }
}
