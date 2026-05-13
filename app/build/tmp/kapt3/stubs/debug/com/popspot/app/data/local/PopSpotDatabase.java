package com.popspot.app.data.local;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\u0008'\u0012\u0001\u0000\u0018\u0000B\u0007\u00A2\u0006\u0004\u0008\u0002\u0010\u0003J\u0006\u0010\u00048\u0001H&\u00F2\u0001\u0008\n\u00020\u0001\n\u00020\u0005\u00A8\u0006\u0006"}, d2 = {"Lcom/popspot/app/data/local/PopSpotDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "scrapDao", "Lcom/popspot/app/data/local/ScrapDao;", "app_debug"}, xs= "", pn = "", xi = 48)
@androidx.room.Database(entities = {com.popspot.app.data.local.ScrapEntity.class}, version = 1, exportSchema = false)
public abstract class PopSpotDatabase extends androidx.room.RoomDatabase {

    public PopSpotDatabase() {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public abstract com.popspot.app.data.local.ScrapDao scrapDao();
}
