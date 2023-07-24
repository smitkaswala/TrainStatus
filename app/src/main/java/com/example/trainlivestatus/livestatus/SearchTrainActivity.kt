package com.example.trainlivestatus.livestatus

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainlivestatus.R
import com.example.trainlivestatus.adapter.SearchTrainAdapter
import com.example.trainlivestatus.clicklistner.TrainClickListener
import com.example.trainlivestatus.databinding.ActivitySearchTrainBinding
import com.example.trainlivestatus.utils.CommonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchTrainActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchTrainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_train)

        binding.rvToolbar.setNavigationOnClickListener {

            onBackPressed()
        }

        val trainList = CommonUtil.getTrainList(this@SearchTrainActivity)
        val type = object : TypeToken<ArrayList<String?>?>() {}.type
        val list = Gson().fromJson<ArrayList<String>>(trainList, type)

        binding.apply {

            rvSearchTrain.layoutManager = LinearLayoutManager(this@SearchTrainActivity)
            rvSearchTrain.setHasFixedSize(true)

            val adapter = SearchTrainAdapter(this@SearchTrainActivity, list,
                object : TrainClickListener {
                    override fun onTrainClick(trainNo: String?, TrainName: String?) {

                        val intent =
                            Intent(this@SearchTrainActivity, IntermediatestnActivity::class.java)
                        intent.putExtra("trainNo", trainNo)
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

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    adapter.filter.filter(s)
                }

                override fun afterTextChanged(s: Editable) {}
            })

        }

    }

    /*private fun getTrainList(): String? {
        val json: String?
        try {
            val `is` = this.assets.open("trains.json")
            var size = 0
            size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return json
    }*/


}