package com.desarrollodeaplicacionesmoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.desarrollodeaplicacionesmoviles.model.MainUiState

class MainViewModel : ViewModel() {

    private var _strings = MutableLiveData(MainUiState("", ""))

    val strings: LiveData<MainUiState> get() = _strings;

    fun comparar(): Unit {
        val str1 = _strings.value?.txt1 ?: ""
        val str2 = _strings.value?.txt2 ?: ""
        val result: String = if (_strings.value?.txt1.equals(_strings.value?.txt2))
            "Las palabras son iguales"
        else "Las palabras no son iguales"
        result(str1, str2, result)
    }

    fun updateTxt1(text: String) {
        _strings.value?.txt1 = text
    }

    // Método público para actualizar txt2
    fun updateTxt2(text: String) {
        _strings.value?.txt2 = text
    }

    private fun result(str1: String?, str2: String?, result: String) {
        if (str1 != null && str2 != null)
            _strings.value = MainUiState(str1, str2, result)
        else {
            if (str1 != null)
                _strings.value = MainUiState(str1, "", result)
            if (str2 != null)
                _strings.value = MainUiState("", str2, result)
            else _strings.value = MainUiState("", "", result)
        }
    }


}