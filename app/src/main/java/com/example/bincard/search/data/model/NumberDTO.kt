package com.example.bincard.search.data.model

import com.google.gson.annotations.SerializedName

class NumberDTO(
    @SerializedName("length") var length: Int?,
    @SerializedName("luhn") var luhn: Boolean?
)
