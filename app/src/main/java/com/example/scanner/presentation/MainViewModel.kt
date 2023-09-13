package com.example.scanner.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanner.domain.repo.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repo: MainRepo
): ViewModel() {

    // private for encapsulation and safety
    private val _state = MutableStateFlow(MainScreenState())    // mutable and change over time
    val state = _state.asStateFlow()    // immutable and is read only


    fun startScan() {
        viewModelScope.launch {

            // get the data from the barcode
            repo.startScan().collect() { data->

                if (!data.isNullOrBlank()) {

                    // change the state
                    _state.value = state.value.copy(data)
                }
            }
        }
    }
}