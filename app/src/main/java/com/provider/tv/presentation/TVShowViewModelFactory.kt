package com.provider.tv.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.provider.tv.framework.Interactors

object TVShowViewModelFactory : ViewModelProvider.Factory {

  lateinit var application: Application

  lateinit var dependencies: Interactors

  fun inject(application: Application, dependencies: Interactors) {
    TVShowViewModelFactory.application = application
    TVShowViewModelFactory.dependencies = dependencies
  }

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if(TVShowViewModel::class.java.isAssignableFrom(modelClass)) {
      return modelClass.getConstructor(Application::class.java, Interactors::class.java)
          .newInstance(
              application,
              dependencies)
    } else {
      throw IllegalStateException("ViewModel must extend TVShowViewModel")
    }
  }

}