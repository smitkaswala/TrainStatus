package com.example.trainlivestatus.livestatus

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trainlivestatus.R
import com.example.trainlivestatus.adapter.ClassSelectionAdapter
import com.example.trainlivestatus.application.TrainPays
import com.example.trainlivestatus.databinding.ActivityTrainscheduleBinding
import com.example.trainlivestatus.model.ClassSelectionModel
import com.example.trainlivestatus.utils.SharedPref
import com.example.trainlivestatus.utils.SharedPref.Companion.scheduledDate
import com.example.trainlivestatus.utils.SharedPref.Companion.scheduledFrom
import com.example.trainlivestatus.utils.SharedPref.Companion.scheduledTo
import com.example.trainlivestatus.utils.Validation
import java.util.*

class TrainscheduleActivity : AppCompatActivity() {

    lateinit var binding: ActivityTrainscheduleBinding
    var datePickerDialog: DatePickerDialog? = null
    private var cityFrom: String? = null
    private var cityTo: String? = null
    private var date: String? = null
    var adapter: ClassSelectionAdapter? = null
    var list: ArrayList<ClassSelectionModel> = ArrayList<ClassSelectionModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_trainschedule)

        /*binding.tvStations.setText(SharedPrefs.getString(SharedPrefs.userSharedPrefData.scheduledFrom))
        binding.etTo.setText(SharedPrefs.getString(SharedPrefs.userSharedPrefData.scheduledTo))*/

        binding.rvToolbar.setNavigationOnClickListener {

            onBackPressed()
        }

        list.add(ClassSelectionModel(null, getString(R.string.first_ac)))
        list.add(ClassSelectionModel(null, getString(R.string.secound_ac)))
        list.add(ClassSelectionModel(null, getString(R.string.third_ac)))
        list.add(ClassSelectionModel(null, getString(R.string.chair_car)))
        list.add(ClassSelectionModel(null, getString(R.string.executive_chair)))
        list.add(ClassSelectionModel(null, getString(R.string.excutive_anubhuti)))
        list.add(ClassSelectionModel(null, getString(R.string.secound_seating)))
        list.add(ClassSelectionModel(null, getString(R.string.sleeper)))

        binding.apply {

            tvStations.setText(SharedPref.getString(scheduledFrom))
            etTo.setText(SharedPref.getString(scheduledTo))
            tvSelectDate.setText(SharedPref.getString(scheduledDate))

            rvSeatSelected.layoutManager = GridLayoutManager(this@TrainscheduleActivity, 3)

            adapter = ClassSelectionAdapter(this@TrainscheduleActivity, list)
            rvSeatSelected.adapter = adapter

            tvStations.setTextIsSelectable(true)
            tvStations.isFocusable = false
            tvStations.isFocusableInTouchMode = false


            tvStations.setOnClickListener {

                val intent = Intent(this@TrainscheduleActivity, FindStationActivity::class.java)
                startActivityForResult(intent, 2)
            }


            etTo.setTextIsSelectable(true)
            etTo.isFocusable = false
            etTo.isFocusableInTouchMode = false
            etTo.setOnClickListener {

                val intent = Intent(this@TrainscheduleActivity, FindStationActivity::class.java)
                startActivityForResult(intent, 1)
            }


            tvSelectDate.setTextIsSelectable(true)
            tvSelectDate.isFocusable = false
            tvSelectDate.isFocusableInTouchMode = false

            binding.swip.setOnClickListener {
                val from = tvStations.text.toString().trim()
                val to = etTo.text.toString().trim()
                tvStations.setText(to)
                etTo.setText(from)
            }

            binding.tvSelectDate.setOnClickListener {

                openpicker()

            }

            binding.ivOpenCal.setOnClickListener {

                openpicker()
            }

            binding.tvGetStart.setOnClickListener(View.OnClickListener {

                if (TrainPays.isNetConnectionAvailable()) {

                    val validations = Validation()

                    if (!validations.isStartingP(tvStations)) {

                        cityFrom = tvStations.text.toString().trim()

                        SharedPref.putString(scheduledFrom, cityFrom)

                        if (!validations.isEndingP(etTo)) {

                            cityTo = etTo.text.toString().trim()

                            SharedPref.putString(scheduledTo, cityTo)

                            if (!validations.isEmpty(tvSelectDate)) {

                                date = tvSelectDate.text.toString()

                                SharedPref.putString(scheduledDate, date)

                                if (!validations.isSameDestinations(tvStations, etTo)) {

                                    val classs: String? = adapter!!.getSelected()
                                    val intent = Intent(
                                        this@TrainscheduleActivity,
                                        ScheduleActivity::class.java
                                    )
                                    intent.putExtra("to", cityTo)
                                    intent.putExtra("from", cityFrom)
                                    intent.putExtra("date", date)
                                    intent.putExtra("1a", classs)
                                    startActivity(intent)

                                } else {

                                    Toast.makeText(
                                        this@TrainscheduleActivity,
                                        "Same Destinations",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {

                                Toast.makeText(
                                    this@TrainscheduleActivity,
                                    "Please Enter Date",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {

                            Toast.makeText(
                                this@TrainscheduleActivity,
                                "Please Enter Destinations Station",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    } else {

                        Toast.makeText(
                            this@TrainscheduleActivity,
                            "Please Enter Starting Station",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                } else {

                    Toast.makeText(
                        this@TrainscheduleActivity,
                        R.string.please_internet,
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == 2) {
                if (data != null) {
                    cityFrom = data.getStringExtra("citycode")
                    cityFrom = cityFrom!!.replace("\"", "")
                    SharedPref.putString(scheduledFrom, cityFrom)
                    binding.tvStations.setText(cityFrom)
                }
            }
            if (requestCode == 1) {
                if (data != null) {
                    cityTo = data.getStringExtra("citycode")
                    cityTo = cityTo!!.replace("\"", "")
                    SharedPref.putString(scheduledTo, cityTo)
                    binding.etTo.setText(cityTo)
                }
            }
        }
    }

    fun openpicker(){

        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_MONTH]
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        datePickerDialog = DatePickerDialog(

            this@TrainscheduleActivity, R.style.DatePickerTheme,

            { view, year, month, dayOfMonth ->

                date = dayOfMonth.toString() + "-" + (month + 1) + "-" + year
                SharedPref.putString(scheduledDate, date)
                binding.tvSelectDate.setText(date)

            }, year, month, day
        )
        datePickerDialog!!.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog!!.show()
        datePickerDialog!!.getButton(DatePickerDialog.BUTTON_NEGATIVE)
            .setTextColor(resources.getColor(R.color.colorYellow))
        datePickerDialog!!.getButton(DatePickerDialog.BUTTON_POSITIVE)
            .setTextColor(resources.getColor(R.color.colorYellow))
    }
}