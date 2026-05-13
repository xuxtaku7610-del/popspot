package com.popspot.app.domain.repository;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0002\u0008f\u0012\u0001\u0000\u0018\u0000J\u000C\u0010\u00028\u0002H\u00A6@\u00A2\u0006\u0002\u0010\u0005\u00F2\u0001\u0010\n\u00020\u0001\n\u00020\u0004\n\u0006\u0012\u0002\u0018\u00010\u0003\u00A8\u0006\u0006"}, d2 = {"Lcom/popspot/app/domain/repository/PublicDataRepository;", "", "getEvents", "", "Lcom/popspot/app/domain/model/Event;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, xs= "", pn = "", xi = 48)
public abstract interface PublicDataRepository {

    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEvents(@org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super java.util.List<com.popspot.app.domain.model.Event>> $completion);
}
