package com.example.trainlivestatus.livestatus

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainlivestatus.R
import com.example.trainlivestatus.adapter.RouteDetailsAdapter
import com.example.trainlivestatus.apihelper.ApiInterface
import com.example.trainlivestatus.databinding.ActivityRouteDetailsBinding
import com.example.trainlivestatus.repository.MainRespository
import com.example.trainlivestatus.utils.CommonUtil
import com.example.trainlivestatus.utils.ModelFactory
import com.example.trainlivestatus.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RouteDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityRouteDetailsBinding
    var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_route_details)

        binding.rvToolbar.setNavigationOnClickListener {

            onBackPressed()
        }

        val apiInterface: ApiInterface = getClient().create(ApiInterface::class.java)

        val intent = intent

        val from = intent.getStringExtra("citycode")
        val to = intent.getStringExtra("citycode1")
        val date = intent.getStringExtra("date")

            mainViewModel = ViewModelProvider(this, ModelFactory(MainRespository(apiInterface)))[MainViewModel::class.java]

        mainViewModel?.safeBreakingNewsCall(from, to, date)

        mainViewModel?.topcalmodellist?.observe(this) {

            binding.rvRoutDetails.layoutManager = LinearLayoutManager(this@RouteDetailsActivity)
            binding.rvRoutDetails.adapter = RouteDetailsAdapter(this@RouteDetailsActivity, it, date)

        }

        mainViewModel?.errorMessage?.observe(this) { s ->
            Toast.makeText(
                this@RouteDetailsActivity,
                s,
                Toast.LENGTH_SHORT
            ).show()
        }

        mainViewModel?.showLoadingProg?.observe(this) { aBoolean ->
            if (aBoolean) {

                binding.progressCircular.visibility = View.VISIBLE
            } else {

                binding.progressCircular.visibility = View.GONE
            }
        }

        mainViewModel?.no_available_errormessage?.observe(this, Observer {

            Toast.makeText(
                this@RouteDetailsActivity,
                it,
                Toast.LENGTH_SHORT
            ).show()
        })

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