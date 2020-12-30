package com.provider.tv.presentation.main_screen.show_list

import com.provider.tv.entity.Show

interface OnShowSelectedListener {
    fun onShowSelected(show: Show)
}