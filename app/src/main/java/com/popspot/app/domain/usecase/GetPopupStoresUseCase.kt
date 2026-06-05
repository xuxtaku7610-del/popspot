package com.popspot.app.domain.usecase

import com.popspot.app.domain.model.Location
import com.popspot.app.domain.model.PopupStore
import javax.inject.Inject

class GetPopupStoresUseCase @Inject constructor() {
    // TODO: PopupRepository 연동 후 실제 API 데이터로 교체
    suspend operator fun invoke(): List<PopupStore> = listOf(
        PopupStore(
            id        = "1",
            name      = "나이키 팝업 성수",
            description = "나이키 한정판 컬렉션 팝업스토어",
            location  = Location(37.5444, 127.0557, "서울시 성동구 성수동"),
            imageUrl  = "https://picsum.photos/seed/popup1/300/200",
            startDate = "2026-06-01",
            endDate   = "2026-06-30",
            category  = "패션"
        ),
        PopupStore(
            id        = "2",
            name      = "무신사 팝업 홍대",
            description = "무신사 여름 시즌 팝업스토어",
            location  = Location(37.5563, 126.9236, "서울시 마포구 홍대앞"),
            imageUrl  = "https://picsum.photos/seed/popup2/300/200",
            startDate = "2026-06-05",
            endDate   = "2026-06-20",
            category  = "패션"
        ),
        PopupStore(
            id        = "3",
            name      = "카카오프렌즈 강남",
            description = "카카오 캐릭터 팝업스토어",
            location  = Location(37.4979, 127.0276, "서울시 강남구 강남대로"),
            imageUrl  = "https://picsum.photos/seed/popup3/300/200",
            startDate = "2026-05-15",
            endDate   = "2026-07-15",
            category  = "캐릭터"
        ),
        PopupStore(
            id        = "4",
            name      = "스타벅스 리저브 명동",
            description = "스타벅스 특별 한정 메뉴 팝업",
            location  = Location(37.5636, 126.9869, "서울시 중구 명동"),
            imageUrl  = "https://picsum.photos/seed/popup4/300/200",
            startDate = "2026-06-01",
            endDate   = "2026-06-30",
            category  = "카페"
        ),
        PopupStore(
            id        = "5",
            name      = "아디다스 팝업 이태원",
            description = "아디다스 오리지널스 컬렉션",
            location  = Location(37.5344, 126.9949, "서울시 용산구 이태원동"),
            imageUrl  = "https://picsum.photos/seed/popup5/300/200",
            startDate = "2026-06-10",
            endDate   = "2026-06-25",
            category  = "패션"
        )
    )
}
