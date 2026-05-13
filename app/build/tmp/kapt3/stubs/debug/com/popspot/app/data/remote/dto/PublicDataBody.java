package com.popspot.app.data.remote.dto;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\n\n\u0002\u0010\u000B\n\u0002\u0008\u0003\n\u0002\u0010\u000E\n\u0000\u0008\u0086\u0008\u0012\u0001\u0000\u0018\u0000B'\u0012\u0006\u0008\u0002\u0010\u0002(\u0002\u0012\u0006\u0008\u0002\u0010\u0005(\u0003\u0012\u0006\u0008\u0002\u0010\u0007(\u0003\u0012\u0006\u0008\u0002\u0010\u0008(\u0003\u00A2\u0006\u0004\u0008\t\u0010\nJ\u0007\u0010\u000B8\u0002H\u00C6\u0003J\u0007\u0010\u000C8\u0003H\u00C6\u0003J\u0007\u0010\r8\u0003H\u00C6\u0003J\u0007\u0010\u000E8\u0003H\u00C6\u0003J'\u0010\u000F2\u0006\u0008\u0002\u0010\u0002(\u00022\u0006\u0008\u0002\u0010\u0005(\u00032\u0006\u0008\u0002\u0010\u0007(\u00032\u0006\u0008\u0002\u0010\u0008(\u00038\u0004H\u00C6\u0001J\r\u0010\u00102\u0004\u0010\u0012(\u00068\u0005H\u00D6\u0003J\u0007\u0010\u00138\u0003H\u00D6\u0001J\u0007\u0010\u00148\u0007H\u00D6\u0001R\u000E\u0010\u00028\u0006H\u0002X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u00058\u0006H\u0003X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u00078\u0006H\u0003X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u00088\u0006H\u0003X\u0087\u0004\u00A2\u0006\u0002\n\u0000\u00F2\u0001&\n\u00020\u0001\n\u00020\u0004\n\u0006\u0012\u0002\u0018\u00010\u0003\n\u00020\u0006\n\u00020\u0000\n\u00020\u0011\n\u0004\u0018\u00010\u0001\n\u00020\u0015\u00A8\u0006\u0016"}, d2 = {"Lcom/popspot/app/data/remote/dto/PublicDataBody;", "", "items", "", "Lcom/popspot/app/data/remote/dto/PublicDataItem;", "numOfRows", "", "pageNo", "totalCount", "<init>", "(Ljava/util/List;III)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, xs= "", pn = "", xi = 48)
public final class PublicDataBody {
    @com.google.gson.annotations.SerializedName(value = "items")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.popspot.app.data.remote.dto.PublicDataItem> items = null;

    @com.google.gson.annotations.SerializedName(value = "numOfRows")
    private final int numOfRows = 0;

    @com.google.gson.annotations.SerializedName(value = "pageNo")
    private final int pageNo = 0;

    @com.google.gson.annotations.SerializedName(value = "totalCount")
    private final int totalCount = 0;

    public PublicDataBody() {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.data.remote.dto.PublicDataBody copy(@org.jetbrains.annotations.NotNull() java.util.List<com.popspot.app.data.remote.dto.PublicDataItem> items, int numOfRows, int pageNo, int totalCount) {
        return null;
    }

    public boolean equals(@org.jetbrains.annotations.Nullable() java.lang.Object other) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }

    public PublicDataBody(@org.jetbrains.annotations.NotNull() java.util.List<com.popspot.app.data.remote.dto.PublicDataItem> items, int numOfRows, int pageNo, int totalCount) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.popspot.app.data.remote.dto.PublicDataItem> component1() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.popspot.app.data.remote.dto.PublicDataItem> getItems() {
        return null;
    }

    public final int component2() {
        return 0;
    }

    public final int getNumOfRows() {
        return 0;
    }

    public final int component3() {
        return 0;
    }

    public final int getPageNo() {
        return 0;
    }

    public final int component4() {
        return 0;
    }

    public final int getTotalCount() {
        return 0;
    }
}
