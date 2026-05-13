package com.popspot.app.presentation.detail;

@kotlin.Metadata(k = 2, mv = {2, 0, 0}, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\u0008\u0007\u001A\u0014\u0010\u00022\u0004\u0010\u0004(\u00022\u0006\u0008\u0002\u0010\u0006(\u00038\u0001H\u0007\u001A\u000C\u0010\u00082\u0004\u0010\t(\u00048\u0001H\u0003\u001A\u001A\u0010\u000B2\u0004\u0010\u000C(\u00002\u0004\u0010\r(\u00002\u0006\u0008\u0002\u0010\u000E(\u00058\u0001H\u0003\u001A\u0012\u0010\u00102\u0004\u0010\u0011(\u00062\u0004\u0010\u0013(\u00078\u0001H\u0003\u001A\u0018\u0010\u00152\u0004\u0010\u0016(\u00082\u0004\u0010\u0018(\t2\u0004\u0010\u001A(\t8\u0001H\u0003\u001A\u0018\u0010\u001B2\u0004\u0010\u0013(\u00072\u0004\u0010\u001C(\u00042\u0004\u0010\u001D(\u00048\u0001H\u0003\u001A\u000C\u0010\u001E2\u0004\u0010\u001F(\t8\tH\u0002\"\u000C\u0010\u0000H\u0000X\u0082T\u00A2\u0006\u0002\n\u0000\u00F2\u0001,\n\u00020\u0001\n\u00020\u0003\n\u00020\u0005\n\u00020\u0007\n\u0006\u0012\u0002\u0018\u00010\n\n\u00020\u000F\n\u00020\u0012\n\u00020\u0014\n\u00020\u0017\n\u00020\u0019\u00A8\u0006 "}, d2 = {"PAGINATION_DOT_COUNT", "", "DetailScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/popspot/app/presentation/detail/DetailViewModel;", "DetailImageHeader", "onBack", "Lkotlin/Function0;", "PaginationDots", "total", "current", "modifier", "Landroidx/compose/ui/Modifier;", "DetailContent", "store", "Lcom/popspot/app/domain/model/PopupStore;", "isScrapped", "", "InfoRow", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "value", "DetailBottomBar", "onToggleScrap", "onViewSource", "formatDisplayDate", "raw", "app_debug"}, xs= "", pn = "", xi = 48)
public final class DetailScreenKt {
    private static final int PAGINATION_DOT_COUNT = 6;

    @androidx.compose.runtime.Composable()
    private static final void DetailBottomBar(boolean isScrapped, kotlin.jvm.functions.Function0<kotlin.Unit> onToggleScrap, kotlin.jvm.functions.Function0<kotlin.Unit> onViewSource) {
    }

    @androidx.compose.runtime.Composable()
    private static final void DetailContent(com.popspot.app.domain.model.PopupStore store, boolean isScrapped) {
    }

    @androidx.compose.runtime.Composable()
    private static final void DetailImageHeader(kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }

    @androidx.compose.runtime.Composable()
    public static final void DetailScreen(@org.jetbrains.annotations.NotNull() androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull() com.popspot.app.presentation.detail.DetailViewModel viewModel) {
    }

    @androidx.compose.runtime.Composable()
    private static final void InfoRow(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String label, java.lang.String value) {
    }

    @androidx.compose.runtime.Composable()
    private static final void PaginationDots(int total, int current, androidx.compose.ui.Modifier modifier) {
    }

    private static final java.lang.String formatDisplayDate(java.lang.String raw) {
        return null;
    }
}
