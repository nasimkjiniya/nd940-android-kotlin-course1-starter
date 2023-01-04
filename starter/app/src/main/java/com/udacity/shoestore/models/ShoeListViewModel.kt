package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoeListViewModel : ViewModel() {

    private var _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList : LiveData<ArrayList<Shoe>>
        get() = _shoeList

    private var _shoeName : MutableLiveData<String> = MutableLiveData()
    val shoeName : LiveData<String>
        get() = _shoeName

    private var _shoe = MutableLiveData<Shoe>()
    val shoe : LiveData<Shoe>
        get() = _shoe

    init {
        _shoeList.value= arrayListOf()
        Timber.i("View Model has been Initialized!")
    }

    fun addNewShoe(newShoe: Shoe)
    {
        _shoeList.value?.add(newShoe)
        _shoe.value=newShoe
        _shoeName.value="Nasim"
    }

    fun getArraySize() : Int
    {
        return arrayOf(_shoeList).size
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("View Model has been Destroyed!")
    }

}