package com.popspot.app.domain.repository;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\u0008\u0002\u0008f\u0012\u0001\u0000\u0018\u0000J\u0012\u0010\u00022\u0004\u0010\u0004(\u00028\u0001H\u00A6@\u00A2\u0006\u0002\u0010\u0006\u00F2\u0001\u000C\n\u00020\u0001\n\u00020\u0003\n\u00020\u0005\u00A8\u0006\u0007"}, d2 = {"Lcom/popspot/app/domain/repository/MapRepository;", "", "getLocation", "Lcom/popspot/app/domain/model/Location;", "address", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, xs= "", pn = "", xi = 48)
public abstract interface MapRepository {

    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLocation(@org.jetbrains.annotations.NotNull() java.lang.String address, @org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super com.popspot.app.domain.model.Location> $completion);
}
