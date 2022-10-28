@file:OptIn(ExperimentalMaterial3Api::class)

package com.kirson.unitconverterapp.compose.converter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.kirson.unitconverterapp.data.Conversion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionMenu(
    list: List<Conversion>,
    isLandscape: Boolean,
    descrText : String,
    modifier: Modifier = Modifier,
    convert: (Conversion) -> Unit
) {

    var textFieldSize by remember { mutableStateOf(Size.Zero) } // To assign the dropdown the same width as TextField
    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {

        if (isLandscape) {
            OutlinedTextField(
                value = descrText,
                onValueChange = {  },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = { Text(text = "Convertion type") },
                trailingIcon = {
                    Icon(icon, contentDescription = "icon",
                        modifier.clickable { expanded = !expanded })
                },
                readOnly = true
            )
        } else {
            OutlinedTextField(
                value = descrText,
                onValueChange = {  },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = { Text(text = "Convertion type") },
                trailingIcon = {
                    Icon(icon, contentDescription = "icon",
                        modifier.clickable { expanded = !expanded })
                },
                readOnly = true
            )


        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .background(Color.White)

        ) {
            list.forEach { conversion ->
                DropdownMenuItem(text = {
                    Text(
                        text = conversion.description,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                    onClick = {
                        expanded = false
                        convert(conversion)
                    }
                )

            }
        }

    }
}

