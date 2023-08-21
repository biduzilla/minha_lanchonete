package com.ricky.minhaempresa.presentation.home.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ricky.minhaempresa.R
import com.ricky.minhaempresa.ui.theme.ErrorLight

@Composable
fun TextError() {
    Text(
        text = stringResource(id = R.string.campo_obrigatorio),
        color =ErrorLight
    )
}