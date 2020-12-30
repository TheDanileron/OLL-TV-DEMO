package com.provider.tv.presentation.main_screen.show_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.provider.tv.entity.Show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_show.view.*

class ShowViewHolder(var view: View): RecyclerView.ViewHolder(view){

        fun bind(showData: Show) {
            itemView.title?.text = showData.name
            Picasso.get().load(showData.imgUrl).into(itemView.image)
        }

        fun setClickListener(clickListener: View.OnClickListener) {
            itemView.setOnClickListener(clickListener)
        }
    }