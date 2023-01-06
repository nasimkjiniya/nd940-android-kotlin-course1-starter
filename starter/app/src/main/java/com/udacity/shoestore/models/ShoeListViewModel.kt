package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.fragments.ShoeDetailsFragmentDirections
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel : ViewModel() {

    private var _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList : LiveData<ArrayList<Shoe>>
        get() = _shoeList

    private var _isNameEmpty = MutableLiveData<Boolean>()
    val isNameEmpty : LiveData<Boolean>
        get() = _isNameEmpty

    private var _isSizeEmpty = MutableLiveData<Boolean>()
    val isSizeEmpty : LiveData<Boolean>
        get() = _isSizeEmpty

    private var _isCompanyEmpty = MutableLiveData<Boolean>()
    val isCompanyEmpty : LiveData<Boolean>
        get() = _isCompanyEmpty

    private var _isSaved = MutableLiveData<Boolean>()
    val isSaved : LiveData<Boolean>
        get() = _isSaved


    init {
        _shoeList.value= arrayListOf()
        _isNameEmpty.value=false
        _isSizeEmpty.value=false
        _isCompanyEmpty.value=false
        _isSaved.value=false

        Timber.i("View Model has been Initialized!")
    }

    fun addNewShoe(newShoe: Shoe)
    {
        if(newShoe.name.get()==null)
        {
            _isNameEmpty.value=true
        }else if(newShoe.size.get()==null)
        {
            _isSizeEmpty.value=true
        }else if(newShoe.company.get()==null)
        {
            _isCompanyEmpty.value=true
        }else
        {
            if(newShoe.description.get()==null)
            {
                newShoe.description.set("")
            }
            _isSaved.value=true
            _shoeList.value?.add(newShoe)
        }
    }

    fun shoeDetailsAdded()
    {
        _isNameEmpty.value=false
        _isSizeEmpty.value=false
        _isCompanyEmpty.value=false
        _isSaved.value=false
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("View Model has been Destroyed!")
    }

}