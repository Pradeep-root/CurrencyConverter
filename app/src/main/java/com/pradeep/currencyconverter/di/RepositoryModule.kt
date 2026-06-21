package com.pradeep.currencyconverter.di

import com.pradeep.currencyconverter.data.repository.CurrencyRatesRepositoryImpl
import com.pradeep.currencyconverter.domain.repository.CurrencyRatesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsCurrencyRatesRepository(
        repositoryImpl: CurrencyRatesRepositoryImpl
    ): CurrencyRatesRepository

}