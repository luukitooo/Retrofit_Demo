package com.example.retrofitdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitdemo.model.ProductInfo
import com.example.retrofitdemo.model.service.RetrofitInstance
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _data = MutableSharedFlow<ProductInfo>()
    val data: SharedFlow<ProductInfo> get() = _data

    fun getData() {
        viewModelScope.launch {
            RetrofitInstance.getInstance().getProduct().also {
                if (it.isSuccessful && it.body() != null)
                    _data.emit(it.body()!!)
            }
        }
    }

}