package com.popspot.app.data.remote.dto;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0006\n\u0002\u0010\u000B\n\u0002\u0008\u0003\n\u0002\u0010\u000E\n\u0000\u0008\u0086\u0008\u0012\u0001\u0000\u0018\u0000B\u0015\u0012\u0004\u0010\u0002(\u0001\u0012\u0006\u0008\u0002\u0010\u0004(\u0002\u00A2\u0006\u0004\u0008\u0006\u0010\u0007J\u0007\u0010\u00088\u0001H\u00C6\u0003J\u0007\u0010\t8\u0002H\u00C6\u0003J\u0017\u0010\n2\u0006\u0008\u0002\u0010\u0002(\u00012\u0006\u0008\u0002\u0010\u0004(\u00028\u0003H\u00C6\u0001J\r\u0010\u000B2\u0004\u0010\r(\u00058\u0004H\u00D6\u0003J\u0007\u0010\u000E8\u0002H\u00D6\u0001J\u0007\u0010\u000F8\u0006H\u00D6\u0001R\u000E\u0010\u00028\u0006H\u0001X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u00048\u0006H\u0002X\u0087\u0004\u00A2\u0006\u0002\n\u0000\u00F2\u0001\u001E\n\u00020\u0001\n\u00020\u0003\n\u00020\u0005\n\u00020\u0000\n\u00020\u000C\n\u0004\u0018\u00010\u0001\n\u00020\u0010\u00A8\u0006\u0011"}, d2 = {"Lcom/popspot/app/data/remote/dto/TourBody;", "", "items", "Lcom/popspot/app/data/remote/dto/TourItems;", "totalCount", "", "<init>", "(Lcom/popspot/app/data/remote/dto/TourItems;I)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, xs= "", pn = "", xi = 48)
public final class TourBody {
    @com.google.gson.annotations.SerializedName(value = "items")
    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.data.remote.dto.TourItems items = null;

    @com.google.gson.annotations.SerializedName(value = "totalCount")
    private final int totalCount = 0;

    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.data.remote.dto.TourBody copy(@org.jetbrains.annotations.NotNull() com.popspot.app.data.remote.dto.TourItems items, int totalCount) {
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

    public TourBody(@org.jetbrains.annotations.NotNull() com.popspot.app.data.remote.dto.TourItems items, int totalCount) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.data.remote.dto.TourItems component1() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.data.remote.dto.TourItems getItems() {
        return null;
    }

    public final int component2() {
        return 0;
    }

    public final int getTotalCount() {
        return 0;
    }
}
