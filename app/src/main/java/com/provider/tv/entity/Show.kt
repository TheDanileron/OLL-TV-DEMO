package com.provider.tv.entity

data class Show(
        val id: Int,
        val name: String,
        val tvChannelName: String,
        val imgUrl: String,
        val description: String,
        val isFree: Int,
        val subscription: Subscription
)