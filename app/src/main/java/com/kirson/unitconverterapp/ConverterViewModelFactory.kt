package com.kirson.unitconverterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kirson.unitconverterapp.data.ConverterRepository

class ConverterViewModelFactory(
    private val repository: ConverterRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        ConverterViewModel(repository) as T
}