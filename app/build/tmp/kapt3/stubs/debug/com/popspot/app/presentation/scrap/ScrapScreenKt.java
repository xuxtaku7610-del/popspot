package com.popspot.app.presentation.scrap;

@kotlin.Metadata(k = 2, mv = {2, 0, 0}, d1 = {"\u0000D\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0006\u001A\u0014\u0010\u00032\u0004\u0010\u0005(\u00032\u0006\u0008\u0002\u0010\u0007(\u00048\u0002H\u0007\u001A\u0012\u0010\t2\u0004\u0010\n(\u00052\u0004\u0010\u000C(\u00068\u0002H\u0003\u001A \u0010\u000E2\u0004\u0010\u000F(\u00012\u0004\u0010\u0010(\u00002\u0004\u0010\u0011(\u00072\u0006\u0008\u0002\u0010\u0013(\u00088\u0002H\u0003\u001A \u0010\u00152\u0004\u0010\u0016(\n2\u0004\u0010\n(\u00052\u0004\u0010\u0018(\u00072\u0006\u0008\u0002\u0010\u0013(\u00088\u0002H\u0003\u001A\u0018\u0010\u00192\u0004\u0010\u001A(\t2\u0004\u0010\n(\u00052\u0004\u0010\u0018(\u00068\u0002H\u0003\u001A\u000C\u0010\u001B2\u0004\u0010\u001C(\u00008\u0000H\u0002\"\u000C\u0010\u0000H\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00F2\u0001@\n\u00020\u0002\n\u0006\u0012\u0002\u0018\u00000\u0001\n\u00020\u0004\n\u00020\u0006\n\u00020\u0008\n\u00020\u000B\n\u0006\u0012\u0002\u0018\u00020\r\n\n\u0012\u0002\u0018\u0000\u0012\u0002\u0018\u00020\u0012\n\u00020\u0014\n\u00020\u0017\n\u0006\u0012\u0002\u0018\t0\u0001\u00A8\u0006\u001D"}, d2 = {"SCRAP_FILTER_CHIPS", "", "", "ScrapScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/popspot/app/presentation/scrap/ScrapViewModel;", "ScrapTopBar", "isEditMode", "", "onEditToggle", "Lkotlin/Function0;", "ScrapFilterRow", "chips", "selectedFilter", "onFilterSelect", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "ScrapList", "items", "Lcom/popspot/app/domain/model/ScrapItem;", "onUnscrap", "ScrapItemCard", "item", "formatScrapDate", "raw", "app_debug"}, xs= "", pn = "", xi = 48)
public final class ScrapScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> SCRAP_FILTER_CHIPS = null;

    @androidx.compose.runtime.Composable()
    private static final void ScrapFilterRow(java.util.List<java.lang.String> chips, java.lang.String selectedFilter, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onFilterSelect, androidx.compose.ui.Modifier modifier) {
    }

    @androidx.compose.runtime.Composable()
    private static final void ScrapItemCard(com.popspot.app.domain.model.ScrapItem item, boolean isEditMode, kotlin.jvm.functions.Function0<kotlin.Unit> onUnscrap) {
    }

    @androidx.compose.runtime.Composable()
    private static final void ScrapList(java.util.List<com.popspot.app.domain.model.ScrapItem> items, boolean isEditMode, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onUnscrap, androidx.compose.ui.Modifier modifier) {
    }

    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ScrapScreen(@org.jetbrains.annotations.NotNull() androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull() com.popspot.app.presentation.scrap.ScrapViewModel viewModel) {
    }

    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void ScrapTopBar(boolean isEditMode, kotlin.jvm.functions.Function0<kotlin.Unit> onEditToggle) {
    }

    private static final java.lang.String formatScrapDate(java.lang.String raw) {
        return null;
    }
}
