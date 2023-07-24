package com.example.trainlivestatus.livestatus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainlivestatus.R
import com.example.trainlivestatus.adapter.RouteDetailsAdapter
import com.example.trainlivestatus.apihelper.ApiInterface
import com.example.trainlivestatus.application.TrainPays
import com.example.trainlivestatus.databinding.ActivityScheduleBinding
import com.example.trainlivestatus.model.RouteStationModel
import com.example.trainlivestatus.model.TrainBtwnStnsListItem
import com.example.trainlivestatus.utils.CommonUtil
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ScheduleActivity : AppCompatActivity() {

    lateinit var binding: ActivityScheduleBinding
    private var from: String? = null
    private var to: String? = null
    private var date: String? = null
    var firstAc: String? = null
    var secoundAc = false
    var thirdAc = false

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule)

        binding.rvToolbar.setNavigationOnClickListener {

            onBackPressed()
        }

        to = intent.getStringExtra("to")
        from = intent.getStringExtra("from")
        date = intent.getStringExtra("date")
        firstAc = intent.getStringExtra("1a")
        secoundAc = intent.getBooleanExtra("2a", false)
        thirdAc = intent.getBooleanExtra("3a", false)

        val format1 = SimpleDateFormat("dd-MM-yyyy")
        var dt1: Date? = null

        try {
            dt1 = format1.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val format2: DateFormat = SimpleDateFormat("EE")
        var finalDay: String? = null

        if (dt1 != null) {
            finalDay = format2.format(dt1)
        }

        getTrainDetails(finalDay)


    }

    private fun getTrainDetails(finalDay: String?) {

        if (TrainPays.isNetConnectionAvailable()) {

            binding.progressCircular.visibility = View.VISIBLE

            val apiInterface: ApiInterface = getClient().create(ApiInterface::class.java)

            val call: Call<RouteStationModel?>? = apiInterface.RouteStationCall(from, to, date, CommonUtil.tokan, CommonUtil.quota, CommonUtil.locale, "4c266f54-988a-477d-bd6c-4981c124a80a", CommonUtil.appVersion, CommonUtil.EMAIL)
            call?.enqueue(object : Callback<RouteStationModel?> {

                override fun onResponse(
                    call: Call<RouteStationModel?>,
                    response: Response<RouteStationModel?>) {

                    binding.progressCircular.visibility = View.GONE

                    if (response.isSuccessful) {

                        val searchStationModel: RouteStationModel? = response.body()

                        CommonUtil.errormessage = searchStationModel?.errorMessage.toString()

                        if (searchStationModel != null) {

                            val listItemList: List<TrainBtwnStnsListItem>? = searchStationModel.trainBtwnStnsList as List<TrainBtwnStnsListItem>?

                            if (listItemList != null && listItemList.isNotEmpty()) {

                                val filteredList: List<TrainBtwnStnsListItem> = listItemList

                                for (i in filteredList.indices) {

                                    if (finalDay == "Fri") {

                                        if (listItemList[i].runningFri.equals("Y")) {
                                            if (firstAc == null) {

                                                filteredList.toMutableList().add(listItemList[i])

                                            } else {

                                                if (firstAc == "First A/C") {

                                                    if (listItemList[i].avaiblitycache!!.jsonMember1A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Third A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember3A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Chair Car") {
                                                    if (listItemList[i].avaiblitycache!!.cC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Chair") {
                                                    if (listItemList[i].avaiblitycache!!.eC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Anubhuti") {
                                                    if (listItemList[i].avaiblitycache!!.eA != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second Seating") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2S != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Sleeper") {
                                                    if (listItemList[i].avaiblitycache!!.sL != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                            }
                                        }
                                    } else if (finalDay == "Mon") {
                                        if (listItemList[i].runningMon.equals("Y")) {
                                            if (firstAc == null) {
                                                filteredList.toMutableList().add(listItemList[i])
                                            } else {
                                                if (firstAc == "First A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember1A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Third A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember3A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Chair Car") {
                                                    if (listItemList[i].avaiblitycache!!.cC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Chair") {
                                                    if (listItemList[i].avaiblitycache!!.eC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Anubhuti") {
                                                    if (listItemList[i].avaiblitycache!!.eA != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second Seating") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2S != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Sleeper") {
                                                    if (listItemList[i].avaiblitycache!!.sL != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                            }
                                        }
                                    } else if (finalDay == "Sat") {
                                        if (listItemList[i].runningSat.equals("Y")) {
                                            if (firstAc == null) {
                                                filteredList.toMutableList().add(listItemList[i])
                                            } else {
                                                if (firstAc == "First A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember1A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Third A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember3A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Chair Car") {
                                                    if (listItemList[i].avaiblitycache!!.cC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Chair") {
                                                    if (listItemList[i].avaiblitycache!!.eC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Anubhuti") {
                                                    if (listItemList[i].avaiblitycache!!.eA != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second Seating") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2S != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Sleeper") {
                                                    if (listItemList[i].avaiblitycache!!.sL != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                            }
                                        }
                                    } else if (finalDay == "Sun") {
                                        if (listItemList[i].runningSun.equals("Y")) {
                                            if (firstAc == null) {
                                                filteredList.toMutableList().add(listItemList[i])
                                            } else {
                                                if (firstAc == "First A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember1A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Third A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember3A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Chair Car") {
                                                    if (listItemList[i].avaiblitycache!!.cC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Chair") {
                                                    if (listItemList[i].avaiblitycache!!.eC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Anubhuti") {
                                                    if (listItemList[i].avaiblitycache!!.eA != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second Seating") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2S != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Sleeper") {
                                                    if (listItemList[i].avaiblitycache!!.sL != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                            }
                                        }
                                    } else if (finalDay == "Thu") {
                                        if (listItemList[i].runningThu.equals("Y")) {
                                            if (firstAc == null) {
                                                filteredList.toMutableList().add(listItemList[i])
                                            } else {
                                                if (firstAc == "First A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember1A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Third A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember3A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Chair Car") {
                                                    if (listItemList[i].avaiblitycache!!.cC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Chair") {
                                                    if (listItemList[i].avaiblitycache!!.eC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Anubhuti") {
                                                    if (listItemList[i].avaiblitycache!!.eA != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second Seating") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2S != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Sleeper") {
                                                    if (listItemList[i].avaiblitycache!!.sL != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                            }
                                        }
                                    } else if (finalDay == "Tue") {
                                        if (listItemList[i].runningTue.equals("Y")) {
                                            if (firstAc == null) {
                                                filteredList.toMutableList().add(listItemList[i])
                                            } else {
                                                if (firstAc == "First A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember1A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Third A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember3A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Chair Car") {
                                                    if (listItemList[i].avaiblitycache!!.cC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Chair") {
                                                    if (listItemList[i].avaiblitycache!!.eC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Anubhuti") {
                                                    if (listItemList[i].avaiblitycache!!.eA != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second Seating") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2S != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Sleeper") {
                                                    if (listItemList[i].avaiblitycache!!.sL != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                            }
                                        }
                                    } else if (finalDay == "Wed") {
                                        if (listItemList[i].runningWed.equals("Y")) {
                                            if (firstAc == null) {
                                                filteredList.toMutableList().add(listItemList[i])
                                            } else {
                                                if (firstAc == "First A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember1A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Third A/C") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember3A != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Chair Car") {
                                                    if (listItemList[i].avaiblitycache!!.cC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Chair") {
                                                    if (listItemList[i].avaiblitycache!!.eC != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Executive Anubhuti") {
                                                    if (listItemList[i].avaiblitycache!!.eA != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Second Seating") {
                                                    if (listItemList[i].avaiblitycache!!.jsonMember2S != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                                if (firstAc == "Sleeper") {
                                                    if (listItemList[i].avaiblitycache!!.sL != null) {
                                                        filteredList.toMutableList()
                                                            .add(listItemList[i])
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }


                                if (filteredList.isNotEmpty()) {

                                    binding.rv.layoutManager =
                                        LinearLayoutManager(this@ScheduleActivity)
                                    val adapter = RouteDetailsAdapter(
                                        this@ScheduleActivity,
                                        filteredList,
                                        date
                                    )
                                    binding.rv.adapter = adapter
                                } else {

                                    binding.progressCircular.visibility = View.GONE
                                    Toast.makeText(
                                        this@ScheduleActivity,
                                        "" + CommonUtil.errormessage,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            } else {

                                binding.progressCircular.visibility = View.GONE
                                Toast.makeText(
                                    this@ScheduleActivity,
                                    "" + CommonUtil.errormessage,
                                    Toast.LENGTH_SHORT
                                ).show()

                            }

                        } else {

                            binding.progressCircular.visibility = View.GONE
                            Toast.makeText(
                                this@ScheduleActivity,
                                "" + CommonUtil.errormessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {


                        when (response.code()) {

                            404 -> {

                                Toast.makeText(
                                    this@ScheduleActivity,
                                    "404 not found",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            500 -> {

                                Toast.makeText(
                                    this@ScheduleActivity,
                                    "500 server broken",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {

                                Toast.makeText(
                                    this@ScheduleActivity,
                                    "unknown error",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                    }

                }

                override fun onFailure(call: Call<RouteStationModel?>, t: Throwable) {

                    binding.progressCircular.visibility = View.GONE

                    Toast.makeText(
                        this@ScheduleActivity,
                        "Network failure, Please Try Again",
                        Toast.LENGTH_SHORT
                    ).show()

                }


            })


        } else {

            binding.progressCircular.visibility = View.GONE
            Toast.makeText(this@ScheduleActivity, R.string.please_internet, Toast.LENGTH_SHORT).show()
        }

    }


    private fun getClient(): Retrofit {

        val client = OkHttpClient().newBuilder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build();
        return Retrofit.Builder().baseUrl(CommonUtil.BASE_URL_FARE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


}