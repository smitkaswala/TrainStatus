package com.example.trainlivestatus.livestatus

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainlivestatus.R
import com.example.trainlivestatus.adapter.RepoAdapter
import com.example.trainlivestatus.apihelper.ApiInterface
import com.example.trainlivestatus.application.TrainPays
import com.example.trainlivestatus.clicklistner.TrainSeatClick
import com.example.trainlivestatus.databinding.ActivitySeatAvailableBinding
import com.example.trainlivestatus.model.NameOrCodeModelItem
import com.example.trainlivestatus.utils.CommonUtil
import com.example.trainlivestatus.utils.SharedPref
import com.example.trainlivestatus.utils.Validation
import com.example.trainlivestatus.viewmodel.MainViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*
import kotlin.coroutines.CoroutineContext


class SeatAvailableActivity : BaseClass(), CoroutineScope {

    lateinit var binding: ActivitySeatAvailableBinding
    private var datePickerDialog: DatePickerDialog? = null
    var citycode: String? = null
    var citycode1: String? = null
    var cityname: String? = null
    var cityname1: String? = null
    private var date: String? = null
    lateinit var mainViewModel: MainViewModel

    val apiInterface: ApiInterface by lazy {

        getclient(CommonUtil.FIND_TRAIN_NUMBER).create(ApiInterface::class.java)
    }

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job


    @OptIn(DelicateCoroutinesApi::class, FlowPreview::class, ExperimentalCoroutinesApi::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_seat_available)

        binding.rvToolbar.setNavigationOnClickListener {

            onBackPressed()
        }

        binding.apply {

            tvStations.setText(SharedPref.getString(SharedPref.facebook_url))
            to.setText(SharedPref.getString(SharedPref.day_count))
            cityname = SharedPref.getString(SharedPref.city_from_st)
            cityname1 = SharedPref.getString(SharedPref.city_to_st)
            tvSelectDate.setText(SharedPref.getString(SharedPref.is_date))
            tvSelectDate.setText(SharedPref.getString(SharedPref.is_date))

        }

        clickevent()

        binding.rvSearch.layoutManager = LinearLayoutManager(this@SeatAvailableActivity)

        val adapter = RepoAdapter(this@SeatAvailableActivity, trainSeatClick = object : TrainSeatClick {
                override fun click(photo: NameOrCodeModelItem) {

                    val intent = Intent(this@SeatAvailableActivity, TopSeatCalenderActivity::class.java)
                    intent.putExtra("type", 2)
                    intent.putExtra("trainNo", photo.C)
                    intent.putExtra("sourceCode", photo.origin)
                    intent.putExtra("destinationCode", photo.destination)
                    intent.putExtra("trainName", photo.N)
                    startActivity(intent)
                    binding.rvSearch.visibility = View.GONE
                }
            })
        binding.rvSearch.adapter = adapter



        binding.etSearchTrain.setOnClickListener {

            startActivity(Intent(this@SeatAvailableActivity,TrainBySearchActivity::class.java))
        }



    }

    private fun clickevent() {

        binding.apply {

            tvSelectDate.setTextIsSelectable(true)
            tvSelectDate.isFocusable = false
            tvSelectDate.isFocusableInTouchMode = false

            binding.tvSelectDate.setOnClickListener {

                openpicker()

            }

            binding.ivOpenCal.setOnClickListener {

                openpicker()
            }

            binding.tvGetStart.setOnClickListener {

                if (TrainPays.isNetConnectionAvailable()) {

                    val validations = Validation()

                    if (!validations.isStartingP(tvStations)) {

                        citycode = tvStations.text.toString().trim()
                        SharedPref.putString(SharedPref.facebook_url, citycode)

                        if (!validations.isEndingP(to)) {

                            citycode1 = to.text.toString().trim()
                            SharedPref.putString(SharedPref.day_count, citycode1)

                            if (!validations.isEmpty(tvSelectDate)) {

                                date = tvSelectDate.text.toString()
                                SharedPref.putString(SharedPref.is_date, date)

                                if (!validations.isSameDestinations(tvStations, to)) {

                                    val intent = Intent(
                                        this@SeatAvailableActivity,
                                        TrainTimeActivity::class.java
                                    )
                                    intent.putExtra("type", 0)
                                    intent.putExtra("citycode", citycode)
                                    intent.putExtra("citycode1", citycode1)
                                    intent.putExtra("cityname", cityname)
                                    intent.putExtra("cityname1", cityname1)
                                    intent.putExtra("date", date)

                                    startActivity(intent)

                                } else {

                                    Toast.makeText(
                                        this@SeatAvailableActivity,
                                        getString(R.string.same_destinations),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {

                                Toast.makeText(
                                    this@SeatAvailableActivity,
                                    getString(R.string.please_enter_date),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@SeatAvailableActivity,
                                getString(R.string.enter_destinations_station),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {

                        Toast.makeText(
                            this@SeatAvailableActivity,
                            getString(R.string.please_enter_start_station),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {

                    Toast.makeText(
                        this@SeatAvailableActivity,
                        R.string.please_internet,
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }

            tvStations.setOnClickListener {

                val intent = Intent(this@SeatAvailableActivity, FindStationActivity::class.java)
                startActivityForResult(intent, 1)
            }

            to.setOnClickListener {
                val intent = Intent(this@SeatAvailableActivity, FindStationActivity::class.java)
                startActivityForResult(intent, 2)
            }

            binding.swip.setOnClickListener {

                val from = tvStations.text.toString().trim()
                val too = to.text.toString().trim()
                tvStations.setText(too)
                to.setText(from)
            }
        }

    }

    override fun onRestart() {
        super.onRestart()

        binding.apply {

            tvStations.setText(SharedPref.getString(SharedPref.facebook_url))
            to.setText(SharedPref.getString(SharedPref.day_count))
            cityname = SharedPref.getString(SharedPref.city_from_st)
            cityname1 = SharedPref.getString(SharedPref.city_to_st)
            tvSelectDate.setText(SharedPref.getString(SharedPref.is_date))
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                if (data != null) {

                    citycode = data.getStringExtra("citycode")
                    cityname = data.getStringExtra("cityname")
                    citycode = citycode!!.replace("\"", "")

                    SharedPref.putString(SharedPref.facebook_url, citycode)
                    SharedPref.putString(SharedPref.city_from_st, cityname)

                    Log.e("1", "onActivityResult: $citycode-$cityname")

                    binding.tvStations.setText(citycode)
                }
            }

            if (requestCode == 2) {

                if (data != null) {

                    citycode1 = data.getStringExtra("citycode")
                    cityname1 = data.getStringExtra("cityname")
                    citycode1 = citycode1!!.replace("\"", "")
                    SharedPref.putString(SharedPref.day_count, citycode1)
                    SharedPref.putString(SharedPref.city_to_st, cityname1)
                    binding.to.setText(citycode1)

                }
            }
        }
    }

    fun openpicker() {

        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_MONTH]
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        datePickerDialog = DatePickerDialog(

            this@SeatAvailableActivity, R.style.DatePickerTheme,

            { view, year, month, dayOfMonth ->

                date = dayOfMonth.toString() + "-" + (month + 1) + "-" + year
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

    suspend fun fetchnamecode(id: String): Flow<List<NameOrCodeModelItem>> {

        return flow {
            val comment = apiInterface.nameorcode(id, "getTrainByNameOrCode")
            delay(300)
            emit(comment)
        }.flowOn(Dispatchers.IO)
    }

    override fun onStop() {
        super.onStop()
        job.cancel()
    }

}