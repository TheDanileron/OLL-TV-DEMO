package com.provider.tv.entity

data class Show(
    val name: String,
    val tvChannelName: String,
    val imgUrl: String,
    val description: String,
    val isFree: Int
)