package com.provider.tv.framework.retorift

import java.io.Serializable

data class Result(
    val items: List<ShowResult>
) : Serializable