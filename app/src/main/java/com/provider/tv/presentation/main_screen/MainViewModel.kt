package com.provider.tv.presentation.main_screen

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.provider.tv.entity.Show
import com.provider.tv.framework.Constants
import com.provider.tv.framework.Interactors
import com.provider.tv.framework.data_helper.ShowDataConverter
import com.provider.tv.framework.retorift.StateHolder
import com.provider.tv.presentation.TVShowViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainViewModel(application: Application, interactors: Interactors) :
    TVShowViewModel(application, interactors) {
    val showLiveData = MutableLiveData<MutableList<Show>>(ArrayList())
    val currentShow = MutableLiveData<Show?>()

    // is loading
    val loadingStateLiveData = MutableLiveData<Boolean>(false)
    val backgroundExecutor: ExecutorService = Executors.newSingleThreadExecutor()
    var savedIndex = 0
    val dataConverter: ShowDataConverter = ShowDataConverter(application)

    fun loadShowList() {
        if (loadingStateLiveData.value!! || showLiveData.value?.size!! > 0)
            return
        loadingStateLiveData.postValue(true)
        backgroundExecutor.submit {
            val list = interactors.getShowList()
            StateHolder.lastUpdateSize = list.size

            showLiveData.value?.addAll(list)
            showLiveData.postValue(showLiveData.value)
            loadingStateLiveData.postValue(false)
        }
    }

    fun updateShowList(direction: Int) {
        if (loadingStateLiveData.value!!)
            return
        loadingStateLiveData.postValue(true)
        StateHolder.direction = direction
        backgroundExecutor.submit {
            // if we are scrolling up - border is first item, if scrolling down - last item
            if (direction == Constants.direction_up) {
                StateHolder.borderId = showLiveData.value?.first()!!.id
            } else if (direction == Constants.direction_down) {
                StateHolder.borderId = showLiveData.value?.last()!!.id
            }
            val list = interactors.getShowList()
            StateHolder.lastUpdateSize = list.size

            if (direction == Constants.direction_up) {
                showLiveData.value?.addAll(0, list.reversed())
            } else {
                showLiveData.value?.addAll(list)
            }
            showLiveData.postValue(showLiveData.value)
            loadingStateLiveData.postValue(false)
        }
    }

    fun updateDatePanel(firstVisibleItemPosition: Int): String {
        if (showLiveData.value?.size!! > 0 && firstVisibleItemPosition != -1)
            return ShowDataConverter(getApplication()).getDatePanelMainText(
                showLiveData.value?.get(
                    firstVisibleItemPosition
                )?.endTime!!
            )
        return ""
    }

    fun openShow(show: Show) {
        currentShow.postValue(show)
    }

    fun onShowClosed() {
        currentShow.postValue(null)
    }
}