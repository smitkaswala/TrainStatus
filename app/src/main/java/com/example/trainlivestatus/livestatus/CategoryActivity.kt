package com.example.trainlivestatus.livestatus

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.trainlivestatus.R
import com.example.trainlivestatus.databinding.ActivityCategoryBinding
import com.example.trainlivestatus.model.CategoryModel


class CategoryActivity : AppCompatActivity() {


    var list = ArrayList<CategoryModel>()
    private var doubleBackToExitPressedOnce = false


    lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_category)


        binding.setting.setOnClickListener {

            startActivity(Intent(this@CategoryActivity, SettingsActivity::class.java))
        }
        binding.icFareIn.setOnClickListener {
            startActivity(
                Intent(
                    this@CategoryActivity,
                    FairInquiryActivity::class.java
                )
            )
        }
        binding.icSearchTrain.setOnClickListener {
            startActivity(
                Intent(
                    this@CategoryActivity,
                    SearchTrainActivity::class.java
                )
            )
        }
        binding.icTrainSchedule.setOnClickListener {
            startActivity(
                Intent(
                    this@CategoryActivity,
                    TrainscheduleActivity::class.java
                )
            )
        }
        binding.icLiveTrain.setOnClickListener {
            startActivity(
                Intent(
                    this@CategoryActivity,
                    TrainListActivity::class.java
                )
            )
        }
        binding.icSeatAvaila.setOnClickListener {
            startActivity(
                Intent(
                    this@CategoryActivity,
                    SeatAvailableActivity::class.java
                )
            )
        }
        binding.icLiveStation.setOnClickListener {
            startActivity(
                Intent(
                    this@CategoryActivity,
                    LiveStationActivity::class.java
                )
            )
        }

    }


    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce) {

            super@CategoryActivity.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this@CategoryActivity, "Press again to close", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }


}