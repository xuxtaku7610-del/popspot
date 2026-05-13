package com.popspot.app.data.remote.api;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0003\u0008f\u0012\u0001\u0000\u0018\u0000J$\u0010\u00022\u0006\u0008\u0001\u0010\u0004(\u00022\u0006\u0008\u0003\u0010\u0006(\u00032\u0006\u0008\u0003\u0010\u0008(\u00038\u0001H\u00A7@\u00A2\u0006\u0002\u0010\t\u00F2\u0001\u0010\n\u00020\u0001\n\u00020\u0003\n\u00020\u0005\n\u00020\u0007\u00A8\u0006\n"}, d2 = {"Lcom/popspot/app/data/remote/api/PublicDataApi;", "", "getEvents", "Lcom/popspot/app/data/remote/dto/PublicDataDto;", "serviceKey", "", "numOfRows", "", "pageNo", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, xs= "", pn = "", xi = 48)
public abstract interface PublicDataApi {

    @retrofit2.http.GET(value = "events")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEvents(@retrofit2.http.Query(value = "serviceKey") @org.jetbrains.annotations.NotNull() java.lang.String serviceKey, @retrofit2.http.Query(value = "numOfRows") int numOfRows, @retrofit2.http.Query(value = "pageNo") int pageNo, @org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super com.popspot.app.data.remote.dto.PublicDataDto> $completion);
}
