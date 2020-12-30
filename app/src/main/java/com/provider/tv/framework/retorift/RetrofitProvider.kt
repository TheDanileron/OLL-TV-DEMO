package com.provider.tv.framework.retorift


object RetrofitProvider {
    private val BASE_URL = "http://oll.tv/"
    val retrofitInterface: ShowRetrofitInterface
        get() = RetrofitClient.getClient(BASE_URL).create(ShowRetrofitInterface::class.java)
}