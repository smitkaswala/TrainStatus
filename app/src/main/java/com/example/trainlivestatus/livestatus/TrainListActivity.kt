package com.example.trainlivestatus.livestatus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainlivestatus.R
import com.example.trainlivestatus.adapter.SearchTrainAdapter
import com.example.trainlivestatus.clicklistner.TrainClickListener
import com.example.trainlivestatus.databinding.ActivityTrainListBinding
import com.example.trainlivestatus.utils.CommonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TrainListActivity : AppCompatActivity() {

    lateinit var binding: ActivityTrainListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_train_list)

        binding.rvToolbar.setNavigationOnClickListener {

            onBackPressed()
        }

        val trainList = CommonUtil.getTrainList(this@TrainListActivity)
        val type = object : TypeToken<ArrayList<String?>?>() {}.type
        val list = Gson().fromJson<ArrayList<String>>(trainList, type)

        binding.apply {

            rvSearchTrain.layoutManager = LinearLayoutManager(this@TrainListActivity)
            rvSearchTrain.setHasFixedSize(true)
            val adapter = SearchTrainAdapter(
                this@TrainListActivity,
                list,
                object : TrainClickListener {

                    override fun onTrainClick(trainNo: String?, TrainName: String?) {
                        val intent = Intent(this@TrainListActivity, LiveTrainActivity::class.java)
                        intent.putExtra("trainNo", trainNo)
                        intent.putExtra("trainName", TrainName)
                        startActivity(intent)
                    }

                })

            rvSearchTrain.adapter = adapter

            etSearchTrain.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                    adapter.filter.filter(s)


                }

                override fun afterTextChanged(s: Editable) {}
            })

        }
    }
}