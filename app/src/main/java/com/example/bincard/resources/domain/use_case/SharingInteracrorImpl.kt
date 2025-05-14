package com.example.bincard.resources.domain.use_case

import com.example.bincard.resources.data.ResourceProvider
import com.example.bincard.resources.domain.api.SharingInteractor

class SharingInteracrorImpl(private val resourceProvider: ResourceProvider) : SharingInteractor {
    override fun getErrorUnknown() =
        resourceProvider.getErrorUnknown()
}