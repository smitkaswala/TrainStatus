package com.example.trainlivestatus.livestatus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.adapter.LiveTrainAdapter
import com.example.trainlivestatus.apihelper.ApiInterface
import com.example.trainlivestatus.application.TrainPays
import com.example.trainlivestatus.clicklistner.LiveTrainClick
import com.example.trainlivestatus.databinding.ActivityLiveTrainBinding
import com.example.trainlivestatus.model.StationsItem
import com.example.trainlivestatus.repository.MainRespository
import com.example.trainlivestatus.utils.BottomSheetDialog
import com.example.trainlivestatus.utils.ModelFactory
import com.example.trainlivestatus.viewmodel.MainViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class LiveTrainActivity : AppCompatActivity() {

    lateinit var binding: ActivityLiveTrainBinding
    lateinit var mainViewModel: MainViewModel
    var date: String? = null
    var calendar: Calendar? = null
    var today: Date? = null
    var dateFormat: DateFormat? = null
    var fixCalender: Calendar? = null
    var fixDate: String? = null
    var p = 0
    var trainNumber: String? = null
    var trainName: String? = null


    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_live_train)

        binding.rvToolbar.setNavigationOnClickListener {

            onBackPressed()
        }

        val intent = intent
        trainNumber = intent.getStringExtra("trainNo")
        trainName = intent.getStringExtra("trainName")

        calendar = Calendar.getInstance()
        today = calendar?.time

        dateFormat = SimpleDateFormat("dd-MM-yyyy")
        fixCalender = Calendar.getInstance()
        today = fixCalender?.time
        fixCalender?.add(Calendar.DAY_OF_YEAR, -4)
        fixDate = fixCalender?.time?.let { dateFormat?.format(it) }

        binding.apply {

            inNoTran.no.visibility = View.GONE
            binding.tvSelectDate.text = (dateFormat as SimpleDateFormat).format(today)

        }

        val apiInterface: ApiInterface = TrainPays.getClient().create(ApiInterface::class.java)

        mainViewModel = ViewModelProvider(
            this,
            ModelFactory(MainRespository(apiInterface))
        )[MainViewModel::class.java]

        mainViewModel.getlivestatus(trainNumber, (dateFormat as SimpleDateFormat).format(today))

        mainViewModel.liveStatusModel.observe(this) {

            binding.apply {

                cvDate.visibility = View.VISIBLE

                val list: List<StationsItem?>? = it?.stations

                for (i in list!!.indices) {

                    if (!list[i]!!.travelled!!) {

                        if (p == 0) {
                            p = i - 1
                        }
                    }

                }

                if (p < 0) {

                    val bottomSheet = BottomSheetDialog()
                    bottomSheet.show(supportFragmentManager, "ModalBottomSheet")
                }

                recyclerLiveStatus.layoutManager = LinearLayoutManager(this@LiveTrainActivity)
                recyclerLiveStatus.adapter = LiveTrainAdapter(
                    this@LiveTrainActivity, it.stations as List<StationsItem>, p,
                    object : LiveTrainClick {
                        @SuppressLint("SetTextI18n")
                        override fun click(
                            pos: Int,
                            stationName: String?,
                            departureTime: String?,
                            delay: Int
                        ) {

                            if (stationName != null && departureTime != null) {

                                binding.tvTrainInfo.visibility = View.VISIBLE

                                if (delay !== 0) {

                                    binding.tvTrainInfo.text =
                                        "Departed $stationName at $departureTime Delay: " + getTime(
                                            delay
                                        )

                                } else {

                                    binding.tvTrainInfo.text =
                                        "Departed $stationName at $departureTime"
                                }

                            } else {

                                binding.tvTrainInfo.visibility = View.GONE
                            }

                            binding.recyclerLiveStatus.post {

                                try {

                                    binding.recyclerLiveStatus.scrollToPosition(pos)

                                } catch (e: Exception) {

                                }
                            }

                        }

                    })
            }

        }

        mainViewModel.errorMessage.observe(this) {

            Toast.makeText(this@LiveTrainActivity, it, Toast.LENGTH_SHORT).show()
        }

        mainViewModel.showLoadingProg.observe(this) {

            if (it) {
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE
            }
        }

        mainViewModel.trainname.observe(this) {

            binding.rvToolbar.title = it

        }

        mainViewModel.trainliveornot.observe(this) {

            if (it) {

                binding.inNoTran.no.visibility = View.VISIBLE
                binding.cvDate.visibility = View.GONE

            }
        }

    }


    private fun getTime(time: Int): String {
        return if (time != 0) {
            val hours = time / 60
            val minute = time % 60
            "$hours:$minute"
        } else {
            "-"
        }
    }

    fun RecyclerView.smoothSnapToPosition(
        position: Int,
        snapMode: Int = LinearSmoothScroller.SNAP_TO_START
    ) {
        val smoothScroller = object : LinearSmoothScroller(this.context) {
            override fun getVerticalSnapPreference(): Int = snapMode
            override fun getHorizontalSnapPreference(): Int = snapMode
        }
        smoothScroller.targetPosition = position
        layoutManager?.startSmoothScroll(smoothScroller)
    }


}