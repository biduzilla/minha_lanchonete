package com.ricky.minhaempresa.presentation.home.components

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.ricky.minhaempresa.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    @StringRes label:Int,
    keyboardType:KeyboardType = KeyboardType.Text,
    onChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = { onChange(it) },
        label = {
            Text(text = stringResource(id = label))
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
    )

}