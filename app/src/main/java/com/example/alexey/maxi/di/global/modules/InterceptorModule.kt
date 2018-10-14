package com.example.alexey.maxi.di.global.modules

import android.content.Context
import android.util.Log
import com.example.alexey.maxi.util.isConnected
import dagger.Module
import dagger.Provides
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class InterceptorModule {

    companion object {
        const val ONLINE_CACHE_INTERCEPTOR = "online cache interceptor"
        const val OFFLINE_CACHE_INTERCEPTOR = "offline cache interceptor"
        const val CACHE_CONTROL = "Cache-Control"
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
        Log.d(NetworkModule.LOG_TAG, it)
    }).apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Provides
    @Named(ONLINE_CACHE_INTERCEPTOR)
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
    @Named(OFFLINE_CACHE_INTERCEPTOR)
    @Singleton
    fun provideOfflineCacheInterceptor(context: Context) = Interceptor {
        var request = it.request()
        if (context.isConnected()) {
            val cacheControl = CacheControl.Builder()
                    .maxAge(7, TimeUnit.DAYS)
                    .build()
            request = it.request().newBuilder()
                    .header(CACHE_CONTROL, cacheControl.toString())
                    .build()
        }
        it.proceed(request)
    }
}