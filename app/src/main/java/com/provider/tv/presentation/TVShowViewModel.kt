package com.provider.tv.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.provider.tv.framework.Interactors

open class TVShowViewModel(application: Application, protected val interactors: Interactors) : AndroidViewModel(application){

}