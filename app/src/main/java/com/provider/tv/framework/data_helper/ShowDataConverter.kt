package com.provider.tv.framework.data_helper

import android.content.Context
import com.provider.tv.R
import java.text.SimpleDateFormat
import java.util.*

class ShowDataConverter(val context: Context) {
    val datePaternBefore = "yyyy-MM-dd HH:mm:ss"
    val datePaternAfter = context.getString(R.string.date_pattern)

    fun formatDate(dateStr: String): String {
        val dateFormat = SimpleDateFormat(datePaternBefore, Locale.getDefault())
        val dateFormatAfter = SimpleDateFormat(datePaternAfter, Locale.getDefault())

        val date = dateFormat.parse(dateStr)
        return dateFormatAfter.format(date)
    }

    fun getTypeString(isFree: Int): String {
        var str = ""
        when (isFree) {
            0 -> str = context.getString(R.string.show_by_sub)
            1 -> str = context.getString(R.string.show_free)
            else ->
                str = context.getString(R.string.show_by_sub)
        }
        return str
    }

    fun getDatePanelMainText(dateStr: String): String {
        val dateFormat = SimpleDateFormat(datePaternBefore, Locale.getDefault())
        val date = dateFormat.parse(dateStr)
        val showCalendar = GregorianCalendar()
        val calendar = GregorianCalendar()
        showCalendar.time = date

        val firstArg = if(showCalendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) context.getString(R.string.today_str) else ""
        val secondArg = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())
        val dateFormatAfter = SimpleDateFormat(context.getString(R.string.date_pattern_main).format(firstArg, secondArg), Locale.getDefault())


        return dateFormatAfter.format(date)
    }
}