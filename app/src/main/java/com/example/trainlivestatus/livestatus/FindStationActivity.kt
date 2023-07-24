package com.example.trainlivestatus.livestatus

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.adapter.FindStationAdapter
import com.example.trainlivestatus.apihelper.ApiInterface
import com.example.trainlivestatus.application.TrainPays
import com.example.trainlivestatus.databinding.ActivityFindStationBinding
import com.example.trainlivestatus.model.FindStationModel
import com.example.trainlivestatus.model.SearchStationsItem
import com.example.trainlivestatus.utils.CommonUtil
import com.example.trainlivestatus.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindStationActivity : BaseClass() {

    lateinit var binding: ActivityFindStationBinding
    var arraylist = mutableListOf<FindStationModel>()
    lateinit var mainViewModel: MainViewModel
    var adapter: FindStationAdapter? = null
    var list = arrayListOf<FindStationModel>()

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_station)

        /*setSupportActionBar(binding.appBarLayout)

        if (supportActionBar != null) {

            supportActionBar?.title = null
            binding.progressCircular.visibility = View.VISIBLE

            findstationcall()
        }*/

        binding.ivBack.setOnClickListener {

            onBackPressed()
        }

        binding.progressCircular.visibility = View.VISIBLE

        binding.ivsearch.setOnClickListener {

            adapter?.clear()
            binding.etSearchTrain.text.clear()
        }

        binding.etSearchTrain.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s.toString().isNotEmpty()) {

                    binding.rv.visibility = View.VISIBLE
                    binding.ivsearch.visibility = View.VISIBLE
                    qfindstationcall(s.toString())

                } else {

                    binding.rv.visibility = View.GONE
                    binding.ivsearch.visibility = View.GONE
                    adapter?.clear()
                    adapter?.notifyDataSetChanged()

                }

            }

            override fun afterTextChanged(s: Editable?) {

            }


        })

        findstationcall()

        /*binding.rv.layoutManager = LinearLayoutManager(this@FindStationActivity)

        binding.rv.adapter = adapter

        mainViewModel = ViewModelProvider(this, ModelFactory(MainRespository(apiInterface)))[MainViewModel::class.java]

        lifecycleScope.launchWhenCreated {


            mainViewModel.ar.observe(this@FindStationActivity, Observer {

                adapter.setdata(it)
            })
        }

        mainViewModel.getfindstationcall()*/

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun findstationcall() {

        if (TrainPays.isNetConnectionAvailable()) {

            val apiInterface: ApiInterface =
                getclient(CommonUtil.SEARCH_STATION_BASE_URL).create(ApiInterface::class.java)

            val call = apiInterface.newSearchStations(false, "trainstationsLatLon", "Surat")

            call.enqueue(object : Callback<List<SearchStationsItem>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<List<SearchStationsItem>>,
                    response: Response<List<SearchStationsItem>>
                ) {

                    binding.progressCircular.visibility = View.GONE

                    if (response.isSuccessful) {

                        val mLayoutManager: RecyclerView.LayoutManager =
                            LinearLayoutManager(this@FindStationActivity)
                        adapter = FindStationAdapter(
                            this@FindStationActivity,
                            response.body() as MutableList<SearchStationsItem>?
                        )
                        binding.rv.layoutManager = mLayoutManager
                        binding.rv.adapter = adapter
                        adapter?.notifyDataSetChanged()
                        binding.rv.isNestedScrollingEnabled = false

                    } else {

                        when (response.code()) {

                            404 -> "404 not found"
                            500 -> "500 server broken"
                            else -> "unknown error"
                        }

                    }


                    /* response.body()?.forEach { it ->

                         val name = it.asJsonObject
                         val citycode = name["a"].asString
                         val cityname = name["e"].asString

                         val stationModel = FindStationModel(citycode, cityname, null)
                         list.add(stationModel)

                         val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@FindStationActivity)
                         adapter = FindStationAdapter(this@FindStationActivity, list)
                         binding.rv.layoutManager = mLayoutManager
                         binding.rv.adapter = adapter
                         adapter?.notifyDataSetChanged()
                         binding.rv.isNestedScrollingEnabled = false

                     }*/


                }

                override fun onFailure(call: Call<List<SearchStationsItem>>, t: Throwable) {

                    Toast.makeText(
                        this@FindStationActivity,
                        "No Data Available",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            })


            /*call?.enqueue(object : Callback<JsonArray?> {

                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<JsonArray?>, response: Response<JsonArray?>) {

                    binding.progressCircular.visibility = View.GONE

                    if (response.body() != null && response.body()!!.size() > 0) {

                        for (i in 0 until response.body()!!.size()) {

                            val name = response.body()!![i].asJsonArray
                            val Citycode = name[0].toString()
                            val Cityname = name[1].toString()
                            val Citylocale = name[2].toString()
                            val stationModel = FindStationModel(Citycode, Cityname, Citylocale)

                            arraylist.add(stationModel)

                            val mLayoutManager: RecyclerView.LayoutManager =
                                LinearLayoutManager(applicationContext)
                            adapter = FindStationAdapter(this@FindStationActivity, arraylist)
                            binding.rv.layoutManager = mLayoutManager
                            binding.rv.adapter = adapter
                            adapter!!.notifyDataSetChanged()
                            binding.rv.isNestedScrollingEnabled = false
                        }

                    } else {

                        binding.progressCircular.visibility = View.GONE
                        Toast.makeText(
                            this@FindStationActivity,
                            R.string.data_availabele,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

                override fun onFailure(call: Call<JsonArray?>, t: Throwable) {

                }
            })*/

        } else {

            binding.progressCircular.visibility = View.GONE
            Toast.makeText(
                this@FindStationActivity,
                R.string.please_internet,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun qfindstationcall(q: String) {

        if (TrainPays.isNetConnectionAvailable()) {

            val apiInterface: ApiInterface =
                getclient(CommonUtil.SEARCH_STATION_BASE_URL).create(ApiInterface::class.java)

            val call = apiInterface.newSearchStations(false, "trainstationsLatLon", q)

            call.enqueue(object : Callback<List<SearchStationsItem>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<List<SearchStationsItem>>,
                    response: Response<List<SearchStationsItem>>
                ) {

                    if (response.isSuccessful) {

                        if (response.body()?.isNotEmpty() == true) {

                            val mLayoutManager: RecyclerView.LayoutManager =
                                LinearLayoutManager(this@FindStationActivity)
                            adapter = FindStationAdapter(
                                this@FindStationActivity,
                                response.body() as MutableList<SearchStationsItem>?
                            )
                            binding.rv.layoutManager = mLayoutManager
                            binding.rv.adapter = adapter
                            adapter?.notifyDataSetChanged()
                            binding.rv.isNestedScrollingEnabled = false

                        } else {

                            Toast.makeText(
                                this@FindStationActivity,
                                "No Data Available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    } else {

                        when (response.code()) {

                            404 -> "404 not found"
                            500 -> "500 server broken"
                            else -> "unknown error"
                        }
                    }


                    /*response.body()?.forEach { it ->

                        val name = it.asJsonObject
                        val citycode = name["a"].toString()
                        val cityname = name["e"].toString()

                        val stationModel = FindStationModel(
                            citycode,
                            cityname, null)
                        list.add(stationModel)

                        val mLayoutManager: RecyclerView.LayoutManager =
                            LinearLayoutManager(this@FindStationActivity)
                        adapter = FindStationAdapter(this@FindStationActivity, list)
                        binding.rv.layoutManager = mLayoutManager
                        binding.rv.adapter = adapter
                        adapter?.notifyDataSetChanged()
                        binding.rv.isNestedScrollingEnabled = false

                    }*/

                    /*if (response.body() != null && response.body()!!.size() > 0) {

                        for (i in 0 until response.body()!!.size()) {

                            val name = response.body()!![i].asJsonObject
                            val citycode = name["a"].toString()
                            val cityname = name["e"].toString()
                            val stationModel = FindStationModel(citycode, cityname, null)
                            list.add(stationModel)

                            val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@FindStationActivity)
                            adapter = FindStationAdapter(this@FindStationActivity, list)
                            binding.rv.layoutManager = mLayoutManager
                            binding.rv.adapter = adapter
                            adapter?.notifyDataSetChanged()
                            binding.rv.isNestedScrollingEnabled = false

                        }
                    } else {

                       *//* handler.postDelayed(Runnable { //Do something after 5000ms
                            pd.setVisibility(View.GONE)
                        }, 2000)*//*
                    }*/

                }

                override fun onFailure(call: Call<List<SearchStationsItem>>, t: Throwable) {

                    Toast.makeText(
                        this@FindStationActivity,
                        "No Data Available",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            })


        } else {

            Toast.makeText(
                this@FindStationActivity,
                R.string.please_internet,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView = menu!!.findItem(R.id.action_search).actionView as SearchView
        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView!!.maxWidth = Int.MAX_VALUE
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                if (query.isNotEmpty()) {

                    adapter?.filter?.filter(query)

                } else {


                }
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }*/


}




