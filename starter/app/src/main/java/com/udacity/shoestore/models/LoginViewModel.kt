package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {

    companion object
    {
        //email validation pattern
        val EMAIL_ADDRESS_PATTERN: Pattern? = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
    }

    private var _isValidEmail = MutableLiveData<Boolean>()
    val isValidEmail : LiveData<Boolean>
        get() = _isValidEmail

    private var _isValidPassword = MutableLiveData<Boolean>()
    val isValidPassword : LiveData<Boolean>
        get() = _isValidPassword

    init {

    }

    fun isEmailValid(email_txt : String) : Boolean
    {
        return EMAIL_ADDRESS_PATTERN?.matcher(email_txt)?.matches() ?: false
    }

    fun isPasswordValid(password_txt :String) :Boolean
    {
        return true
    }

}