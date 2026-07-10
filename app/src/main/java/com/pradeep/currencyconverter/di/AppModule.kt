package com.pradeep.currencyconverter.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.pradeep.currencyconverter.BuildConfig
import com.pradeep.currencyconverter.core.common.PreferenceManager
import com.pradeep.currencyconverter.core.dispatcher.DispatcherProvider
import com.pradeep.currencyconverter.core.dispatcher.DispatcherProviderImpl
import com.pradeep.currencyconverter.data.api.FrankFurterApiService
import com.pradeep.currencyconverter.data.local.PreferenceManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("currency_prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesPreferenceManager(
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): PreferenceManager =
        PreferenceManagerImpl(sharedPreferences, gson)

    @Provides
    @Singleton
    fun providesDispatcherProvider(impl: DispatcherProviderImpl): DispatcherProvider = impl
}

