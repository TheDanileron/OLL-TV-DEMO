package com.provider.tv.framework.application

import android.app.Application
import com.provider.tv.framework.Interactors
import com.provider.tv.framework.retorift.RetrofitShowDataSource
import com.provider.tv.presentation.TVShowViewModelFactory
import com.provider.tv.use_cases.GetShowList

class App : Application(){
    override fun onCreate() {
        super.onCreate()

        TVShowViewModelFactory.inject(this,
            Interactors(
                GetShowList(RetrofitShowDataSource(this))
            )
        )
    }
}