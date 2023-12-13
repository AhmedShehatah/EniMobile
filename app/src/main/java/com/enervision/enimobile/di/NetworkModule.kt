package com.enervision.enimobile.di

import com.enervision.enimobile.data.preferences.LocalPref
import com.enervision.enimobile.data.remote.ApiService
import com.enervision.enimobile.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideMoshiConverter(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        prefs: LocalPref,
    ): Retrofit {
        var baseUrl: String = Constants.BASE_URL
        if (!prefs.getString("base_url").isNullOrEmpty()) {
            baseUrl = prefs.getString("base_url")!!
            if (!prefs.getString("port").isNullOrEmpty()) {
                baseUrl += ":${prefs.getString("port")}"
            }
        }
        return try {
            Retrofit.Builder().baseUrl(
                baseUrl
            ).addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
                .build()
        } catch (e: Exception) {
            Retrofit.Builder().baseUrl(
                Constants.BASE_URL
            ).addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
                .build()
        }

    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}