package com.provider.tv.framework.retorift.data

import java.io.Serializable

data class Result(
    val items: List<ShowResult>,
    val block_id: String,
    val first_now_index: Int,
    val items_number: Int,
    val offset: Int,
    val total: Int
) : Serializable