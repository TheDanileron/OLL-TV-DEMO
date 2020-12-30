package com.provider.tv.framework.retorift

import android.content.Context
import android.util.Log
import com.provider.tv.entity.Show
import com.provider.tv.data.ShowDataSource
import com.provider.tv.entity.Subscription
import com.provider.tv.framework.application.UniqueIDProvider
import com.provider.tv.framework.retorift.data.Result
import java.lang.Exception

class RetrofitShowDataSource(val context: Context) : ShowDataSource{
    val tag = RetrofitShowDataSource::class.java.simpleName
    val uniqueIDProvider =
        UniqueIDProvider(context)
    val initialDirection: Int = 0
    val direction: Int = -1

    override fun getShowList(border: Int): List<Show> {
        val list:MutableList<Show> = ArrayList()
        val call = RetrofitProvider.retrofitInterface.getShowList(uniqueIDProvider.getID(), border, if (border == 0) initialDirection else direction)
        var result: Result? = null
        try {
            result = call.execute().body()
            Log.i(tag, result.toString())
        } catch (e: Exception) {
            Log.e(tag, e.toString())
        }
        result?.items?.forEach{
            list.add(Show(it.id,it.name, it.channel_name, it.icon, it.description, it.is_free, Subscription(it.subs.id, it.subs.name, it.subs.price)))
        }
        return list
    }

}