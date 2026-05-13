package com.popspot.app.presentation.event;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\u0008\u0008\n\u0002\u0010\u0002\n\u0002\u0008\u0005\u0008\u0007\u0012\u0001\u0000\u0018\u0000B\u000F\u0008\u0007\u0012\u0004\u0010\u0002(\u0001\u00A2\u0006\u0004\u0008\u0004\u0010\u0005J\n\u0010\u00192\u0004\u0010\u001B(\u00088\u000FJ\n\u0010\u001C2\u0004\u0010\u001D(\u000B8\u000FJ\u0006\u0010\u001E8\u000FH\u0002R\u000C\u0010\u0002H\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\u0006H\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\tH\u0004\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u000B\u0010\u000CR\u000C\u0010\rH\u0007X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\u0010H\tX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u0012H\n\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u0013\u0010\u000CR\u000C\u0010\u0014H\u000CX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u0015H\r\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u0016\u0010\u000CR\u000F\u0010\u0017H\u000E\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u0018\u0010\u000C\u00F2\u0001f\n\u00020\u0001\n\u00020\u0003\n\u00020\u0008\n\u0006\u0012\u0002\u0018\u00020\u0007\n\u0006\u0012\u0002\u0018\u00020\n\n\u00020\u000F\n\u0006\u0012\u0002\u0018\u00050\u000E\n\u0006\u0012\u0002\u0018\u00060\u0007\n\u00020\u0011\n\u0006\u0012\u0002\u0018\u00080\u0007\n\u0006\u0012\u0002\u0018\u00080\n\n\u0004\u0018\u00010\u0011\n\u0006\u0012\u0002\u0018\u000B0\u0007\n\u0006\u0012\u0002\u0018\u000B0\n\n\u0006\u0012\u0002\u0018\u00060\n\n\u00020\u001A\u00A8\u0006\u001F"}, d2 = {"Lcom/popspot/app/presentation/event/EventListViewModel;", "Landroidx/lifecycle/ViewModel;", "getEventsUseCase", "Lcom/popspot/app/domain/usecase/GetEventsUseCase;", "<init>", "(Lcom/popspot/app/domain/usecase/GetEventsUseCase;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/popspot/app/presentation/event/EventListUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "_allEvents", "", "Lcom/popspot/app/domain/model/Event;", "_selectedCity", "", "selectedCity", "getSelectedCity", "_selectedDate", "selectedDate", "getSelectedDate", "events", "getEvents", "filterByCity", "", "city", "filterByDate", "date", "loadEvents", "app_debug"}, xs= "", pn = "", xi = 48)
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class EventListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.domain.usecase.GetEventsUseCase getEventsUseCase = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.popspot.app.presentation.event.EventListUiState> _uiState = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.popspot.app.presentation.event.EventListUiState> uiState = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.popspot.app.domain.model.Event>> _allEvents = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedCity = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedCity = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedDate = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedDate = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.popspot.app.domain.model.Event>> events = null;

    @javax.inject.Inject()
    public EventListViewModel(@org.jetbrains.annotations.NotNull() com.popspot.app.domain.usecase.GetEventsUseCase getEventsUseCase) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.popspot.app.presentation.event.EventListUiState> getUiState() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedCity() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedDate() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.popspot.app.domain.model.Event>> getEvents() {
        return null;
    }

    public final void filterByCity(@org.jetbrains.annotations.NotNull() java.lang.String city) {
    }

    public final void filterByDate(@org.jetbrains.annotations.Nullable() java.lang.String date) {
    }

    private final void loadEvents() {
    }
}
