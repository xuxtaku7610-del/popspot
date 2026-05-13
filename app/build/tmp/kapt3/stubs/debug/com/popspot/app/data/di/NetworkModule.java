package com.popspot.app.data.di;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0008\u00C7\u0002\u0012\u0001\u0000\u0018\u0000B\t\u0008\u0002\u00A2\u0006\u0004\u0008\u0002\u0010\u0003J\u0006\u0010\u00048\u0001H\u0007J\u000C\u0010\u00062\u0004\u0010\u0008(\u00018\u0002H\u0007J\u000C\u0010\t2\u0004\u0010\u0008(\u00018\u0002H\u0007J\u000E\u0010\n2\u0006\u0008\u0001\u0010\u000C(\u00028\u0003H\u0007J\u000E\u0010\r2\u0006\u0008\u0001\u0010\u000C(\u00028\u0004H\u0007J\u000C\u0010\u000F2\u0004\u0010\u0011(\u00068\u0005H\u0007J\u000C\u0010\u00132\u0004\u0010\u0011(\u00088\u0007H\u0007J\u000C\u0010\u00162\u0004\u0010\u0011(\n8\tH\u0007J\u000C\u0010\u00192\u0004\u0010\u0011(\u000C8\u000BH\u0007\u00F2\u00014\n\u00020\u0001\n\u00020\u0005\n\u00020\u0007\n\u00020\u000B\n\u00020\u000E\n\u00020\u0010\n\u00020\u0012\n\u00020\u0014\n\u00020\u0015\n\u00020\u0017\n\u00020\u0018\n\u00020\u001A\n\u00020\u001B\u00A8\u0006\u001C"}, d2 = {"Lcom/popspot/app/data/di/NetworkModule;", "", "<init>", "()V", "provideOkHttpClient", "Lokhttp3/OkHttpClient;", "provideNaverRetrofit", "Lretrofit2/Retrofit;", "client", "provideTourRetrofit", "provideNaverBlogApi", "Lcom/popspot/app/data/remote/api/NaverBlogApi;", "retrofit", "provideTourApi", "Lcom/popspot/app/data/remote/api/TourApi;", "providePopupRepository", "Lcom/popspot/app/domain/repository/PopupRepository;", "impl", "Lcom/popspot/app/data/repository/PopupRepositoryImpl;", "provideTourRepository", "Lcom/popspot/app/domain/repository/TourRepository;", "Lcom/popspot/app/data/repository/TourRepositoryImpl;", "provideMapRepository", "Lcom/popspot/app/domain/repository/MapRepository;", "Lcom/popspot/app/data/repository/MapRepositoryImpl;", "providePublicDataRepository", "Lcom/popspot/app/domain/repository/PublicDataRepository;", "Lcom/popspot/app/data/repository/PublicDataRepositoryImpl;", "app_debug"}, xs= "", pn = "", xi = 48)
@dagger.Module()
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class NetworkModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.popspot.app.data.di.NetworkModule INSTANCE = null;

    private NetworkModule() {
        super();
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient provideOkHttpClient() {
        return null;
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @javax.inject.Named(value = "naver")
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit provideNaverRetrofit(@org.jetbrains.annotations.NotNull() okhttp3.OkHttpClient client) {
        return null;
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @javax.inject.Named(value = "tour")
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit provideTourRetrofit(@org.jetbrains.annotations.NotNull() okhttp3.OkHttpClient client) {
        return null;
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.data.remote.api.NaverBlogApi provideNaverBlogApi(@javax.inject.Named(value = "naver") @org.jetbrains.annotations.NotNull() retrofit2.Retrofit retrofit) {
        return null;
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.data.remote.api.TourApi provideTourApi(@javax.inject.Named(value = "tour") @org.jetbrains.annotations.NotNull() retrofit2.Retrofit retrofit) {
        return null;
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.domain.repository.PopupRepository providePopupRepository(@org.jetbrains.annotations.NotNull() com.popspot.app.data.repository.PopupRepositoryImpl impl) {
        return null;
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.domain.repository.TourRepository provideTourRepository(@org.jetbrains.annotations.NotNull() com.popspot.app.data.repository.TourRepositoryImpl impl) {
        return null;
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.domain.repository.MapRepository provideMapRepository(@org.jetbrains.annotations.NotNull() com.popspot.app.data.repository.MapRepositoryImpl impl) {
        return null;
    }

    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.domain.repository.PublicDataRepository providePublicDataRepository(@org.jetbrains.annotations.NotNull() com.popspot.app.data.repository.PublicDataRepositoryImpl impl) {
        return null;
    }
}
