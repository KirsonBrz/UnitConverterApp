package com.kirson.unitconverterapp.compose.converter


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.kirson.unitconverterapp.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    inputText: MutableState<String>,
    typedValue: MutableState<String>,
    isLandscape: Boolean,
    save: (String, String) -> Unit
) {

    var toSave by remember { mutableStateOf(false) }
    var descrText by remember { mutableStateOf("Select the conversion type") }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {


        ConversionMenu(list = list, isLandscape, descrText) {
            selectedConversion.value = it
            typedValue.value = "0.0"
        }

        selectedConversion.value?.let {
            descrText = it.description
            InputBlock(conversion = it, inputText = inputText, isLandscape) { input ->
                typedValue.value = input
                toSave = true
            }
        }
        if (typedValue.value != "0.0") {
            val input = typedValue.value.toDouble()
            val multiply = selectedConversion.value!!.multiplyBy
            val result = input * multiply


            //Rounding off the result to 4 decimal places
            val df = DecimalFormat("#.####")
            df.roundingMode = RoundingMode.DOWN
            val roundedResult = df.format(result)

            val message1 =
                "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
            val message2 = "$roundedResult ${selectedConversion.value!!.convertTo}"

            if (toSave) {
                save(message1, message2)
                toSave = false
            }

            ResultBlock(message1 = message1, message2 = message2)

        }
    }


}