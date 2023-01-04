package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.ShoeListViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    companion object
    {
        lateinit var viewModel : ShoeListViewModel
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        viewModel= ViewModelProvider(this).get(ShoeListViewModel::class.java)   //init viewModel
    }
}
