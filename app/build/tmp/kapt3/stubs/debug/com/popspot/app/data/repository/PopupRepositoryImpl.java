package com.popspot.app.data.repository;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\u0008\u0003\u0012\u0001\u0000\u0018\u0000 \r:\u0001\rB\u000F\u0008\u0007\u0012\u0004\u0010\u0002(\u0001\u00A2\u0006\u0004\u0008\u0004\u0010\u0005J\r\u0010\u00062\u0004\u0010\n(\u00058\u0004H\u0096@J\u0007\u0010\u000C8\u0004H\u0096@R\u000C\u0010\u0002H\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00F2\u0001 \n\u00020\u0001\n\u00020\u0003\n\u00020\t\n\u0006\u0012\u0002\u0018\u00020\u0008\n\u0006\u0012\u0002\u0018\u00030\u0007\n\u00020\u000B\u00A8\u0006\u000E"}, d2 = {"Lcom/popspot/app/data/repository/PopupRepositoryImpl;", "Lcom/popspot/app/domain/repository/PopupRepository;", "naverBlogApi", "Lcom/popspot/app/data/remote/api/NaverBlogApi;", "<init>", "(Lcom/popspot/app/data/remote/api/NaverBlogApi;)V", "searchPopupPosts", "Lkotlin/Result;", "", "Lcom/popspot/app/domain/model/PopupPost;", "query", "", "getLatestPopupPosts", "Companion", "app_debug"}, xs= "", pn = "", xi = 48)
public final class PopupRepositoryImpl implements com.popspot.app.domain.repository.PopupRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.data.remote.api.NaverBlogApi naverBlogApi = null;

    @org.jetbrains.annotations.NotNull()
    public static final com.popspot.app.data.repository.PopupRepositoryImpl.Companion Companion = null;

    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DEFAULT_QUERY = "\uD31D\uC5C5\uC2A4\uD1A0\uC5B4";

    private static final int DISPLAY_COUNT = 20;

    @javax.inject.Inject()
    public PopupRepositoryImpl(@org.jetbrains.annotations.NotNull() com.popspot.app.data.remote.api.NaverBlogApi naverBlogApi) {
        super();
    }

    @kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0008\n\u0000\u0008\u0086\u0003\u0012\u0001\u0000\u0018\u0000B\t\u0008\u0002\u00A2\u0006\u0004\u0008\u0002\u0010\u0003R\u0007\u0010\u0004H\u0001X\u0082TR\u0007\u0010\u0006H\u0002X\u0082T\u00F2\u0001\u000C\n\u00020\u0001\n\u00020\u0005\n\u00020\u0007\u00A8\u0006\u0008"}, d2 = {"Lcom/popspot/app/data/repository/PopupRepositoryImpl$Companion;", "", "<init>", "()V", "DEFAULT_QUERY", "", "DISPLAY_COUNT", "", "app_debug"}, xs= "", pn = "", xi = 48)
    public static final class Companion {

        private Companion() {
            super();
        }
    }
}
