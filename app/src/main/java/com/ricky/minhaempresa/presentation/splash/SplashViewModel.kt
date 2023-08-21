package com.ricky.minhaempresa.presentation.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    init {
        loadingSplash()
    }

    private fun loadingSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            _state.update {
                it.copy(loading = true)
            }
        }, 3000)
    }
}