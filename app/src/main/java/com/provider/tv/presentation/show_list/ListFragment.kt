package com.provider.tv.presentation.show_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.provider.tv.R
import com.provider.tv.entity.Show
import com.provider.tv.presentation.main_screen.MainScreenViewModel

class ListFragment : Fragment(){
    val viewModel by activityViewModels<MainScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    class ShowRecyclerAdapter(val list:List<Show>) : RecyclerView.Adapter<ShowViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
            return ShowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_show, parent, false))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
            holder.bind(list[position])
        }

    }

    class ShowViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var image: ImageView? = null
        private var title:TextView? = null

        fun bind(showData: Show) {
            title?.text = showData.name
            image?.setImageResource(R.drawable.ic_launcher_background)
        }

    }
}