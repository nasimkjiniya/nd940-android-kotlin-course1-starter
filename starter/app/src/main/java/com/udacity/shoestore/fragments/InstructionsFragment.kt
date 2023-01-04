package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.LoginViewModel
import com.udacity.shoestore.models.Shoe

class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_instructions,
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
        binding.shoeListButton.setOnClickListener()
        {
            gotoMyShoeList()
        }
    }

    private fun gotoMyShoeList()
    {
        val action = InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment()
        NavHostFragment.findNavController(this).navigate(action)   // fragment transaction
    }

}