package com.example.bincard.search.data.dto

import com.example.bincard.search.data.model.BankDTO
import com.example.bincard.search.data.model.CountryDTO
import com.example.bincard.search.data.model.NumberDTO
import com.google.gson.annotations.SerializedName

class SearchBinResponse(
    @SerializedName("number") val number: NumberDTO?,
    @SerializedName("scheme") val scheme: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("brand") val brand: String?,
    @SerializedName("prepaid") val prepaid: Boolean?,
    @SerializedName("country") val country: CountryDTO?,
    @SerializedName("bank") val bank: BankDTO?,
) : Response()