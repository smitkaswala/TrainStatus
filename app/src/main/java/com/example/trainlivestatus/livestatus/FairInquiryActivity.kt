package com.example.trainlivestatus.livestatus

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.trainlivestatus.R
import com.example.trainlivestatus.application.TrainPays
import com.example.trainlivestatus.databinding.ActivityFairInquiryBinding
import com.example.trainlivestatus.utils.SharedPref
import com.example.trainlivestatus.utils.SharedPref.Companion.city_from_st
import com.example.trainlivestatus.utils.SharedPref.Companion.city_to_st
import com.example.trainlivestatus.utils.SharedPref.Companion.day_count
import com.example.trainlivestatus.utils.SharedPref.Companion.facebook_url
import com.example.trainlivestatus.utils.SharedPref.Companion.is_date
import com.example.trainlivestatus.utils.Validation
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class FairInquiryActivity : AppCompatActivity() {

    lateinit var binding: ActivityFairInquiryBinding
    private var datePickerDialog: DatePickerDialog? = null
    var citycode: String? = null
    private var citycode1: String? = null
    var cityname: String? = null
    private var cityname1: String? = null
    private var date: String? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_fair_inquiry)

        binding.etfrom.setText(SharedPref.getString(facebook_url))
        binding.etto.setText(SharedPref.getString(day_count))
        cityname = SharedPref.getString(city_from_st)
        cityname1 = SharedPref.getString(city_to_st)
        binding.tvSelectDate.setText(SharedPref.getString(is_date))

        clickevent()

    }

    private fun clickevent() {

        binding.rvToolbar.setNavigationOnClickListener {

            onBackPressed()
        }
        binding.tvSelectDate.setTextIsSelectable(true)
        binding.tvSelectDate.isFocusable = false
        binding.tvSelectDate.isFocusableInTouchMode = false
        binding.tvSelectDate.setOnClickListener {

            openpicker()
        }
        binding.ivOpenCal.setOnClickListener {

            openpicker()
        }
        binding.tvGetStart.setOnClickListener {

            if (TrainPays.isNetConnectionAvailable()) {

                val validations = Validation()

                if (!validations.isStartingP(binding.etfrom)) {

                    citycode = binding.etfrom.text.toString().trim()

                    SharedPref.putString(facebook_url, citycode)

                    if (!validations.isEndingP(binding.etto)) {

                        citycode1 = binding.etto.text.toString().trim()

                        SharedPref.putString(day_count, citycode1)

                        if (!validations.isEmpty(binding.tvSelectDate)) {

                            date = binding.tvSelectDate.text.toString()
                            SharedPref.putString(is_date, date)

                            if (!validations.isSameDestinations(binding.etfrom, binding.etto)) {

                                val intent = Intent(
                                    this@FairInquiryActivity,
                                    RouteDetailsActivity::class.java
                                )

                                intent.putExtra("citycode", citycode)
                                intent.putExtra("citycode1", citycode1)
                                intent.putExtra("cityname", cityname)
                                intent.putExtra("cityname1", cityname1)
                                intent.putExtra("date", date)
                                startActivity(intent)

                            } else {

                                Toast.makeText(
                                    this@FairInquiryActivity,
                                    getString(R.string.same_destinations),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {

                            Toast.makeText(
                                this@FairInquiryActivity,
                                getString(R.string.please_enter_date),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@FairInquiryActivity,
                            getString(R.string.enter_destinations_station),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@FairInquiryActivity,
                        getString(R.string.please_enter_start_station),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {

                Toast.makeText(
                    this@FairInquiryActivity,
                    R.string.please_internet,
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
        binding.etfrom.setOnClickListener {

            val intent = Intent(this@FairInquiryActivity, FindStationActivity::class.java)
            startActivityForResult(intent, 1)
        }
        binding.etto.setOnClickListener {
            val intent = Intent(this@FairInquiryActivity, FindStationActivity::class.java)
            startActivityForResult(intent, 2)
        }
        binding.swip.setOnClickListener {
            val from = binding.etfrom.text.toString().trim()
            val to = binding.etto.text.toString().trim()
            binding.etfrom.setText(to)
            binding.etto.setText(from)
        }

    }

    override fun onRestart() {
        super.onRestart()

        binding.etfrom.setText(SharedPref.getString(facebook_url))
        binding.etto.setText(SharedPref.getString(day_count))
        cityname = SharedPref.getString(city_from_st)
        cityname1 = SharedPref.getString(city_to_st)
        binding.tvSelectDate.setText(SharedPref.getString(is_date))
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                if (data != null) {

                    citycode = data.getStringExtra("citycode")
                    cityname = data.getStringExtra("cityname")
                    citycode = citycode!!.replace("\"", "")

                    SharedPref.putString(facebook_url, citycode)
                    SharedPref.putString(city_from_st, cityname)

                    binding.etfrom.setText(citycode)
                }
            }

            if (requestCode == 2) {

                if (data != null) {

                    citycode1 = data.getStringExtra("citycode")
                    cityname1 = data.getStringExtra("cityname")
                    citycode1 = citycode1?.replace("\"", "")
                    SharedPref.putString(day_count, citycode1)
                    SharedPref.putString(city_to_st, cityname1)
                    binding.etto.setText(citycode1)
                }
            }
        }
    }

    private fun openpicker() {

        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_MONTH]
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]

        datePickerDialog = DatePickerDialog(this@FairInquiryActivity, R.style.DatePickerTheme,
            { _, year, month, dayOfMonth ->

                date = dayOfMonth.toString() + "-" + (month + 1) + "-" + year
                binding.tvSelectDate.setText(date)
                SharedPref.putString(is_date,date)
            }, year, month, day
        )

        datePickerDialog!!.datePicker.minDate = System.currentTimeMillis() - 1000

        //tommorow
        /*datePickerDialog!!.datePicker.minDate = System.currentTimeMillis()+(1000*24*60*60)*/

        datePickerDialog!!.show()
        datePickerDialog!!.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.colorYellow))
        datePickerDialog!!.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.colorYellow))
    }




}