package com.popspot.app.data.di;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0008\u00C7\u0002\u0012\u0001\u0000\u0018\u0000B\t\u0008\u0002\u00A2\u0006\u0004\u0008\u0002\u0010\u0003J\u000E\u0010\u00042\u0006\u0008\u0001\u0010\u0006(\u00028\u0001H\u0007J\u000C\u0010\u00082\u0004\u0010\n(\u00018\u0003H\u0007J\u000C\u0010\u000B2\u0004\u0010\r(\u00058\u0004H\u0007\u00F2\u0001\u0018\n\u00020\u0001\n\u00020\u0005\n\u00020\u0007\n\u00020\t\n\u00020\u000C\n\u00020\u000E\u00A8\u0006\u000F"}, d2 = {"Lcom/popspot/app/data/di/DatabaseModule;", "", "<init>", "()V", "provideDatabase", "Lcom/popspot/app/data/local/PopSpotDatabase;", "ctx", "Landroid/content/Context;", "provideScrapDao", "Lcom/popspot/app/data/local/ScrapDao;", "db", "provideScrapRepository", "Lcom/popspot/app/domain/repository/ScrapRepository;", "impl", "Lcom/popspot/app/data/repository/ScrapRepositoryImpl;", "app_debug"}, xs= "", pn = "", xi = 48)
@dagger.Module()
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.popspot.app.data.di.DatabaseModule INSTANCE = null;

    private DatabaseModule() {
        super();
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.data.local.PopSpotDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext() @org.jetbrains.annotations.NotNull() android.content.Context ctx) {
        return null;
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.data.local.ScrapDao provideScrapDao(@org.jetbrains.annotations.NotNull() com.popspot.app.data.local.PopSpotDatabase db) {
        return null;
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.domain.repository.ScrapRepository provideScrapRepository(@org.jetbrains.annotations.NotNull() com.popspot.app.data.repository.ScrapRepositoryImpl impl) {
        return null;
    }
}
