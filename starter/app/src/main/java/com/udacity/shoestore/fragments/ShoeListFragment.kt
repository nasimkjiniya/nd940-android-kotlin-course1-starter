package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        //getElements()
        initElements()

        return binding.root
    }

    private fun initElements()
    {
        MainActivity.viewModel.shoeList.observe(viewLifecycleOwner, Observer { newShoe ->
            if(newShoe.size>0)
            {
                for (n in newShoe.indices){
                    createNewLayout(newShoe.get(n))
                }
                //Timber.i("Added a new shoe -> ${newShoe.get(newShoe.size-1)}")
            }
        })

        MainActivity.viewModel.shoeName.observe(viewLifecycleOwner, Observer { newName->
            Timber.i("Added a new shoeName -> ${newName}")
        })

        binding.floatingActionButton.setOnClickListener()
        {
            gotoShoeDetailsPage()
        }
    }

    private fun createNewLayout(newShoe: Shoe)
    {
        val textView = TextView(context)
        textView.setLayoutParams(
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        if(newShoe!=null)
        {
            textView.text= newShoe.name
            binding.linearLayout.addView(textView)
        }
    }

    private fun gotoShoeDetailsPage()
    {
        val action=ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment()
        NavHostFragment.findNavController(this).navigate(action)   // fragment transaction
    }

}