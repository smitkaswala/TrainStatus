package com.example.trainlivestatus.livestatus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainlivestatus.R
import com.example.trainlivestatus.adapter.QuotaSpinnerAdapter
import com.example.trainlivestatus.adapter.TrainTimeAdapter
import com.example.trainlivestatus.apihelper.ApiInterface
import com.example.trainlivestatus.application.TrainPays
import com.example.trainlivestatus.databinding.ActivityTopSeatCalenderBinding
import com.example.trainlivestatus.databinding.ItemQautaBinding
import com.example.trainlivestatus.livestatus.TrainTimeActivity.Companion.trainname
import com.example.trainlivestatus.livestatus.TrainTimeActivity.Companion.trainnum
import com.example.trainlivestatus.repository.MainRespository
import com.example.trainlivestatus.utils.CommonUtil
import com.example.trainlivestatus.utils.ModelFactory
import com.example.trainlivestatus.utils.TrainQuota
import com.example.trainlivestatus.viewmodel.MainViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TopSeatCalenderActivity : AppCompatActivity() {

    lateinit var binding: ActivityTopSeatCalenderBinding
    private lateinit var mainViewModel: MainViewModel
    val apiInterface: ApiInterface = TrainPays.getClient().create(ApiInterface::class.java)
    var qautabinding: ItemQautaBinding? = null

    companion object {

        var trainNo: String? = null
        var sourceCode: String? = null
        var destinationCode: String? = null
        var trainName: String? = null
        var date: String? = null
        var speakfromlangcode = "GN"
        var datelist: ArrayList<Date> = ArrayList()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_top_seat_calender)

        qautabinding = binding.tvDay

        binding.rvToolbar.setOnClickListener {

            onBackPressed()
        }

        mainViewModel = ViewModelProvider(
            this,
            ModelFactory(MainRespository(apiInterface))
        )[MainViewModel::class.java]

        val intent = intent

        if (intent != null) {

            if (intent.getIntExtra("type", 0) == 1) {

                trainNo = intent.getStringExtra("trainNo")
                sourceCode = intent.getStringExtra("from")
                destinationCode = intent.getStringExtra("to")
                date = intent.getStringExtra("date")
                trainName = intent.getStringExtra("trainName")

                if (getIntent().getStringExtra("mon") == "Y") {
                    binding.tvDay.monday.setTextColor(resources.getColor(R.color.colore_d))
                } else {
                    binding.tvDay.monday.setTextColor(resources.getColor(R.color.colorGray))
                }
                if (getIntent().getStringExtra("tue") == "Y") {
                    binding.tvDay.t.setTextColor(resources.getColor(R.color.colore_d))
                } else {
                    binding.tvDay.t.setTextColor(resources.getColor(R.color.colorGray))
                }
                if (getIntent().getStringExtra("wed") == "Y") {
                    binding.tvDay.w.setTextColor(resources.getColor(R.color.colore_d))
                } else {
                    binding.tvDay.w.setTextColor(resources.getColor(R.color.colorGray))
                }
                if (getIntent().getStringExtra("thu") == "Y") {
                    binding.tvDay.th.setTextColor(resources.getColor(R.color.colore_d))
                } else {
                    binding.tvDay.th.setTextColor(resources.getColor(R.color.colorGray))
                }
                if (getIntent().getStringExtra("fri") == "Y") {
                    binding.tvDay.f.setTextColor(resources.getColor(R.color.colore_d))
                } else {
                    binding.tvDay.f.setTextColor(resources.getColor(R.color.colorGray))
                }
                if (getIntent().getStringExtra("sat") == "Y") {
                    binding.tvDay.s.setTextColor(resources.getColor(R.color.colore_d))
                } else {
                    binding.tvDay.s.setTextColor(resources.getColor(R.color.colorGray))
                }
                if (getIntent().getStringExtra("sun") == "Y") {
                    binding.tvDay.su.setTextColor(resources.getColor(R.color.colore_d))
                } else {
                    binding.tvDay.su.setTextColor(resources.getColor(R.color.colorGray))
                }

                binding.tvtrainnum.text = "$trainNo - $trainName"

                bottomcall(speakfromlangcode)

            }

            if (intent.getIntExtra("type", 0) == 2) {

                trainNo = intent.getStringExtra("trainNo")
                sourceCode = intent.getStringExtra("sourceCode")
                destinationCode = intent.getStringExtra("destinationCode")
                trainName = intent.getStringExtra("trainName")

                @SuppressLint("SimpleDateFormat")
                val formatter: DateFormat = SimpleDateFormat("dd-MM-yyyy")
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis()

                date = formatter.format(calendar.time)
                topitemcall()
                bottomcall(speakfromlangcode)
            }

        }

        trainquotalist()
    }

    @SuppressLint("SetTextI18n")
    fun topitemcall() {

        mainViewModel.topseatcalander(
            sourceCode,
            destinationCode,
            date,
            "ZZ",
            "4c266f54-988a-477d-bd6c-4981c124a80a",
            true,
            "en",
            true)

        mainViewModel.TopcalModelList.observe(this) {

            for (i in it.indices) {

                if (i == 0) {

                    trainnum = it[i].trainNo
                    trainname = it[i].trainName
                    binding.tvtrainnum.text = "$trainnum - $trainname"

                }
            }
        }

        mainViewModel.errorMessage.observe(this) {

            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        mainViewModel.recyclerview_flag.observe(this) {

            if (it) {

                binding.rvLay.visibility = View.VISIBLE

            } else {

                binding.rvLay.visibility = View.GONE
            }
        }

        mainViewModel.showLoadingProg.observe(this) {

            if (it) {

                binding.progressCircular.visibility = View.VISIBLE

            } else {

                binding.progressCircular.visibility = View.GONE
            }
        }

        mainViewModel.mon.observe(this) { integer ->
            binding.tvDay.monday.setTextColor(
                applicationContext.resources.getColor(
                    integer!!
                )
            )
        }

        mainViewModel.tue.observe(
            this
        ) { integer ->

            binding.tvDay.t.setTextColor(
                applicationContext.resources.getColor(
                    integer!!
                )
            )
        }

        mainViewModel.wed.observe(
            this
        ) { integer ->

            binding.tvDay.w.setTextColor(applicationContext.resources.getColor(integer!!))
        }

        mainViewModel.th.observe(
            this
        ) { integer ->

            binding.tvDay.th.setTextColor(applicationContext.resources.getColor(integer!!))
        }

        mainViewModel.fri.observe(
            this
        ) { integer ->

            binding.tvDay.f.setTextColor(
                applicationContext.resources.getColor(
                    integer!!
                )
            )
        }

        mainViewModel.sat.observe(
            this
        ) { integer ->
            binding.tvDay.s.setTextColor(
                applicationContext.resources.getColor(
                    integer!!
                )
            )
        }

        mainViewModel.sun.observe(this) { integer ->
            binding.tvDay.su.setTextColor(
                applicationContext.resources.getColor(
                    integer!!
                )
            )
        }

    }

    fun bottomcall(speakfromlangcode: String) {

        mainViewModel.tarintimecalander(
            trainNo,
            sourceCode,
            destinationCode,
            date,
            "1A,2A,3A,SL",
            speakfromlangcode,
            CommonUtil.api_key
        )
        mainViewModel.monthlyAvaModel.observe(this) {

            for (i in it.indices) {

                val calendar: Calendar = GregorianCalendar()
                calendar.add(Calendar.DATE, i)
                datelist.add(calendar.time)
            }

            val adapter = TrainTimeAdapter(this@TopSeatCalenderActivity, it, datelist)
            binding.rvtimetable.layoutManager = LinearLayoutManager(this@TopSeatCalenderActivity)
            binding.rvtimetable.adapter = adapter
        }

        mainViewModel.calanderLoadingProg.observe(this) {

            if (it) {

                binding.progressCircular.visibility = View.VISIBLE

            } else {

                binding.progressCircular.visibility = View.GONE
            }
        }
        mainViewModel.calandermessage.observe(this) {

            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

    }

    private fun trainquotalist() {

        val arrayList: ArrayList<String> = ArrayList<String>()
        Collections.addAll(arrayList, *TrainQuota.getQutosName())
        val quotaSpinnerAdapter = QuotaSpinnerAdapter(this, arrayList)
        quotaSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spQuata.adapter = quotaSpinnerAdapter
        binding.spQuata.setSelection(0)
        binding.spQuata.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {

                speakfromlangcode = TrainQuota.getQutoscode(binding.spQuata.selectedItemPosition)
                bottomcall(speakfromlangcode)

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

}