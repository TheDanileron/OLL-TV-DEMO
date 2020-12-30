package com.provider.tv.presentation.main_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.provider.tv.R
import com.provider.tv.entity.Show
import com.provider.tv.presentation.TVShowViewModelFactory
import com.provider.tv.presentation.main_screen.show_description.ShowDescriptionFragment
import com.provider.tv.presentation.main_screen.show_list.ShowListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    val currentShowObserver = Observer<Show?>{
        it?.let { navigateToDescription() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(fragment_container.id, ShowListFragment()).commit()
        viewModel = ViewModelProvider(this, TVShowViewModelFactory).get(MainViewModel::class.java)
        viewModel.currentShow.observe(this, currentShowObserver)
    }

    fun navigateToDescription() {
        supportFragmentManager.beginTransaction().add(fragment_container.id, ShowDescriptionFragment()).addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0) {
            viewModel.onShowClosed()
            supportFragmentManager.popBackStack()
            return
        }
        super.onBackPressed()
    }
}