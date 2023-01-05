package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoeListViewModel : ViewModel() {

    private var _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList : LiveData<ArrayList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value= arrayListOf()
        Timber.i("View Model has been Initialized!")
    }

    fun addNewShoe(newShoe: Shoe)
    {
        _shoeList.value?.add(newShoe)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("View Model has been Destroyed!")
    }

}