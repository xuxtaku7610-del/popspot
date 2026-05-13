package com.popspot.app.presentation.popup;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000E\n\u0002\u0008\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0005\u0008\u0007\u0012\u0001\u0000\u0018\u0000B\u000F\u0008\u0007\u0012\u0004\u0010\u0002(\u0001\u00A2\u0006\u0004\u0008\u0004\u0010\u0005J\n\u0010\u00182\u0004\u0010\u001A(\u00058\u000BJ\n\u0010\u001B2\u0004\u0010\u001C(\u00058\u000BJ\u0006\u0010\u001D8\u000BH\u0002R\u000C\u0010\u0002H\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\u0006H\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\tH\u0004\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u000B\u0010\u000CR\u000C\u0010\rH\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u000FH\u0007\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u0010\u0010\u000CR\u000C\u0010\u0011H\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u0012H\u0007\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u0013\u0010\u000CR\u000F\u0010\u0014H\n\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u0017\u0010\u000C\u00F2\u0001H\n\u00020\u0001\n\u00020\u0003\n\u00020\u0008\n\u0006\u0012\u0002\u0018\u00020\u0007\n\u0006\u0012\u0002\u0018\u00020\n\n\u00020\u000E\n\u0006\u0012\u0002\u0018\u00050\u0007\n\u0006\u0012\u0002\u0018\u00050\n\n\u00020\u0016\n\u0006\u0012\u0002\u0018\u00080\u0015\n\u0006\u0012\u0002\u0018\t0\n\n\u00020\u0019\u00A8\u0006\u001E"}, d2 = {"Lcom/popspot/app/presentation/popup/PopupFeedViewModel;", "Landroidx/lifecycle/ViewModel;", "getLatestPopupPostsUseCase", "Lcom/popspot/app/domain/usecase/GetLatestPopupPostsUseCase;", "<init>", "(Lcom/popspot/app/domain/usecase/GetLatestPopupPostsUseCase;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/popspot/app/presentation/popup/PopupFeedUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "_searchQuery", "", "searchQuery", "getSearchQuery", "_selectedChip", "selectedChip", "getSelectedChip", "filteredItems", "", "Lcom/popspot/app/domain/model/PopupPost;", "getFilteredItems", "onSearch", "", "query", "onChipSelected", "chip", "loadPosts", "app_debug"}, xs= "", pn = "", xi = 48)
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PopupFeedViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.domain.usecase.GetLatestPopupPostsUseCase getLatestPopupPostsUseCase = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.popspot.app.presentation.popup.PopupFeedUiState> _uiState = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.popspot.app.presentation.popup.PopupFeedUiState> uiState = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> searchQuery = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedChip = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedChip = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.popspot.app.domain.model.PopupPost>> filteredItems = null;

    @javax.inject.Inject()
    public PopupFeedViewModel(@org.jetbrains.annotations.NotNull() com.popspot.app.domain.usecase.GetLatestPopupPostsUseCase getLatestPopupPostsUseCase) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.popspot.app.presentation.popup.PopupFeedUiState> getUiState() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSearchQuery() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedChip() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.popspot.app.domain.model.PopupPost>> getFilteredItems() {
        return null;
    }

    public final void onSearch(@org.jetbrains.annotations.NotNull() java.lang.String query) {
    }

    public final void onChipSelected(@org.jetbrains.annotations.NotNull() java.lang.String chip) {
    }

    private final void loadPosts() {
    }
}
