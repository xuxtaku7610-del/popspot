package com.popspot.app.data.util

import androidx.core.text.HtmlCompat

object HtmlUtils {
    fun stripTags(html: String): String =
        HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY).toString().trim()
}
