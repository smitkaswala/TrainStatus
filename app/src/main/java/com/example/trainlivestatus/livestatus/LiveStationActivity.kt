package com.example.trainlivestatus.livestatus

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.trainlivestatus.R
import com.example.trainlivestatus.application.TrainPays
import com.example.trainlivestatus.databinding.ActivityLiveStationBinding
import com.example.trainlivestatus.utils.SharedPref
import com.example.trainlivestatus.utils.SharedPref.Companion.facebook_url
import com.example.trainlivestatus.utils.Validation

class LiveStationActivity : AppCompatActivity() {

    lateinit var binding: ActivityLiveStationBinding
    private var citycode: String? = null
    private var citycode1: String? = null
    var cityname: String? = null
    var select: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_live_station)

        binding.rvToolbar.setNavigationOnClickListener {

            onBackPressed()
        }

        binding.etfrom.setText(SharedPref.getString(facebook_url))

        livedetails()
    }

    private fun livedetails() {

        binding.apply {

            etfrom.setTextIsSelectable(true)
            etfrom.isFocusable = false
            etfrom.isFocusableInTouchMode = false
            etfrom.setOnClickListener {

                val intent = Intent(this@LiveStationActivity, FindStationActivity::class.java)
                startActivityForResult(intent, 1)
            }


            etto.setTextIsSelectable(true)
            etto.isFocusable = false
            etto.isFocusableInTouchMode = false
            etto.setOnClickListener {
                val intent = Intent(this@LiveStationActivity, FindStationActivity::class.java)
                startActivityForResult(intent, 2)
            }


            btngetdetails.setOnClickListener {

                if (TrainPays.isNetConnectionAvailable()) {

                    val validations = Validation()

                    if (!validations.isStartingP(etfrom)) {

                        if (etfrom.text != null) {
                            citycode = etfrom.text.toString().trim()

                            SharedPref.putString(facebook_url, citycode)

                            when {

                                rbTwo.isChecked -> {
                                    select = "2"
                                }
                                rbThree.isChecked -> {
                                    select = "3"
                                }
                                rbFour.isChecked -> {
                                    select = "4"
                                }
                            }

                            val intent = Intent(this@LiveStationActivity, CallLiveStationActivity::class.java)
                            intent.putExtra("citycode", citycode)
                            intent.putExtra("citycode1", citycode1)
                            intent.putExtra("cityname", cityname)
                            intent.putExtra("NextHours", select)
                            startActivity(intent)
                        }
                    } else {

                        Toast.makeText(this@LiveStationActivity, "Please Enter Starting Station", Toast.LENGTH_SHORT).show()
                    }
                } else {

                    Toast.makeText(this@LiveStationActivity, R.string.please_internet, Toast.LENGTH_SHORT).show()

                }
            }

        }

    }

    override fun onRestart() {
        super.onRestart()
        binding.etfrom.setText(SharedPref.getString(facebook_url))
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                if (data != null) {

                    citycode = data.getStringExtra("citycode")
                    citycode = citycode?.replace("\"", "")
                    SharedPref.putString(facebook_url, citycode)
                    binding.etfrom.setText(citycode)
                }
            }
            if (requestCode == 2) {

                if (data != null) {

                    citycode1 = data.getStringExtra("citycode")
                    citycode1 = citycode1!!.replace("\"", "")
                    cityname = data.getStringExtra("cityname")
                    cityname = cityname!!.replace("\"", "")
                    binding.etto.setText(cityname)
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    fun onRadioButtonClicked(view: View) {

        val checked = (view as RadioButton).isChecked
        var str = ""
        when (view.getId()) {

            R.id.rb_two -> if (checked) {
                str = "2"
            }
            R.id.rb_three -> if (checked) {
                str = "3"
            }
            R.id.rb_four -> if (checked) {
                str = "4"
            }
        }
    }


}