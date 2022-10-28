package com.kirson.unitconverterapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirson.unitconverterapp.data.Conversion
import com.kirson.unitconverterapp.data.ConversionResult
import com.kirson.unitconverterapp.data.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterViewModel(
    private val repository: ConverterRepository
) : ViewModel() {

    val selectedConversion: MutableState<Conversion?> =  mutableStateOf(null)
    val inputText: MutableState<String> =  mutableStateOf("")
    val typedValue =  mutableStateOf("0.0")


    fun getConversions() = listOf<Conversion>(
        Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, "Kilograms to Pounds", "kg", "lbs", 2.20462),
        Conversion(3, "Yards to Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters to Yards", "m", "yd", 1.09361),
        Conversion(5, "Miles to Kilometers", "mi", "km", 1.60934),
        Conversion(6, "Kilometers to Miles", "km", "mi", 0.621371)
    )

    val resultList = repository.getSavedResults()

    fun addResult(message1: String, message2: String) {


        viewModelScope.launch(Dispatchers.IO) {


            repository.insertResult(ConversionResult(0, message1, message2))


        }


    }

    fun removeResult(item: ConversionResult) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteResult(item)
        }
    }

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllResults()
        }
    }

}