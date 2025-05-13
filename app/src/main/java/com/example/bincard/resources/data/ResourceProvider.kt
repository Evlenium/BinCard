package com.example.bincard.resources.data

import android.content.Context
import com.example.bincard.R
import com.example.bincard.util.CheckConnection

class ResourceProvider(
    private val context: Context,
    private val checkConnection: CheckConnection
) {
    fun getErrorInternetConnection() = context.getString(R.string.no_internet)
    fun getErrorEmptyAnswer() = context.getString(R.string.empty_answer)
    fun getErrorServer() = context.getString(R.string.server_error)
    fun getErrorUnknown() = context.getString(R.string.unknown)
    fun checkInternetConnection() = checkConnection.isInternetAvailable()
}