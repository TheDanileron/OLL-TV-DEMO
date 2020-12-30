package com.provider.tv.framework.retorift

import com.provider.tv.framework.retorift.data.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowRetrofitInterface {
    @GET("demo")
    fun getShowList(@Query("serial_number") serialNumber: String, @Query("borderId") borderId: Int, @Query("direction") direction:Int): Call<Result>
}