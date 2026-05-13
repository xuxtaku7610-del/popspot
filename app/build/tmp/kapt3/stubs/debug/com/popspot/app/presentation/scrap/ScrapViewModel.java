package com.popspot.app.presentation.scrap;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0002\n\u0002\u0008\u0004\u0008\u0007\u0012\u0001\u0000\u0018\u0000B\u000F\u0008\u0007\u0012\u0004\u0010\u0002(\u0001\u00A2\u0006\u0004\u0008\u0004\u0010\u0005J\n\u0010\u00122\u0004\u0010\u0014(\u00028\u0008J\n\u0010\u00152\u0004\u0010\u0016(\u00028\u0008R\u000C\u0010\u0002H\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000C\u0010\u0006H\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\tH\u0004\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u000B\u0010\u000CR\u000C\u0010\rH\u0007X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000F\u0010\u0010H\u0007\u00A2\u0006\u0008\n\u0000\u001A\u0004\u0008\u0011\u0010\u000C\u00F2\u00014\n\u00020\u0001\n\u00020\u0003\n\u00020\u0008\n\u0006\u0012\u0002\u0018\u00020\u0007\n\u0006\u0012\u0002\u0018\u00020\n\n\u00020\u000F\n\u0006\u0012\u0002\u0018\u00050\u000E\n\u0006\u0012\u0002\u0018\u00060\n\n\u00020\u0013\u00A8\u0006\u0017"}, d2 = {"Lcom/popspot/app/presentation/scrap/ScrapViewModel;", "Landroidx/lifecycle/ViewModel;", "scrapRepository", "Lcom/popspot/app/domain/repository/ScrapRepository;", "<init>", "(Lcom/popspot/app/domain/repository/ScrapRepository;)V", "_selectedFilter", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "selectedFilter", "Lkotlinx/coroutines/flow/StateFlow;", "getSelectedFilter", "()Lkotlinx/coroutines/flow/StateFlow;", "_allScraps", "", "Lcom/popspot/app/domain/model/ScrapItem;", "scrappedItems", "getScrappedItems", "filterItems", "", "filter", "removeScrap", "id", "app_debug"}, xs= "", pn = "", xi = 48)
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ScrapViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.domain.repository.ScrapRepository scrapRepository = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedFilter = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedFilter = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.popspot.app.domain.model.ScrapItem>> _allScraps = null;

    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.popspot.app.domain.model.ScrapItem>> scrappedItems = null;

    @javax.inject.Inject()
    public ScrapViewModel(@org.jetbrains.annotations.NotNull() com.popspot.app.domain.repository.ScrapRepository scrapRepository) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedFilter() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.popspot.app.domain.model.ScrapItem>> getScrappedItems() {
        return null;
    }

    public final void filterItems(@org.jetbrains.annotations.NotNull() java.lang.String filter) {
    }

    public final void removeScrap(@org.jetbrains.annotations.NotNull() java.lang.String id) {
    }
}
