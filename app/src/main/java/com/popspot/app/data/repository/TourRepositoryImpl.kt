package com.popspot.app.data.repository

import com.popspot.app.BuildConfig
import com.popspot.app.data.remote.api.TourApi
import com.popspot.app.data.remote.dto.TourDetailItem
import com.popspot.app.data.remote.dto.TourItem
import com.popspot.app.domain.model.TourEvent
import com.popspot.app.domain.repository.TourRepository
import javax.inject.Inject

class TourRepositoryImpl @Inject constructor(
    private val tourApi: TourApi
) : TourRepository {

    override suspend fun getOfficialEvents(
        areaCode: String,
        startDate: String
    ): Result<List<TourEvent>> {
        return try {
            val response = tourApi.getEventList(
                serviceKey     = BuildConfig.TOUR_API_KEY,
                numOfRows      = 20,
                pageNo         = 1,
                mobileOS       = "AND",
                mobileApp      = "PopSpot",
                type           = "json",
                listYN         = "Y",
                arrange        = "A",
                eventStartDate = startDate,
                areaCode       = areaCode
            )
            val events = response.response.body.items.item
                .orEmpty()
                .map { it.toTourEvent() }
            Result.success(events)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEventDetail(contentId: String): Result<TourEvent> {
        return try {
            val response = tourApi.getEventDetail(
                serviceKey   = BuildConfig.TOUR_API_KEY,
                contentId    = contentId,
                mobileOS     = "AND",
                mobileApp    = "PopSpot",
                type         = "json",
                defaultYN    = "Y",
                firstImageYN = "Y",
                overviewYN   = "Y"
            )
            val item = response.response.body.items.item?.firstOrNull()
                ?: return Result.failure(NoSuchElementException("No detail for contentId=$contentId"))
            Result.success(item.toTourEvent())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun TourItem.toTourEvent(): TourEvent = TourEvent(
        id          = contentid,
        title       = title,
        place       = addr1.orEmpty(),
        startDate   = eventstartdate.orEmpty(),
        endDate     = eventenddate.orEmpty(),
        imageUrl    = firstimage.orEmpty(),
        description = "",
        category    = resolveCategoryName(cat2)
    )

    private fun TourDetailItem.toTourEvent(): TourEvent = TourEvent(
        id          = contentid,
        title       = title.orEmpty(),
        place       = addr1.orEmpty(),
        startDate   = eventstartdate.orEmpty(),
        endDate     = eventenddate.orEmpty(),
        imageUrl    = firstimage.orEmpty(),
        description = overview.orEmpty(),
        category    = ""
    )

    private fun resolveCategoryName(cat2: String?): String = when (cat2) {
        "A0207" -> "축제"
        "A0208" -> "문화행사"
        "A0209" -> "전통행사"
        else    -> "행사"
    }
}
