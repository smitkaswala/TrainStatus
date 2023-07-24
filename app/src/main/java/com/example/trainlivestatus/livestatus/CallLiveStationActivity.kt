package com.example.trainlivestatus.livestatus

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainlivestatus.R
import com.example.trainlivestatus.adapter.LiveStationAdapter
import com.example.trainlivestatus.apihelper.ApiInterface
import com.example.trainlivestatus.application.TrainPays
import com.example.trainlivestatus.databinding.ActivityCallLiveStationBinding
import com.example.trainlivestatus.model.LiveModel
import com.example.trainlivestatus.utils.CommonUtil
import com.google.gson.JsonObject
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CallLiveStationActivity : BaseClass() {

    lateinit var binding: ActivityCallLiveStationBinding
    val handler = Handler(Looper.getMainLooper())
    var mes: String? = null
    var secpnd: String? = null
    var first: String? = null
    var code: String? = null
    var NextHours: String? = null
    var cityname: String? = null
    var objects: ArrayList<LiveModel> = ArrayList()
    var TrainName: String? = null
    var TrainNumber: String? = null
    var ScheduleArr: String? = null
    var ExpectedArr: String? = null
    var ExpectedArrColor: String? = null
    var DelayArr: String? = null
    var DelayArrColor: String? = null
    var ScheduleDep: String? = null
    var ExpectedDep: String? = null
    var ExpectedDepColor: String? = null
    var DelayDep: String? = null
    var DelayDepColor: String? = null
    var ExpPF: String? = null
    var uri: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_call_live_station)

        binding.progressCircular.visibility = View.VISIBLE

        binding.rvToolbar.setOnClickListener {

            onBackPressed()
        }

        binding.rvToolbar.setOnMenuItemClickListener {

            when (it.itemId) {

                R.id.share -> {

                }
            }
            true
        }

        code = intent.getStringExtra("citycode")
        cityname = intent.getStringExtra("cityname")
        NextHours = intent.getStringExtra("NextHours")

        livestaioninfo()

    }

    private fun livestaioninfo() {

        if (TrainPays.isNetConnectionAvailable()) {

            /* binding.progressCircular.visibility = View.VISIBLE*/

            val apiInterface: ApiInterface = getclient(CommonUtil.API_URL1).create(ApiInterface::class.java)

            val jsonob = JsonObject()
            jsonob.addProperty("NextHours", NextHours)
            jsonob.addProperty("StationFromCode", code)
            jsonob.addProperty("FromStation", cityname)
            jsonob.addProperty("key_version", "pnr_ios_key_v1")

            val call: Call<JsonObject?>? = apiInterface.livestation1(jsonob)

            call?.enqueue(object : Callback<JsonObject?> {

                override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {

                    Log.e("TAG", "onResponse: ${response.body().toString()}" )

                    /*binding.progressCircular.visibility = View.GONE*/

                    if (response.isSuccessful) {

                        val jsonObject = response.body()

                        if (jsonObject != null) {

                            val jsonObject1 = jsonObject.getAsJsonObject("parameters")
                            val jsonin = jsonObject1["jsonIn"].asString

                            getlivestation1(jsonin)

                        } else {

                            handler.postDelayed({ //Do something after 5000ms

                                /*binding.progressCircular.visibility = View.GONE*/

                            }, 2000)

                        }

                    } else {

                        when (response.code()) {

                            404 -> handler.postDelayed({ //Do something after 5000ms

                                /*binding.progressCircular.visibility = View.GONE*/

                                Toast.makeText(
                                    this@CallLiveStationActivity,
                                    "404 not found",
                                    Toast.LENGTH_SHORT
                                ).show()


                            }, 2000)

                            500 -> handler.postDelayed({ //Do something after 5000ms

                                /*binding.progressCircular.visibility = View.GONE*/

                                Toast.makeText(
                                    this@CallLiveStationActivity,
                                    "500 server broken",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }, 2000)

                            else -> handler.postDelayed({ //Do something after 5000ms

                                /*binding.progressCircular.visibility = View.GONE*/

                                Toast.makeText(
                                    this@CallLiveStationActivity,
                                    "unknown error",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }, 2000)

                        }

                    }
                }

                override fun onFailure(call: Call<JsonObject?>, t: Throwable) {

                    handler.postDelayed({ //Do something after 5000ms

                        /*binding.progressCircular.visibility = View.GONE*/

                        Toast.makeText(
                            this@CallLiveStationActivity,
                            "Network failure, Please Try Again",
                            Toast.LENGTH_SHORT
                        ).show()

                    }, 2000)

                }

            })


        } else {

            handler.postDelayed({ //Do something after 5000ms

                /* binding.progressCircular.visibility = View.GONE*/

                Toast.makeText(
                    this@CallLiveStationActivity,
                    R.string.please_internet,
                    Toast.LENGTH_SHORT
                ).show()

            }, 2000)

        }


    }

    private fun getlivestation1(jsonin: String?) {

        if (TrainPays.isNetConnectionAvailable()) {

            /*binding.progressCircular.visibility = View.VISIBLE*/

            val apiInterface: ApiInterface =
                getclient(CommonUtil.API_URL2).create(ApiInterface::class.java)

            val jsonob = JsonObject()
            jsonob.addProperty("jsonIn", jsonin)

            val call: Call<JsonObject?>? = apiInterface.livestation2(jsonob)
            call?.enqueue(object : Callback<JsonObject?> {

                override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {

                    /*binding.progressCircular.visibility = View.GONE*/

                    if (response.isSuccessful) {

                        val jsonObject = response.body()

                        if (jsonObject != null) {

                            val jsonIn1 = jsonObject["jsonIn"].asString
                            getlivestation2(jsonIn1)

                        } else {

                            /* handler.postDelayed({ //Do something after 5000ms

                                 binding.progressCircular.visibility = View.GONE

                             }, 2000)*/
                        }
                    } else {

                        when (response.code()) {

                            404 ->

                                handler.postDelayed({ //Do something after 5000ms

                                    binding.progressCircular.visibility = View.GONE
                                    Toast.makeText(
                                        this@CallLiveStationActivity,
                                        "404 not found",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                }, 2000)

                            500 ->

                                handler.postDelayed({ //Do something after 5000ms

                                    binding.progressCircular.visibility = View.GONE

                                    Toast.makeText(
                                        this@CallLiveStationActivity,
                                        "500 server broken",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }, 2000)

                            else ->

                                handler.postDelayed({ //Do something after 5000ms

                                    binding.progressCircular.visibility = View.GONE

                                    Toast.makeText(
                                        this@CallLiveStationActivity,
                                        "unknown error",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }, 2000)

                        }
                    }
                }

                override fun onFailure(call: Call<JsonObject?>, t: Throwable) {

                    handler.postDelayed({ //Do something after 5000ms

                        /*binding.progressCircular.visibility = View.GONE*/
                        Toast.makeText(
                            this@CallLiveStationActivity,
                            "Network failure, Please Try Again",
                            Toast.LENGTH_SHORT
                        ).show()

                    }, 2000)
                }
            })

        } else {

            handler.postDelayed({ //Do something after 5000ms

                /*binding.progressCircular.visibility = View.GONE*/

                Toast.makeText(
                    this@CallLiveStationActivity,
                    R.string.please_internet,
                    Toast.LENGTH_SHORT
                ).show()

            }, 2000)
        }
    }

    private fun getlivestation2(jsonIn1: String?) {

        if (TrainPays.isNetConnectionAvailable()) {

            binding.progressCircular.visibility = View.VISIBLE

            val apiInterface: ApiInterface = getclient(CommonUtil.API_URL1).create(ApiInterface::class.java)
            val jsonObject = JsonObject()
            jsonObject.addProperty("NextHours", NextHours)
            jsonObject.addProperty("StationFromCode", code)
            jsonObject.addProperty("fromUrl", "https://enquiry.indianrail.gov.in/crisns/AppServAnd")
            jsonObject.addProperty("FromStation", cityname)
            val obj = JsonObject()
            obj.addProperty("jsonIn", jsonIn1)
            jsonObject.addProperty("responseString", obj.toString())
            jsonObject.addProperty("key_version", "pnr_ios_key_v1")

            val call = apiInterface.finalstation(jsonObject)

            call?.enqueue(object : Callback<JsonObject?> {

                override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {

                    handler.postDelayed({ //Do something after 5000ms

                        binding.progressCircular.visibility = View.GONE

                    }, 2000)

                    if (response.isSuccessful) {

                        val jsonObject = response.body()

                        if (jsonObject != null) {

                            val jsonObject1 = jsonObject.getAsJsonObject("pair")

                            if (jsonObject1 != null) {

                                first = if (jsonObject1["first"].isJsonNull) null else jsonObject1["first"].asString

                                secpnd = if (jsonObject1["second"].isJsonNull) null else jsonObject1["second"].asString

                                try {

                                    val jsonObject2: JSONObject

                                    if (secpnd != null && secpnd!!.isNotEmpty()) {

                                        jsonObject2 = JSONObject(secpnd)

                                        val jsonArray = jsonObject2.getJSONArray("liveStationModels")

                                        for (i in 0 until jsonArray.length()) {

                                            TrainName = jsonArray.getJSONObject(i).getString("TrainName")
                                            TrainNumber = jsonArray.getJSONObject(i).getString("TrainNumber")
                                            ScheduleArr = jsonArray.getJSONObject(i).getString("ScheduleArr")
                                            ExpectedArr = jsonArray.getJSONObject(i).getString("ExpectedArr")
                                            ExpectedArrColor = jsonArray.getJSONObject(i).getString("ExpectedArrColor")
                                            DelayArr = jsonArray.getJSONObject(i).getString("DelayArr")
                                            DelayArrColor = jsonArray.getJSONObject(i).getString("DelayArrColor")
                                            ScheduleDep = jsonArray.getJSONObject(i).getString("ScheduleDep")
                                            ExpectedDep = jsonArray.getJSONObject(i).getString("ExpectedDep")
                                            ExpectedDepColor = jsonArray.getJSONObject(i).getString("ExpectedDepColor")
                                            DelayDep = jsonArray.getJSONObject(i).getString("DelayDep")
                                            DelayDepColor = jsonArray.getJSONObject(i).getString("DelayDepColor")
                                            ExpPF = jsonArray.getJSONObject(i).getString("ExpPF")


                                            val model = LiveModel(
                                                TrainName,
                                                TrainNumber,
                                                ScheduleArr,
                                                ExpectedArr,
                                                ExpectedArrColor,
                                                DelayArr,
                                                DelayArrColor,
                                                ScheduleDep,
                                                ExpectedDep,
                                                ExpectedDepColor,
                                                DelayDep,
                                                DelayDepColor,
                                                ExpPF)

                                            objects.add(model)

                                        }

                                        mes = jsonObject2.getString("TitleMessage")
                                        binding.text.visibility = View.VISIBLE
                                        binding.tvErrro.visibility = View.GONE
                                        binding.text.text = mes

                                        binding.rvLiveStation.layoutManager = LinearLayoutManager(this@CallLiveStationActivity)
                                        binding.rvLiveStation.adapter = LiveStationAdapter(this@CallLiveStationActivity, objects)

                                    } else {

                                        binding.progressCircular.visibility = View.GONE
                                        binding.text.visibility = View.GONE
                                        binding.tvErrro.visibility = View.VISIBLE
                                        binding.tvErrro.text = first
                                    }

                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                }

                            } else {

                                handler.postDelayed({

                                    binding.progressCircular.visibility = View.GONE
                                    Toast.makeText(this@CallLiveStationActivity, R.string.please_internet, Toast.LENGTH_SHORT).show()

                                }, 2000)
                            }

                        } else {

                            handler.postDelayed({ //Do something after 5000ms

                                binding.progressCircular.visibility = View.GONE

                                Toast.makeText(
                                    this@CallLiveStationActivity,
                                    R.string.please_internet,
                                    Toast.LENGTH_SHORT
                                ).show()

                            }, 2000)
                        }

                    } else {

                        when (response.code()) {

                            404 ->
                                handler.postDelayed({ //Do something after 5000ms

                                    binding.progressCircular.visibility = View.GONE

                                    Toast.makeText(
                                        this@CallLiveStationActivity,
                                        R.string.please_internet,
                                        Toast.LENGTH_SHORT).show()


                                }, 2000)


                            500 ->

                                handler.postDelayed({ //Do something after 5000ms

                                    binding.progressCircular.visibility = View.GONE

                                    Toast.makeText(
                                        this@CallLiveStationActivity,
                                        R.string.please_internet,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }, 2000)


                            else ->

                                handler.postDelayed({ //Do something after 5000ms

                                    binding.progressCircular.visibility = View.GONE

                                    Toast.makeText(
                                        this@CallLiveStationActivity,
                                        R.string.please_internet,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }, 2000)

                        }
                    }
                }

                override fun onFailure(call: Call<JsonObject?>, t: Throwable) {

                    handler.postDelayed({ //Do something after 5000ms

                        binding.progressCircular.visibility = View.GONE

                        Toast.makeText(
                            this@CallLiveStationActivity,
                            "Network failure, Please Try Again",
                            Toast.LENGTH_SHORT
                        ).show()

                    }, 2000)
                }

            })

        } else {

            Toast.makeText(
                this@CallLiveStationActivity,
                R.string.please_internet,
                Toast.LENGTH_SHORT
            ).show()

        }


    }

}