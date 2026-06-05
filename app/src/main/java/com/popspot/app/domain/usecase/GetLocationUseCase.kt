package com.popspot.app.domain.usecase

import com.popspot.app.domain.model.Location
import com.popspot.app.domain.repository.MapRepository
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val repository: MapRepository
) {
    suspend operator fun invoke(address: String): Location = repository.getLocation(address)
}
