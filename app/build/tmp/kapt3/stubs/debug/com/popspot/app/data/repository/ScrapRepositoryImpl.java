package com.popspot.app.data.repository;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u000E\n\u0002\u0008\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\u0012\u0001\u0000\u0018\u0000B\u000F\u0008\u0007\u0012\u0004\u0010\u0002(\u0001\u00A2\u0006\u0004\u0008\u0004\u0010\u0005J\u0006\u0010\u00068\u0004H\u0016J\u0012\u0010\n2\u0004\u0010\u000C(\u00028\u0005H\u0096@\u00A2\u0006\u0002\u0010\rJ\u0012\u0010\u000E2\u0004\u0010\u000F(\u00068\u0005H\u0096@\u00A2\u0006\u0002\u0010\u0011J\u0012\u0010\u00122\u0004\u0010\u000F(\u00068\u0007H\u0096@\u00A2\u0006\u0002\u0010\u0011J\u0008\u0010\u00148\u0002@\u0008H\u0002J\u0008\u0010\u00168\u0008@\u0002H\u0002R\u000C\u0010\u0002H\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00F2\u0001,\n\u00020\u0001\n\u00020\u0003\n\u00020\t\n\u0006\u0012\u0002\u0018\u00020\u0008\n\u0006\u0012\u0002\u0018\u00030\u0007\n\u00020\u000B\n\u00020\u0010\n\u00020\u0013\n\u00020\u0015\u00A8\u0006\u0017"}, d2 = {"Lcom/popspot/app/data/repository/ScrapRepositoryImpl;", "Lcom/popspot/app/domain/repository/ScrapRepository;", "scrapDao", "Lcom/popspot/app/data/local/ScrapDao;", "<init>", "(Lcom/popspot/app/data/local/ScrapDao;)V", "getAllScraps", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/popspot/app/domain/model/ScrapItem;", "insertScrap", "", "item", "(Lcom/popspot/app/domain/model/ScrapItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteScrap", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isScapped", "", "toScrapItem", "Lcom/popspot/app/data/local/ScrapEntity;", "toEntity", "app_debug"}, xs= "", pn = "", xi = 48)
public final class ScrapRepositoryImpl implements com.popspot.app.domain.repository.ScrapRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.data.local.ScrapDao scrapDao = null;

    @javax.inject.Inject()
    public ScrapRepositoryImpl(@org.jetbrains.annotations.NotNull() com.popspot.app.data.local.ScrapDao scrapDao) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.popspot.app.domain.model.ScrapItem>> getAllScraps() {
        return null;
    }

    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object insertScrap(@org.jetbrains.annotations.NotNull() com.popspot.app.domain.model.ScrapItem item, @org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }

    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object deleteScrap(@org.jetbrains.annotations.NotNull() java.lang.String id, @org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }

    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object isScapped(@org.jetbrains.annotations.NotNull() java.lang.String id, @org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }

    private final com.popspot.app.domain.model.ScrapItem toScrapItem(@org.jetbrains.annotations.NotNull() com.popspot.app.data.local.ScrapEntity $this$toScrapItem) {
        return null;
    }

    private final com.popspot.app.data.local.ScrapEntity toEntity(@org.jetbrains.annotations.NotNull() com.popspot.app.domain.model.ScrapItem $this$toEntity) {
        return null;
    }
}
