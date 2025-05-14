package com.example.bincard.di

import androidx.room.Room
import com.example.bincard.history.data.db.CardInfoDataBase
import com.example.bincard.search.data.network.NetworkClient
import com.example.bincard.search.data.network.RetrofitNetworkClient
import com.example.bincard.search.data.network.SearchBin
import com.example.bincard.util.CheckConnection
import com.example.bincard.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    factory { CheckConnection(androidContext()) }
    single<SearchBin> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build()
            )
            .build()
            .create(SearchBin::class.java)
    }
    single<NetworkClient> {
        RetrofitNetworkClient(get(), get())
    }
    single {
        Room.databaseBuilder(get(), CardInfoDataBase::class.java, "cardInfoDataBase.db")
            .build()
    }
}