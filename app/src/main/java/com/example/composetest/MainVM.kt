package com.example.composetest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainVM: ViewModel() {
    private var _items = MutableLiveData<MutableList<Int>>(arrayListOf(-1))
    val items : LiveData<MutableList<Int>> = _items

    fun addItem(number:Int) {
        _items.value?.let {
            it.add(number)
        }
    }
}