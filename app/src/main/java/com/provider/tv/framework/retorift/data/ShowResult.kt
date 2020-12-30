package com.provider.tv.framework.retorift.data

import java.io.Serializable

data class ShowResult(
        val id: Int,
        val name: String,
        val channel_name: String,
        val icon: String,
        val description: String,
        val is_free: Int,
        val subs: SubscriptionResult
) : Serializable  {
    override fun toString(): String {
        return "ShowResult(id=$id, name=$name)"
    }
}