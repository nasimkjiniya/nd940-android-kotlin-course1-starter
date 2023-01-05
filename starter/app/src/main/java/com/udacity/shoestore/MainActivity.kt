package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.ShoeListViewModel

class MainActivity : AppCompatActivity() {

    companion object
    {
        lateinit var viewModel : ShoeListViewModel
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel= ViewModelProvider(this).get(ShoeListViewModel::class.java)   //init viewModel
    }

}
