package com.example.jcomposeappsubmission.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jcomposeappsubmission.data.model.Kuliner
import com.example.jcomposeappsubmission.data.repository.KulinerRepository
import com.example.jcomposeappsubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: KulinerRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Kuliner>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Kuliner>>>
        get() = _uiState

    fun getAllKuliner() {
        viewModelScope.launch {
            repository.getAllListKuliner()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { kulinerData: List<Kuliner> ->
                    _uiState.value = UiState.Success(kulinerData)
                }
        }
    }
}