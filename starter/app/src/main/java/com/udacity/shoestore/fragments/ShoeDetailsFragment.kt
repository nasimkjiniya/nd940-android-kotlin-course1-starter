package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private val viewModel by activityViewModels<ShoeListViewModel>()

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
        binding.shoe=Shoe()
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
            val myShoe = Shoe(binding.shoe?.name!!,binding.shoe?.size!!,binding.shoe?.company!!,binding.shoe?.description!!,)
            viewModel.addNewShoe(myShoe)

            Toast.makeText(context,getString(R.string.shoe_added_message),Toast.LENGTH_SHORT).show()
            navigateBack()
        }
    }

    private fun areFeildsValid() : Boolean
    {
        if(binding.nameEdittext.text.toString().trim() == "")
        {
            binding.nameEdittext.error=getString(R.string.empty_field_message)
            return false
        }
        else if(binding.companyEdittext.text.toString().trim() == "")
        {
            binding.companyEdittext.error=getString(R.string.empty_field_message)
            return false
        }
        else if(binding.sizeEdittext.text.toString().trim() == "")
        {
            binding.sizeEdittext.error= getString(R.string.empty_field_message)
            return false
        }
        return true
    }

}