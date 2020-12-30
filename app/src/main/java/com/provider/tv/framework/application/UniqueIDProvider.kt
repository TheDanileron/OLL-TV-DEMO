package com.provider.tv.framework.application

import android.content.Context
import android.provider.Settings

class UniqueIDProvider(val context: Context) {
    fun getID():String {
        val android_id = Settings.Secure.getString(context.contentResolver,
            Settings.Secure.ANDROID_ID);
        return android_id
    }
}