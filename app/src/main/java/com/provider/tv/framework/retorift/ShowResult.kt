package com.provider.tv.framework.retorift

import java.io.Serializable

data class ShowResult(
    val name: String,
    val tvChannelName: String,
    val imgUrl: String,
    val description: String,
    val isFree: Int
) : Serializable