package com.provider.tv.presentation.main_screen.show_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.provider.tv.R
import com.provider.tv.entity.Show
import com.provider.tv.framework.Constants
import com.provider.tv.framework.retorift.StateHolder

class ShowRecyclerAdapter(val showList: MutableList<Show>, val showSelectedListener: OnShowSelectedListener) : RecyclerView.Adapter<ShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_show, parent, false))
    }

    override fun getItemCount(): Int {
        return showList.size
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(showList[position])
        holder.setClickListener(View.OnClickListener {
            showSelectedListener.onShowSelected(showList[position])
        })
    }

    fun updateAdapter() {
        if(StateHolder.direction == Constants.direction_up) {
            notifyItemRangeInserted(0, StateHolder.lastUpdateSize)
        } else {
            notifyItemRangeInserted(showList.size - StateHolder.lastUpdateSize, StateHolder.lastUpdateSize)
        }
    }

}