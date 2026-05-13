package com.popspot.app.data.local;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u000E\n\u0002\u0008\u0002\n\u0002\u0010\u000B\n\u0000\u0008g\u0012\u0001\u0000\u0018\u0000J\u0006\u0010\u00028\u0003H'J\u0012\u0010\u00062\u0004\u0010\u0008(\u00018\u0004H\u00A7@\u00A2\u0006\u0002\u0010\tJ\u0012\u0010\n2\u0004\u0010\u000B(\u00058\u0004H\u00A7@\u00A2\u0006\u0002\u0010\rJ\u0012\u0010\u000E2\u0004\u0010\u000B(\u00058\u0006H\u00A7@\u00A2\u0006\u0002\u0010\r\u00F2\u0001$\n\u00020\u0001\n\u00020\u0005\n\u0006\u0012\u0002\u0018\u00010\u0004\n\u0006\u0012\u0002\u0018\u00020\u0003\n\u00020\u0007\n\u00020\u000C\n\u00020\u000F\u00A8\u0006\u0010"}, d2 = {"Lcom/popspot/app/data/local/ScrapDao;", "", "getAllScraps", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/popspot/app/data/local/ScrapEntity;", "insert", "", "item", "(Lcom/popspot/app/data/local/ScrapEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isScrapped", "", "app_debug"}, xs= "", pn = "", xi = 48)
@androidx.room.Dao()
public abstract interface ScrapDao {

    @androidx.room.Query(value = "SELECT * FROM scraps ORDER BY rowid DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.popspot.app.data.local.ScrapEntity>> getAllScraps();

    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull() com.popspot.app.data.local.ScrapEntity item, @org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);

    @androidx.room.Query(value = "DELETE FROM scraps WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull() java.lang.String id, @org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);

    @androidx.room.Query(value = "SELECT COUNT(*) > 0 FROM scraps WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isScrapped(@org.jetbrains.annotations.NotNull() java.lang.String id, @org.jetbrains.annotations.NotNull() kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
}
