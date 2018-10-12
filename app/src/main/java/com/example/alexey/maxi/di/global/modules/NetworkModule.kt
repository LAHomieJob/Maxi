package com.example.alexey.maxi.di.global.modules

import android.content.Context
import android.util.Log
import com.example.alexey.maxi.data.network.ApiService
import com.example.alexey.maxi.util.isConnected
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "http://maxi.today/api/v2/"
        const val LOG_TAG = "Log Tag"
        const val CACHE_CONTROL = "Cache-Control"
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(context: Context): Cache {
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
        return Cache(context.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
        Log.d(LOG_TAG, it)
    }).apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Provides
    @Singleton
    fun provideCacheInterceptor() = Interceptor {
        val cacheControl = CacheControl.Builder()
                .maxAge(2, TimeUnit.MINUTES)
                .build()
        it.proceed(it.request()).newBuilder()
                .header(CACHE_CONTROL, cacheControl.toString())
                .build()
    }

    @Provides
    @Singleton
    fun provideOfflineCacheInterceptor(context: Context) = Interceptor {
        var request = it.request()
        if(context.isConnected()){
            val cacheControl = CacheControl.Builder()
                    .maxAge(7, TimeUnit.DAYS)
                    .build()
            request = it.request().newBuilder()
                    .header(CACHE_CONTROL, cacheControl.toString())
                    .build()
        }
        it.proceed(request)
    }

    @Provides
    @Singleton
    fun provideHttpClient(cache: Cache, context: Context) = OkHttpClient.Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .addInterceptor( provideOfflineCacheInterceptor(context))
            .addNetworkInterceptor( provideCacheInterceptor() )
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
                    .client(client)
                    .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}