package com.popspot.app.domain.repository;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u000E\n\u0002\u0008\u0002\n\u0002\u0010\u000B\n\u0000\u0008f\u0012\u0001\u0000\u0018\u0000J\u0006\u0010\u00028\u0003H&J\u0012\u0010\u00062\u0004\u0010\u0008(\u00018\u0004H\u00A6@\u00A2\u0006\u0002\u0010\tJ\u0012\u0010\n2\u0004\u0010\u000B(\u00058\u0004H\u00A6@\u00A2\u0006\u0002\u0010\rJ\u0012\u0010\u000E2\u0004\u0010\u000B(\u00058\u0006H\u00A6@\u00A2\u0006\u0002\u0010\r\u00F2\u0001$\n\u00020\u0001\n\u00020\u0005\n\u0006\u0012\u0002\u0018\u00010\u0004\n\u0006\u0012\u0002\u0018\u00020\u0003\n\u00020\u0007\n\u00020\u000C\n\u00020\u000F\u00A8\u0006\u0010"}, d2 = {"Lcom/popspot/app/domain/repository/ScrapRepository;", "", "getAllScraps", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/popspot/app/domain/model/ScrapItem;", "insertScrap", "", "item", "(Lcom/popspot/app/domain/model/ScrapItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteScrap", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isScapped", "", "app_debug"}, xs= "", pn = "", xi = 48)
public abstract interface ScrapRepository {

    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.popspot.app.domain.model.ScrapItem>> getAllScraps();

    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertScrap(@org.jetbrains.annotations.NotNull() com.popspot.app.domain.model.ScrapItem item, @org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);

    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteScrap(@org.jetbrains.annotations.NotNull() java.lang.String id, @org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);

    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isScapped(@org.jetbrains.annotations.NotNull() java.lang.String id, @org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
}
