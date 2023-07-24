package com.example.trainlivestatus.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trainlivestatus.repository.MainRespository
import com.example.trainlivestatus.viewmodel.MainViewModel

class ModelFactory(private val mainRespository: MainRespository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return MainViewModel(mainRespository) as T
    }
}