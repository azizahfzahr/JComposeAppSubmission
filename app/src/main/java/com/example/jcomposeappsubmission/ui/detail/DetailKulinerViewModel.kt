package com.example.jcomposeappsubmission.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jcomposeappsubmission.data.model.Kuliner
import com.example.jcomposeappsubmission.data.repository.KulinerRepository
import com.example.jcomposeappsubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailKulinerViewModel(private val repository: KulinerRepository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Kuliner>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<Kuliner>>
        get() = _uiState

    fun getIdKuliner(idKuliner: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getDetailKulinerById(idKuliner))
        }
    }
}