package com.popspot.app.data.repository

import com.popspot.app.BuildConfig
import com.popspot.app.data.remote.api.NaverBlogApi
import com.popspot.app.data.util.HtmlUtils
import com.popspot.app.domain.model.PopupPost
import com.popspot.app.domain.repository.PopupRepository
import javax.inject.Inject

class PopupRepositoryImpl @Inject constructor(
    private val naverBlogApi: NaverBlogApi
) : PopupRepository {

    companion object {
        private const val DEFAULT_QUERY = "팝업스토어"
        private const val DISPLAY_COUNT = 20
    }

    override suspend fun searchPopupPosts(query: String): Result<List<PopupPost>> {
        return try {
            val response = naverBlogApi.searchBlog(
                clientId     = BuildConfig.NAVER_CLIENT_ID,
                clientSecret = BuildConfig.NAVER_CLIENT_SECRET,
                query        = query,
                display      = DISPLAY_COUNT,
                start        = 1,
                sort         = "date"
            )
            val posts = response.items.map { item ->
                PopupPost(
                    id          = item.link,
                    title       = HtmlUtils.stripTags(item.title),
                    description = HtmlUtils.stripTags(item.description),
                    bloggerName = item.bloggername,
                    postDate    = item.postdate,
                    link        = item.link
                )
            }
            Result.success(posts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getLatestPopupPosts(): Result<List<PopupPost>> =
        searchPopupPosts(DEFAULT_QUERY)
}
