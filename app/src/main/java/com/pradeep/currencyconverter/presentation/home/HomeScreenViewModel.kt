package com.pradeep.currencyconverter.presentation.home

import androidx.lifecycle.ViewModel
import com.pradeep.currencyconverter.domain.usecase.GetCurrencyRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val useCase: GetCurrencyRatesUseCase
): ViewModel() {


}