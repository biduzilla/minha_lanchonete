package com.ricky.minhaempresa.presentation.home

sealed interface MainEvent {
    data class OnChangeTheme(val isDark: Boolean) : MainEvent
}