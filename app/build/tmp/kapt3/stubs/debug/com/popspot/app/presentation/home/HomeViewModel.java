package com.popspot.app.presentation.home;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0002\n\u0000\u0008\u0007\u0012\u0001\u0000\u0018\u0000B\u0015\u0008\u0007\u0012\u0004\u0010\u0002(\u0001\u0012\u0004\u0010\u0004(\u0002\u00A2\u0006\u0004\u0008\u0006\u0010\u0007J\u0006\u0010\u000F8\u0006H\u0002R\u000C\u0010\u0002H\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\u0004H\u0002X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\u0008H\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u000BH\u0005\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\r\u0010\u000E\u00F2\u0001$\n\u00020\u0001\n\u00020\u0003\n\u00020\u0005\n\u00020\n\n\u0006\u0012\u0002\u0018\u00030\t\n\u0006\u0012\u0002\u0018\u00030\u000C\n\u00020\u0010\u00A8\u0006\u0011"}, d2 = {"Lcom/popspot/app/presentation/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "getPopupStoresUseCase", "Lcom/popspot/app/domain/usecase/GetPopupStoresUseCase;", "getEventsUseCase", "Lcom/popspot/app/domain/usecase/GetEventsUseCase;", "<init>", "(Lcom/popspot/app/domain/usecase/GetPopupStoresUseCase;Lcom/popspot/app/domain/usecase/GetEventsUseCase;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/popspot/app/presentation/home/HomeUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadData", "", "app_debug"}, xs= "", pn = "", xi = 48)
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.domain.usecase.GetPopupStoresUseCase getPopupStoresUseCase = null;

    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.domain.usecase.GetEventsUseCase getEventsUseCase = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.popspot.app.presentation.home.HomeUiState> _uiState = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.popspot.app.presentation.home.HomeUiState> uiState = null;

    @javax.inject.Inject()
    public HomeViewModel(@org.jetbrains.annotations.NotNull() com.popspot.app.domain.usecase.GetPopupStoresUseCase getPopupStoresUseCase, @org.jetbrains.annotations.NotNull() com.popspot.app.domain.usecase.GetEventsUseCase getEventsUseCase) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.popspot.app.presentation.home.HomeUiState> getUiState() {
        return null;
    }

    private final void loadData() {
    }
}
