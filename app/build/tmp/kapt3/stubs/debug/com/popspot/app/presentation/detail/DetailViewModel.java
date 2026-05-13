package com.popspot.app.presentation.detail;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000B\n\u0002\u0008\u0007\n\u0002\u0010\u0002\n\u0002\u0008\u0003\u0008\u0007\u0012\u0001\u0000\u0018\u0000B!\u0008\u0007\u0012\u0004\u0010\u0002(\u0001\u0012\u0004\u0010\u0004(\u0002\u0012\u0004\u0010\u0006(\u0003\u0012\u0004\u0010\u0008(\u0004\u00A2\u0006\u0004\u0008\n\u0010\u000BJ\n\u0010\u001D2\u0004\u0010\u001F(\u00058\u000FJ\u0004\u0010 8\u000FR\u000C\u0010\u0004H\u0002X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\u0006H\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\u0008H\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\u000CH\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\u000EH\u0007X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u0011H\u0008\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u0013\u0010\u0014R\u000C\u0010\u0015H\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u0017H\u000B\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u0017\u0010\u0014R\u000C\u0010\u0018H\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u0019H\u000B\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u0019\u0010\u0014R\u000C\u0010\u001AH\rX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u001BH\u000E\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u001C\u0010\u0014\u00F2\u0001\\\n\u00020\u0001\n\u00020\u0003\n\u00020\u0005\n\u00020\u0007\n\u00020\t\n\u00020\r\n\u0004\u0018\u00010\u0010\n\u0006\u0012\u0002\u0018\u00060\u000F\n\u0006\u0012\u0002\u0018\u00060\u0012\n\u00020\u0016\n\u0006\u0012\u0002\u0018\t0\u000F\n\u0006\u0012\u0002\u0018\t0\u0012\n\u0004\u0018\u00010\r\n\u0006\u0012\u0002\u0018\u000C0\u000F\n\u0006\u0012\u0002\u0018\u000C0\u0012\n\u00020\u001E\u00A8\u0006!"}, d2 = {"Lcom/popspot/app/presentation/detail/DetailViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "getPopupStoresUseCase", "Lcom/popspot/app/domain/usecase/GetPopupStoresUseCase;", "scrapRepository", "Lcom/popspot/app/domain/repository/ScrapRepository;", "toggleScrapUseCase", "Lcom/popspot/app/domain/usecase/ToggleScrapUseCase;", "<init>", "(Landroidx/lifecycle/SavedStateHandle;Lcom/popspot/app/domain/usecase/GetPopupStoresUseCase;Lcom/popspot/app/domain/repository/ScrapRepository;Lcom/popspot/app/domain/usecase/ToggleScrapUseCase;)V", "storeId", "", "_popupStore", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/popspot/app/domain/model/PopupStore;", "popupStore", "Lkotlinx/coroutines/flow/StateFlow;", "getPopupStore", "()Lkotlinx/coroutines/flow/StateFlow;", "_isScrapped", "", "isScrapped", "_isLoading", "isLoading", "_error", "error", "getError", "loadDetail", "", "id", "toggleScrap", "app_debug"}, xs= "", pn = "", xi = 48)
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.domain.usecase.GetPopupStoresUseCase getPopupStoresUseCase = null;

    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.domain.repository.ScrapRepository scrapRepository = null;

    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.domain.usecase.ToggleScrapUseCase toggleScrapUseCase = null;

    @org.jetbrains.annotations.NotNull()
    private final java.lang.String storeId = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.popspot.app.domain.model.PopupStore> _popupStore = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.popspot.app.domain.model.PopupStore> popupStore = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isScrapped = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isScrapped = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;

    @javax.inject.Inject()
    public DetailViewModel(@org.jetbrains.annotations.NotNull() androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull() com.popspot.app.domain.usecase.GetPopupStoresUseCase getPopupStoresUseCase, @org.jetbrains.annotations.NotNull() com.popspot.app.domain.repository.ScrapRepository scrapRepository, @org.jetbrains.annotations.NotNull() com.popspot.app.domain.usecase.ToggleScrapUseCase toggleScrapUseCase) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.popspot.app.domain.model.PopupStore> getPopupStore() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isScrapped() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getError() {
        return null;
    }

    public final void loadDetail(@org.jetbrains.annotations.NotNull() java.lang.String id) {
    }

    public final void toggleScrap() {
    }
}
