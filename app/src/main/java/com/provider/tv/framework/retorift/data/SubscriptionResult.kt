package com.provider.tv.framework.retorift.data

import java.io.Serializable

data class SubscriptionResult(
    val id: Int,
    val price: Double,
    val name: String
): Serializable