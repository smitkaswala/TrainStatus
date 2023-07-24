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
import com.example.trainlivestatus.adapter.TrainScheduleAdapter
import com.example.trainlivestatus.apihelper.ApiInterface
import com.example.trainlivestatus.application.TrainPays
import com.example.trainlivestatus.databinding.ActivityIntermediatestnBinding
import com.example.trainlivestatus.repository.MainRespository
import com.example.trainlivestatus.utils.ModelFactory
import com.example.trainlivestatus.viewmodel.MainViewModel

class IntermediatestnActivity : AppCompatActivity() {

    lateinit var binding: ActivityIntermediatestnBinding
    lateinit var mainViewModel: MainViewModel
    var trainNo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_intermediatestn)


        binding.rvToolbar.setOnClickListener {

            onBackPressed()
        }

        trainNo = intent.getStringExtra("trainNo")

        val apiInterface: ApiInterface = TrainPays.getClient().create(ApiInterface::class.java)

        mainViewModel =
            ViewModelProvider(
                this,
                ModelFactory(MainRespository(apiInterface))
            )[MainViewModel::class.java]

        mainViewModel.schedulewithintermediatestn(trainNo)

        mainViewModel.trainlist.observe(this) {

            /* if (jsonObject1["first"].isJsonNull) null else jsonObject1["first"].asString*/

            binding.apply {


                rv.layoutManager = LinearLayoutManager(this@IntermediatestnActivity)
                rv.adapter = TrainScheduleAdapter(this@IntermediatestnActivity, it, trainNo)

            }


        }

        mainViewModel.errorMessage.observe(this, Observer {

            Toast.makeText(this@IntermediatestnActivity, it, Toast.LENGTH_SHORT).show()
        })

        mainViewModel.showLoadingProg.observe(
            this
        ) {

            if (it) {

                binding.progressCircular.visibility = View.VISIBLE
            } else {

                binding.progressCircular.visibility = View.GONE
            }
        }


    }
}