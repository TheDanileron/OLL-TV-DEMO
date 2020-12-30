package com.provider.tv.presentation.main_screen.show_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.provider.tv.R
import com.provider.tv.entity.Show
import com.provider.tv.framework.Constants
import com.provider.tv.framework.data_helper.ShowDataConverter
import com.provider.tv.framework.retorift.StateHolder
import com.provider.tv.presentation.TVShowViewModelFactory
import com.provider.tv.presentation.main_screen.MainActivity
import com.provider.tv.presentation.main_screen.MainViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ShowListFragment : Fragment(), OnShowSelectedListener {
    lateinit var viewModel: MainViewModel
    var adapter: ShowRecyclerAdapter? = null
    val showDataObserver = Observer<MutableList<Show>> {
        if (adapter == null) {
            initAdapter(it)
            updateDatePanel()
        } else {
            adapter?.updateAdapter()
            updateDatePanel()
        }
    }
    val loadingObserver = Observer<Boolean> {
        if(it)
            loadingPB.visibility = View.VISIBLE
        else
            loadingPB.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            TVShowViewModelFactory
        ).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        viewModel.loadShowList()
    }

    override fun onStart() {
        super.onStart()
        viewModel.showLiveData.observe(viewLifecycleOwner, showDataObserver)
        viewModel.loadingStateLiveData.observe(viewLifecycleOwner, loadingObserver)
        recycler.scrollToPosition(viewModel.savedIndex)
    }

    fun setupScrollListener() {
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == SCROLL_STATE_IDLE) {
                    val layoutManager = recycler.layoutManager as LinearLayoutManager
                    viewModel.savedIndex = layoutManager.findFirstCompletelyVisibleItemPosition()
                    updateDatePanel()

                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.updateShowList(Constants.direction_down)
                    } else if (!recyclerView.canScrollVertically(-1)) {
                        viewModel.updateShowList(Constants.direction_up)
                    }
                }
            }
        })

    }

    fun updateDatePanel() {
        val layoutManager = recycler.layoutManager as LinearLayoutManager
        val date = viewModel.updateDatePanel(layoutManager.findFirstCompletelyVisibleItemPosition())
        if(date.isNotEmpty()) {
            dateTV.visibility = View.VISIBLE
            dateTV.text = date
        }
    }

    fun initAdapter(list: MutableList<Show>) {
        adapter = ShowRecyclerAdapter(list, this)
        recycler.adapter = adapter
        setupScrollListener()
    }

    override fun onShowSelected(show: Show) {
        viewModel.openShow(show)
    }

}