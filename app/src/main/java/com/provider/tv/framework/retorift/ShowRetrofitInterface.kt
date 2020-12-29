package com.provider.tv.framework.retorift

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowRetrofitInterface {
    @GET("demo")
    fun getShowList(@Query("serial_number") serialNumber: String, @Query("borderId") borderId: Int, @Query("direction") appId:Int): Call<Result>
}