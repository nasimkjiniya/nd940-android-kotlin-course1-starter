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
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.LoginViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel : LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        getElements()
        initElements()

        return binding.root
    }

    private fun getElements()
    {
        viewModel= ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel=viewModel
        binding.setLifecycleOwner(this)
    }

    private fun initElements()
    {
        binding.loginButton.setOnClickListener()
        {
            doLogin()
        }
    }

    public fun doLogin()
    {
        if(!viewModel.isEmailValid(binding.emailEdittext.text.toString().trim()))
        {
            binding.emailEdittext.setError("Email is Invalid!")
        }
        else if(!viewModel.isPasswordValid(binding.passwordEdittext.text.toString().trim()))
        {
            binding.passwordEdittext.setError("Password is Invalid!")
        }
        else
        {
            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
            NavHostFragment.findNavController(this).navigate(action)   // fragment transaction
        }

        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}