package com.provider.tv.data

import com.provider.tv.entity.Show

interface ShowDataSource {
    fun getShowList():List<Show>

    //todo other methods
}