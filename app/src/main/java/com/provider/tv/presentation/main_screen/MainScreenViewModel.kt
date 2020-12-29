package com.provider.tv.presentation.main_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.provider.tv.entity.Show

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {
    val showData = MutableLiveData<Show>()
    var borderId = 0

    fun requestList() {
        
    }
}