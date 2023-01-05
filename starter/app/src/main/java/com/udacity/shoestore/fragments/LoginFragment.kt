package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        binding.loginButton.setOnClickListener()
        {
            doLogin()
        }

        binding.loginExistingButton.setOnClickListener()
        {
            doLogin()
        }

    }

    fun doLogin()
    {
        if(!viewModel.isEmailValid(binding.emailEdittext.text.toString().trim()))
        {
            binding.emailEdittext.setError(getString(R.string.invalid_email_message))
        }
        else if(!viewModel.isPasswordValid(binding.passwordEdittext.text.toString().trim()))
        {
            binding.passwordEdittext.setError(getString(R.string.invalid_password_message))
            Toast.makeText(context,getString(R.string.password_correction_message),Toast.LENGTH_LONG).show()
        }
        else
        {
            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
            NavHostFragment.findNavController(this).navigate(action)   // fragment transaction
        }
    }

}