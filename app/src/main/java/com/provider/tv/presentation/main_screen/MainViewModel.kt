package com.provider.tv.presentation.main_screen

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.provider.tv.entity.Show
import com.provider.tv.framework.Interactors
import com.provider.tv.presentation.TVShowViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainViewModel(application: Application, interactors: Interactors) : TVShowViewModel(application, interactors) {
    val showLiveData = MutableLiveData<MutableList<Show>>(ArrayList())
    val currentShow = MutableLiveData<Show?>()
    val backgroundExecutor:ExecutorService = Executors.newSingleThreadExecutor()
    var borderId = 0
    var savedIndex = 0
    var isLoading = false

    fun loadShowList() {
        if(isLoading || showLiveData.value?.size!! > 0)
            return
        isLoading = true
        backgroundExecutor.submit{
            val list = interactors.getShowList(borderId)

            showLiveData.value?.addAll(list)
            borderId = list.last().id
            showLiveData.postValue(showLiveData.value)
        }
    }

    fun updateShowList() {
        if(isLoading)
            return
        isLoading = true
        backgroundExecutor.submit{
            val list = interactors.getShowList(borderId)

            showLiveData.value?.addAll(list)
            borderId = list.last().id
            showLiveData.postValue(showLiveData.value)
        }
    }

    fun openShow(show: Show) {
        currentShow.postValue(show)
    }

    fun onShowClosed() {
        currentShow.postValue(null)
    }
}