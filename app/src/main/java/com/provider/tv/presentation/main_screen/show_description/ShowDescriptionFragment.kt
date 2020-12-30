package com.provider.tv.presentation.main_screen.show_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.provider.tv.R
import com.provider.tv.entity.Show
import com.provider.tv.framework.Constants
import com.provider.tv.presentation.TVShowViewModelFactory
import com.provider.tv.presentation.main_screen.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_descripption.*

class ShowDescriptionFragment : Fragment(){
    lateinit var viewModel: MainViewModel
    var observer = Observer<Show?>{
        it?.let { updateUI(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_descripption, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), TVShowViewModelFactory).get(MainViewModel::class.java)
        viewModel.currentShow.observe(viewLifecycleOwner, observer)
    }

    fun updateUI(show: Show) {
        title.text = show.name
        desc.text = show.description
        Picasso.get().load(show.imgUrl).into(image)
        sub_type_tv.text = viewModel.dataConverter.getTypeString(show.isFree)
        start_tv.text = viewModel.dataConverter.formatDate(show.startTime)
        end_tv.text = viewModel.dataConverter.formatDate(show.endTime)

        if(show.isFree == Constants.showTypeSubscription) {
            subscription_btn.text = String.format(getString(R.string.subscription_btn_text), show.subscription.price.toString())
        } else {
            subscription_btn.text = getString(R.string.subscription_btn_text_free)
        }
    }
}