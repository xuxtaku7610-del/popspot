package com.popspot.app.data.remote.dto;

@kotlin.Metadata(k = 1, mv = {2, 0, 0}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0010\u000B\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0002\u0008\u0086\u0008\u0012\u0001\u0000\u0018\u0000B\u001F\u0012\u0006\u0008\u0002\u0010\u0002(\u0001\u0012\u0006\u0008\u0002\u0010\u0004(\u0002\u0012\u0006\u0008\u0002\u0010\u0006(\u0004\u00A2\u0006\u0004\u0008\t\u0010\nJ\u0007\u0010\u000B8\u0001H\u00C6\u0003J\u0007\u0010\u000C8\u0002H\u00C6\u0003J\u0007\u0010\r8\u0004H\u00C6\u0003J\u001F\u0010\u000E2\u0006\u0008\u0002\u0010\u0002(\u00012\u0006\u0008\u0002\u0010\u0004(\u00022\u0006\u0008\u0002\u0010\u0006(\u00048\u0005H\u00C6\u0001J\r\u0010\u000F2\u0004\u0010\u0011(\u00078\u0006H\u00D6\u0003J\u0007\u0010\u00128\u0008H\u00D6\u0001J\u0007\u0010\u00148\u0001H\u00D6\u0001R\u000E\u0010\u00028\u0006H\u0001X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u00048\u0006H\u0002X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u00068\u0006H\u0004X\u0087\u0004\u00A2\u0006\u0002\n\u0000\u00F2\u0001*\n\u00020\u0001\n\u00020\u0003\n\u00020\u0005\n\u00020\u0008\n\u0006\u0012\u0002\u0018\u00030\u0007\n\u00020\u0000\n\u00020\u0010\n\u0004\u0018\u00010\u0001\n\u00020\u0013\u00A8\u0006\u0015"}, d2 = {"Lcom/popspot/app/data/remote/dto/NaverGeocodingDto;", "", "status", "", "meta", "Lcom/popspot/app/data/remote/dto/GeocodingMeta;", "addresses", "", "Lcom/popspot/app/data/remote/dto/GeocodingAddress;", "<init>", "(Ljava/lang/String;Lcom/popspot/app/data/remote/dto/GeocodingMeta;Ljava/util/List;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, xs= "", pn = "", xi = 48)
public final class NaverGeocodingDto {
    @com.google.gson.annotations.SerializedName(value = "status")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String status = null;

    @com.google.gson.annotations.SerializedName(value = "meta")
    @org.jetbrains.annotations.NotNull()
    private final com.popspot.app.data.remote.dto.GeocodingMeta meta = null;

    @com.google.gson.annotations.SerializedName(value = "addresses")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.popspot.app.data.remote.dto.GeocodingAddress> addresses = null;

    public NaverGeocodingDto() {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.data.remote.dto.NaverGeocodingDto copy(@org.jetbrains.annotations.NotNull() java.lang.String status, @org.jetbrains.annotations.NotNull() com.popspot.app.data.remote.dto.GeocodingMeta meta, @org.jetbrains.annotations.NotNull() java.util.List<com.popspot.app.data.remote.dto.GeocodingAddress> addresses) {
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

    public NaverGeocodingDto(@org.jetbrains.annotations.NotNull() java.lang.String status, @org.jetbrains.annotations.NotNull() com.popspot.app.data.remote.dto.GeocodingMeta meta, @org.jetbrains.annotations.NotNull() java.util.List<com.popspot.app.data.remote.dto.GeocodingAddress> addresses) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatus() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.data.remote.dto.GeocodingMeta component2() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final com.popspot.app.data.remote.dto.GeocodingMeta getMeta() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.popspot.app.data.remote.dto.GeocodingAddress> component3() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.popspot.app.data.remote.dto.GeocodingAddress> getAddresses() {
        return null;
    }
}
