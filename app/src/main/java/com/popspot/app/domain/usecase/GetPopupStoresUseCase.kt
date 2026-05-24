package com.popspot.app.domain.usecase

import com.popspot.app.domain.model.PopupStore
import javax.inject.Inject

class GetPopupStoresUseCase @Inject constructor() {
    suspend operator fun invoke(): List<PopupStore> = emptyList()
}
