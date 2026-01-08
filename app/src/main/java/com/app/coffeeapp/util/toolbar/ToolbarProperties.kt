package com.app.coffeeapp.util.toolbar

import androidx.annotation.ColorRes

data class ToolbarProperties(
    val type: ToolbarType,
    val title: String? = null,
    @ColorRes val backgroundColor: Int? = null
)

enum class ToolbarType {
    GONE,
    EMPTY_TOOLBAR,
    BACK_WITH_CLOSE,
    BACK_ONLY,
    CLOSE_ONLY,
    TITLE_ONLY,
    BACK_WITH_TITLE,
    CLOSE_WITH_TITLE;
}