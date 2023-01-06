package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel



class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel by activityViewModels<ShoeListViewModel>()

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

        setHasOptionsMenu(true)
        initElements()

        return binding.root
    }

    private fun initElements()
    {
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { newShoe ->
            if(newShoe.size>0)
            {
                for (n in newShoe.indices){
                    createNewLayout(newShoe.get(n))
                }
            }
        })

        binding.floatingActionButton.setOnClickListener()
        {
            gotoShoeDetailsPage()
        }
    }

    private fun createNewLayout(newShoe: Shoe)
    {
        val l = LinearLayout(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10, 10, 10, 10)
        l.layoutParams = params
        l.orientation = LinearLayout.HORIZONTAL

        val name_text = TextView(context)
        name_text.setSingleLine(false);
        val paramsTextView = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        paramsTextView.setMargins(15,0,0,0)
        paramsTextView.weight = 0.8f
        name_text.textSize = 25f
        name_text.layoutParams = paramsTextView

        val image = ImageView(context)
        val paramsImgView = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        paramsImgView.gravity=Gravity.CENTER
        paramsImgView.weight = 0.2f
        image.setBackground(resources.getDrawable(R.drawable.shoes,null))

        l.addView(image)
        l.addView(name_text)

        if(newShoe!=null)
        {
            binding.emptyTextview.visibility=View.GONE
            name_text.text= newShoe.name.get()+" (${newShoe.size.get()}) \n"+newShoe.company.get()+"\n"+newShoe.description.get()
            binding.linearLayout.addView(l)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.logout_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun gotoShoeDetailsPage()
    {
        val action=ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment()
        NavHostFragment.findNavController(this).navigate(action)   // fragment transaction
    }

}