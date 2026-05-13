package com.popspot.app.presentation.popup;

@kotlin.Metadata(k = 2, mv = {2, 0, 0}, d1 = {"\u0000D\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0005\u001A\u0014\u0010\u00032\u0004\u0010\u0005(\u00032\u0006\u0008\u0002\u0010\u0007(\u00048\u0002H\u0007\u001A\u0012\u0010\t2\u0004\u0010\n(\u00052\u0004\u0010\u000C(\u00058\u0002H\u0003\u001A \u0010\r2\u0004\u0010\u000E(\u00002\u0004\u0010\u000F(\u00062\u0004\u0010\u0011(\u00072\u0006\u0008\u0002\u0010\u0013(\u00088\u0002H\u0003\u001A\u0018\u0010\u00152\u0004\u0010\u0016(\u00012\u0004\u0010\u0017(\u00002\u0004\u0010\u0018(\u00068\u0002H\u0003\u001A\u0014\u0010\u00192\u0004\u0010\u001A(\n2\u0006\u0008\u0002\u0010\u0013(\u00088\u0002H\u0003\u001A\u000C\u0010\u001C2\u0004\u0010\u001D(\t8\u0002H\u0003\u001A\u000C\u0010\u001E2\u0004\u0010\u001F(\u00008\u0000H\u0002\"\u000C\u0010\u0000H\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00F2\u0001@\n\u00020\u0002\n\u0006\u0012\u0002\u0018\u00000\u0001\n\u00020\u0004\n\u00020\u0006\n\u00020\u0008\n\u0006\u0012\u0002\u0018\u00020\u000B\n\n\u0012\u0002\u0018\u0000\u0012\u0002\u0018\u00020\u0010\n\u00020\u0012\n\u00020\u0014\n\u00020\u001B\n\u0006\u0012\u0002\u0018\t0\u0001\u00A8\u0006 "}, d2 = {"FILTER_CHIPS", "", "", "PopupFeedScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/popspot/app/presentation/popup/PopupFeedViewModel;", "PopupFeedTopBar", "onBack", "Lkotlin/Function0;", "onSearchClick", "SearchBar", "query", "onQueryChange", "Lkotlin/Function1;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "modifier", "Landroidx/compose/ui/Modifier;", "ChipRow", "chips", "selectedChip", "onChipSelect", "PostList", "posts", "Lcom/popspot/app/domain/model/PopupPost;", "PopupPostCard", "post", "formatPostDate", "raw", "app_debug"}, xs= "", pn = "", xi = 48)
public final class PopupFeedScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> FILTER_CHIPS = null;

    @androidx.compose.runtime.Composable()
    private static final void ChipRow(java.util.List<java.lang.String> chips, java.lang.String selectedChip, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onChipSelect) {
    }

    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void PopupFeedScreen(@org.jetbrains.annotations.NotNull() androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull() com.popspot.app.presentation.popup.PopupFeedViewModel viewModel) {
    }

    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void PopupFeedTopBar(kotlin.jvm.functions.Function0<kotlin.Unit> onBack, kotlin.jvm.functions.Function0<kotlin.Unit> onSearchClick) {
    }

    @androidx.compose.runtime.Composable()
    private static final void PopupPostCard(com.popspot.app.domain.model.PopupPost post) {
    }

    @androidx.compose.runtime.Composable()
    private static final void PostList(java.util.List<com.popspot.app.domain.model.PopupPost> posts, androidx.compose.ui.Modifier modifier) {
    }

    @androidx.compose.runtime.Composable()
    private static final void SearchBar(java.lang.String query, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQueryChange, androidx.compose.ui.focus.FocusRequester focusRequester, androidx.compose.ui.Modifier modifier) {
    }

    private static final java.lang.String formatPostDate(java.lang.String raw) {
        return null;
    }
}
