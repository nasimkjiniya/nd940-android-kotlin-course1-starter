package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )

        getElements()
        initElements()

        return binding.root
    }

    private fun getElements()
    {
        binding.setLifecycleOwner(this)
    }

    private fun initElements()
    {
        binding.btnCancel.setOnClickListener()
        {
            navigateBack()
        }

        binding.btnSave.setOnClickListener()
        {
            updateList()
        }
    }

    private fun navigateBack()
    {
        val action=ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun updateList()
    {
        if(areFeildsValid())
        {
            val newShoe = Shoe(binding.nameEdittext.text.toString().trim(),binding.sizeEdittext.text.toString().trim().toDoubleOrNull()!!
            ,binding.companyEdittext.text.toString().trim(),binding.descriptionEdittext.text.toString().trim())

            MainActivity.viewModel.addNewShoe(newShoe)

            val action=ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    private fun areFeildsValid() : Boolean
    {
        if(binding.nameEdittext.text.toString().trim() == "")
        {
            binding.nameEdittext.error="This Field can not be emptied!"
            return false
        }
        else if(binding.companyEdittext.text.toString().trim() == "")
        {
            binding.companyEdittext.error="This Field can not be emptied!"
            return false
        }
        else if(binding.sizeEdittext.text.toString().trim() == "")
        {
            binding.sizeEdittext.error="This Field can not be emptied!"
            return false
        }
        return true
    }

}