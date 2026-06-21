package com.pradeep.currencyconverter.di

import com.pradeep.currencyconverter.BuildConfig
import com.pradeep.currencyconverter.core.dispatcher.DispatcherProvider
import com.pradeep.currencyconverter.core.dispatcher.DispatcherProviderImpl
import com.pradeep.currencyconverter.data.api.FrankFurterApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            }
        )
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesFrankfurtApiService(retrofit: Retrofit): FrankFurterApiService = retrofit.create(
        FrankFurterApiService::class.java
    )

    @Provides
    @Singleton
    fun providesDispatcherProvider(impl: DispatcherProviderImpl): DispatcherProvider = impl
}
