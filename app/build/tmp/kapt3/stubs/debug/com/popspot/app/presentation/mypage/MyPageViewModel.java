package com.popspot.app.presentation.mypage;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0002\n\u0000\u0008\u0007\u0012\u0001\u0000\u0018\u0000B\u000F\u0008\u0007\u0012\u0004\u0010\u0002(\u0001\u00A2\u0006\u0004\u0008\u0004\u0010\u0005J\u0004\u0010\u00108\u0008R\u000C\u0010\u0002H\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\u0006H\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\tH\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u000CH\u0007\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u000E\u0010\u000F\u00F2\u00010\n\u00020\u0001\n\u00020\u0003\n\u00020\u0008\n\u0006\u0012\u0002\u0018\u00020\u0007\n\u00020\u000B\n\u0006\u0012\u0002\u0018\u00040\n\n\u00020\r\n\u0006\u0012\u0002\u0018\u00060\n\n\u00020\u0011\u00A8\u0006\u0012"}, d2 = {"Lcom/popspot/app/presentation/mypage/MyPageViewModel;", "Landroidx/lifecycle/ViewModel;", "scrapRepository", "Lcom/popspot/app/domain/repository/ScrapRepository;", "<init>", "(Lcom/popspot/app/domain/repository/ScrapRepository;)V", "_notificationEnabled", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_scrapCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "uiState", "Lcom/popspot/app/presentation/mypage/MyPageUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "toggleNotification", "", "app_debug"}, xs= "", pn = "", xi = 48)
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MyPageViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.domain.repository.ScrapRepository scrapRepository = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _notificationEnabled = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> _scrapCount = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.popspot.app.presentation.mypage.MyPageUiState> uiState = null;

    @javax.inject.Inject()
    public MyPageViewModel(@org.jetbrains.annotations.NotNull() com.popspot.app.domain.repository.ScrapRepository scrapRepository) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.popspot.app.presentation.mypage.MyPageUiState> getUiState() {
        return null;
    }

    public final void toggleNotification() {
    }
}
