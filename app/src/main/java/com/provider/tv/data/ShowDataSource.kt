package com.provider.tv.data

import com.provider.tv.entity.Show

interface ShowDataSource {
    fun getShowList(border: Int):List<Show>

    //todo other methods
}