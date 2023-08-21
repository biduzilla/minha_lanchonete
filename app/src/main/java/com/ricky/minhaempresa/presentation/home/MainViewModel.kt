package com.ricky.minhaempresa.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.minhaempresa.common.DataStoreUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStoreUtil: DataStoreUtil) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreUtil.getTheme().collect { isDarkMode ->
                _state.update {
                    it.copy(
                        darkMode = isDarkMode
                    )
                }
            }
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnChangeTheme -> {
                _state.update {
                    it.copy(
                        darkMode = event.isDark
                    )
                }
                viewModelScope.launch {
                    dataStoreUtil.saveTheme(event.isDark)
                }
            }
        }
    }
}