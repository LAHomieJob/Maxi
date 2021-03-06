package com.example.alexey.maxi.di.global.modules

import android.content.Context
import com.example.alexey.maxi.data.network.ApiService
import com.example.alexey.maxi.di.global.modules.InterceptorModule.Companion.OFFLINE_CACHE_INTERCEPTOR
import com.example.alexey.maxi.di.global.modules.InterceptorModule.Companion.ONLINE_CACHE_INTERCEPTOR
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "http://maxi.today/api/v2/"
        const val LOG_TAG = "Log Tag"
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(context: Context): Cache {
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
        return Cache(context.cacheDir, cacheSize)
    }


    @Provides
    @Singleton
    fun provideHttpClient(cache: Cache,
                          @Named(OFFLINE_CACHE_INTERCEPTOR) offlineInterceptor: Interceptor,
                          @Named(ONLINE_CACHE_INTERCEPTOR) onlineInterceptor: Interceptor) =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor())
                    .addInterceptor(offlineInterceptor)
                    .addNetworkInterceptor(onlineInterceptor)
                    .cache(cache)
                    .build()

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(@Named("BASE_URL") baseUrl: String, client: OkHttpClient) =
            Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}