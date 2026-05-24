package com.popspot.app.domain.model

data class PopupStore(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val location: Location = Location(),
    val imageUrl: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val category: String = ""
)
