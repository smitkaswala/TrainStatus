package com.example.trainlivestatus.utils

import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Matcher
import java.util.regex.Pattern

class Validation {

    var pattern: Pattern? = null
    private var matcher: Matcher? = null
    private val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")


    fun isEmpty(editText: EditText): Boolean {
        return editText.text.toString() == ""
    }

    fun isStartingP(editText: EditText): Boolean {
        return editText.text.toString() == ""
    }

    fun isEndingP(editText: EditText): Boolean {
        return editText.text.toString() == ""
    }

    fun isEmpty(textView: TextView): Boolean {
        return textView.text.toString() == ""
    }

    fun isSameDestinations(password: EditText, confirmPassword: EditText): Boolean {
        return password.text.toString() == confirmPassword.text.toString()
    }

    fun isValidPassword(password: EditText): Boolean {
        return password.text.toString().length >= 6
    }


    fun isValidPhoneNumber(phoneNumber: EditText): Boolean {
        return phoneNumber.text.toString().length == 10
    }

    fun isChecked(checkBox: CheckBox): Boolean {
        return checkBox.isChecked
    }
}