package com.popspot.app.data.repository;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\u0012\u0001\u0000\u0018\u0000B\u000F\u0008\u0007\u0012\u0004\u0010\u0002(\u0001\u00A2\u0006\u0004\u0008\u0004\u0010\u0005J\u0013\u0010\u00062\u0004\u0010\n(\u00052\u0004\u0010\u000C(\u00058\u0004H\u0096@J\r\u0010\r2\u0004\u0010\u000E(\u00058\u0006H\u0096@J\u0008\u0010\u000F8\u0002@\u0007H\u0002J\u0008\u0010\u000F8\u0002@\u0008H\u0002J\u000C\u0010\u00122\u0004\u0010\u0013(\t8\u0005H\u0002R\u000C\u0010\u0002H\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00F2\u00016\n\u00020\u0001\n\u00020\u0003\n\u00020\t\n\u0006\u0012\u0002\u0018\u00020\u0008\n\u0006\u0012\u0002\u0018\u00030\u0007\n\u00020\u000B\n\u0006\u0012\u0002\u0018\u00020\u0007\n\u00020\u0010\n\u00020\u0011\n\u0004\u0018\u00010\u000B\u00A8\u0006\u0014"}, d2 = {"Lcom/popspot/app/data/repository/TourRepositoryImpl;", "Lcom/popspot/app/domain/repository/TourRepository;", "tourApi", "Lcom/popspot/app/data/remote/api/TourApi;", "<init>", "(Lcom/popspot/app/data/remote/api/TourApi;)V", "getOfficialEvents", "Lkotlin/Result;", "", "Lcom/popspot/app/domain/model/TourEvent;", "areaCode", "", "startDate", "getEventDetail", "contentId", "toTourEvent", "Lcom/popspot/app/data/remote/dto/TourItem;", "Lcom/popspot/app/data/remote/dto/TourDetailItem;", "resolveCategoryName", "cat2", "app_debug"}, xs= "", pn = "", xi = 48)
public final class TourRepositoryImpl implements com.popspot.app.domain.repository.TourRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.data.remote.api.TourApi tourApi = null;

    @javax.inject.Inject()
    public TourRepositoryImpl(@org.jetbrains.annotations.NotNull() com.popspot.app.data.remote.api.TourApi tourApi) {
        super();
    }

    private final com.popspot.app.domain.model.TourEvent toTourEvent(@org.jetbrains.annotations.NotNull() com.popspot.app.data.remote.dto.TourItem $this$toTourEvent) {
        return null;
    }

    private final com.popspot.app.domain.model.TourEvent toTourEvent(@org.jetbrains.annotations.NotNull() com.popspot.app.data.remote.dto.TourDetailItem $this$toTourEvent) {
        return null;
    }

    private final java.lang.String resolveCategoryName(java.lang.String cat2) {
        return null;
    }
}
