package com.popspot.app.presentation.event;

import com.popspot.app.domain.usecase.GetEventsUseCase;
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
public final class EventListViewModel_Factory implements Factory<EventListViewModel> {
  private final Provider<GetEventsUseCase> getEventsUseCaseProvider;

  public EventListViewModel_Factory(Provider<GetEventsUseCase> getEventsUseCaseProvider) {
    this.getEventsUseCaseProvider = getEventsUseCaseProvider;
  }

  @Override
  public EventListViewModel get() {
    return newInstance(getEventsUseCaseProvider.get());
  }

  public static EventListViewModel_Factory create(
      Provider<GetEventsUseCase> getEventsUseCaseProvider) {
    return new EventListViewModel_Factory(getEventsUseCaseProvider);
  }

  public static EventListViewModel newInstance(GetEventsUseCase getEventsUseCase) {
    return new EventListViewModel(getEventsUseCase);
  }
}
