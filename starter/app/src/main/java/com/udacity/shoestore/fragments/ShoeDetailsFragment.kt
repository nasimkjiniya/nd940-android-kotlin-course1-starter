package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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
        binding.viewModel=viewModel
    }

    private fun initElements()
    {
        binding.btnCancel.setOnClickListener()
        {
            navigateBack()
        }

        viewModel.isNameEmpty.observe(viewLifecycleOwner, Observer { isEmpty->
            if(isEmpty)
                binding.nameEdittext.error=getString(R.string.empty_field_message)
        })

        viewModel.isSizeEmpty.observe(viewLifecycleOwner, Observer { isEmpty->
            if(isEmpty)
                binding.sizeEdittext.error= getString(R.string.empty_field_message)
        })

        viewModel.isCompanyEmpty.observe(viewLifecycleOwner, Observer { isEmpty->
            if(isEmpty)
                binding.companyEdittext.error=getString(R.string.empty_field_message)
        })

        viewModel.isSaved.observe(viewLifecycleOwner, Observer { isDone->
            if(isDone)
            {
                Toast.makeText(context,getString(R.string.shoe_added_message),Toast.LENGTH_SHORT).show()
                viewModel.shoeDetailsAdded()
                navigateBack()
            }
        })

    }

    private fun navigateBack() {
        val action = ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}