package com.popspot.app.presentation.event;

@kotlin.Metadata(k = 2, mv = {2, 0, 0}, d1 = {"\u0000D\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u000B\n\u0002\u0010\t\n\u0000\u001A\u0014\u0010\u00032\u0004\u0010\u0005(\u00032\u0006\u0008\u0002\u0010\u0007(\u00048\u0002H\u0007\u001A\u0006\u0010\t8\u0002H\u0003\u001A,\u0010\n2\u0004\u0010\u000B(\u00002\u0004\u0010\u000C(\u00052\u0004\u0010\r(\u00062\u0004\u0010\u000F(\u00072\u0004\u0010\u0010(\u00082\u0006\u0008\u0002\u0010\u0012(\t8\u0002H\u0003\u001A\u0012\u0010\u00142\u0004\u0010\u000B(\u00002\u0004\u0010\r(\u00068\u0002H\u0003\u001A\u0012\u0010\u00152\u0004\u0010\u000C(\u00052\u0004\u0010\u000F(\u00078\u0002H\u0003\u001A\u001A\u0010\u00162\u0004\u0010\u0017(\u000B2\u0004\u0010\u0019(\u000C2\u0006\u0008\u0002\u0010\u0012(\t8\u0002H\u0003\u001A\u0012\u0010\u001A2\u0004\u0010\u001B(\n2\u0004\u0010\u001C(\u00088\u0002H\u0003\u001A\u000C\u0010\u001D2\u0004\u0010\u001E(\u00008\u0000H\u0002\u001A\u0012\u0010\u001F2\u0004\u0010 (\u00002\u0004\u0010!(\u00008\u0000H\u0002\u001A\u000C\u0010\"2\u0004\u0010#(\r8\u0000H\u0002\"\u000C\u0010\u0000H\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00F2\u0001^\n\u00020\u0002\n\u0006\u0012\u0002\u0018\u00000\u0001\n\u00020\u0004\n\u00020\u0006\n\u00020\u0008\n\u0004\u0018\u00010\u0002\n\n\u0012\u0002\u0018\u0000\u0012\u0002\u0018\u00020\u000E\n\n\u0012\u0002\u0018\u0005\u0012\u0002\u0018\u00020\u000E\n\u0006\u0012\u0002\u0018\u00020\u0011\n\u00020\u0013\n\u00020\u0018\n\u0006\u0012\u0002\u0018\n0\u0001\n\n\u0012\u0002\u0018\n\u0012\u0002\u0018\u00020\u000E\n\u00020$\u00A8\u0006%"}, d2 = {"CITY_OPTIONS", "", "", "EventListScreen", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/popspot/app/presentation/event/EventListViewModel;", "EventListTopBar", "FilterRow", "selectedCity", "selectedDate", "onCitySelected", "Lkotlin/Function1;", "onDateSelected", "onResetFilters", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "CityDropdown", "DateDropdownButton", "EventGrid", "events", "Lcom/popspot/app/domain/model/Event;", "onCardClick", "EventCard", "event", "onClick", "formatDateLabel", "yyyyMmDd", "formatDateRange", "start", "end", "millisToYyyyMmDd", "millis", "", "app_debug"}, xs= "", pn = "", xi = 48)
public final class EventListScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> CITY_OPTIONS = null;

    @androidx.compose.runtime.Composable()
    private static final void CityDropdown(java.lang.String selectedCity, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onCitySelected) {
    }

    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void DateDropdownButton(java.lang.String selectedDate, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDateSelected) {
    }

    @androidx.compose.runtime.Composable()
    private static final void EventCard(com.popspot.app.domain.model.Event event, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }

    @androidx.compose.runtime.Composable()
    private static final void EventGrid(java.util.List<com.popspot.app.domain.model.Event> events, kotlin.jvm.functions.Function1<? super com.popspot.app.domain.model.Event, kotlin.Unit> onCardClick, androidx.compose.ui.Modifier modifier) {
    }

    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void EventListScreen(@org.jetbrains.annotations.NotNull() androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull() com.popspot.app.presentation.event.EventListViewModel viewModel) {
    }

    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void EventListTopBar() {
    }

    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void FilterRow(java.lang.String selectedCity, java.lang.String selectedDate, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onCitySelected, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDateSelected, kotlin.jvm.functions.Function0<kotlin.Unit> onResetFilters, androidx.compose.ui.Modifier modifier) {
    }

    private static final java.lang.String formatDateLabel(java.lang.String yyyyMmDd) {
        return null;
    }

    private static final java.lang.String formatDateRange(java.lang.String start, java.lang.String end) {
        return null;
    }

    private static final java.lang.String millisToYyyyMmDd(long millis) {
        return null;
    }
}
