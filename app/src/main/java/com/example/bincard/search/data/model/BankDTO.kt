package com.example.bincard.search.data.model

import com.google.gson.annotations.SerializedName

class BankDTO(
    @SerializedName("name") var name: String?,
    @SerializedName("url") var url: String?,
    @SerializedName("phone") var phone: String?,
    @SerializedName("city") var city: String?
)
