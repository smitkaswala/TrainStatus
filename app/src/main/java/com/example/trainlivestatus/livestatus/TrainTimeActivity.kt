package com.example.trainlivestatus.livestatus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainlivestatus.R
import com.example.trainlivestatus.adapter.QuotaSpinnerAdapter
import com.example.trainlivestatus.adapter.SpinnerAdaptera
import com.example.trainlivestatus.adapter.TrainTimeAdapter
import com.example.trainlivestatus.apihelper.ApiInterface
import com.example.trainlivestatus.application.TrainPays
import com.example.trainlivestatus.databinding.ActivityTrainTimeBinding
import com.example.trainlivestatus.databinding.ItemQautaBinding
import com.example.trainlivestatus.model.SpinnerModel
import com.example.trainlivestatus.repository.MainRespository
import com.example.trainlivestatus.utils.CommonUtil
import com.example.trainlivestatus.utils.ModelFactory
import com.example.trainlivestatus.utils.TrainQuota
import com.example.trainlivestatus.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class TrainTimeActivity : AppCompatActivity() {

    lateinit var binding: ActivityTrainTimeBinding
    var qautaBinding: ItemQautaBinding? = null
    lateinit var mainViewModel: MainViewModel
    var names: ArrayList<SpinnerModel> = ArrayList()
    var datelist: ArrayList<Date> = ArrayList()

    @SuppressLint("SimpleDateFormat")
    companion object {

        @SuppressLint("SimpleDateFormat")
        val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        var trainnum: String? = null
        var trainname: String? = null
        var date: String? = null
        var to: String? = null
        var from: String? = null

        val apiInterface: ApiInterface = TrainPays.getClient().create(ApiInterface::class.java)
    }

    var speakfromlangcode = "GN"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_train_time_)

        qautaBinding = binding.tvDay

        binding.rvToolbar.setNavigationOnClickListener {

            onBackPressed()
        }

        binding.rvToolbar.setOnMenuItemClickListener {

                when (it.itemId) {

                    R.id.share -> {

                    }
                }
                true
        }

        val intent = intent

        if (intent != null) {

            date = intent.getStringExtra("date")
            to = intent.getStringExtra("citycode1")
            from = intent.getStringExtra("citycode")
            binding.tvfrom.text = from
            binding.tvto.text = to

            topitemcall()
            TrainquotaList()

        }




    }

    @SuppressLint("SetTextI18n")
    fun topitemcall() {

        mainViewModel = ViewModelProvider(this, ModelFactory(MainRespository(apiInterface)))[MainViewModel::class.java]

        mainViewModel.topseatcalander(from, to, date, "ZZ", "4c266f54-988a-477d-bd6c-4981c124a80a", true, "en", true)

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

        mainViewModel.TopcalModelList.observe(this) {

            for (i in it.indices) {

                if (i == 0) {

                    trainnum = it[i].trainNo
                    trainname = it[i].trainName
                    binding.tvtrainnum.text = "$trainnum - $trainname"
                    getTrainCalender(speakfromlangcode)
                }

                names.add(SpinnerModel(it[i].trainNo, it[i].trainName, it[i].daysOfRun))
                spinner(names)
            }
        }


        mainViewModel.mon.observe(
            this
        ) { integer ->
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
                    integer!!)
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

    private fun spinner(name: ArrayList<SpinnerModel>) {

        val spinnerAdapter = SpinnerAdaptera(this, name)

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.sptrainame.adapter = spinnerAdapter

        binding.sptrainame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            @SuppressLint("SetTextI18n")
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {

                val spinnerModel = adapterView.getItemAtPosition(i) as SpinnerModel

                binding.tvtrainnum.text = spinnerModel.trainNo + " - " + spinnerModel.trainName

                trainnum = spinnerModel.trainNo

                getTrainCalender(speakfromlangcode)

                if (!spinnerModel.daysOfRun!!.mon!!) {
                    qautaBinding!!.monday.setTextColor(resources.getColor(R.color.colorGray))
                } else {
                    qautaBinding!!.monday.setTextColor(resources.getColor(R.color.colore_d))
                }
                if (!spinnerModel.daysOfRun.tue!!) {
                    qautaBinding!!.t.setTextColor(resources.getColor(R.color.colorGray))
                } else {
                    qautaBinding!!.t.setTextColor(resources.getColor(R.color.colore_d))
                }
                if (!spinnerModel.daysOfRun.wed!!) {
                    qautaBinding!!.w.setTextColor(resources.getColor(R.color.colorGray))
                } else {
                    qautaBinding!!.w.setTextColor(resources.getColor(R.color.colore_d))
                }
                if (!spinnerModel.daysOfRun.thu!!) {
                    qautaBinding!!.th.setTextColor(resources.getColor(R.color.colorGray))
                } else {
                    qautaBinding!!.th.setTextColor(resources.getColor(R.color.colore_d))
                }
                if (!spinnerModel.daysOfRun.fri!!) {
                    qautaBinding!!.f.setTextColor(resources.getColor(R.color.colorGray))
                } else {
                    qautaBinding!!.f.setTextColor(resources.getColor(R.color.colore_d))
                }
                if (!spinnerModel.daysOfRun.sat!!) {
                    qautaBinding!!.s.setTextColor(resources.getColor(R.color.colorGray))
                } else {
                    qautaBinding!!.s.setTextColor(resources.getColor(R.color.colore_d))
                }
                if (!spinnerModel.daysOfRun.sun!!) {
                    qautaBinding!!.su.setTextColor(resources.getColor(R.color.colorGray))
                } else {
                    qautaBinding!!.su.setTextColor(resources.getColor(R.color.colore_d))
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

    private fun getTrainCalender(speakfromlangcode: String) {

        mainViewModel.tarintimecalander(trainnum, from, to, date, "1A,2A,3A,SL", speakfromlangcode, CommonUtil.api_key)

        mainViewModel.monthlyAvaModel.observe(this) {

            for (i in it.indices) {

                val calendar: Calendar = GregorianCalendar()
                calendar.add(Calendar.DATE, i)
                datelist.add(calendar.time)
            }

            val adapter = TrainTimeAdapter(this@TrainTimeActivity, it, datelist)
            binding.rvtimetable.layoutManager = LinearLayoutManager(this@TrainTimeActivity)
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

    private fun TrainquotaList() {

        val arrayList: ArrayList<String> = ArrayList<String>()
        Collections.addAll(arrayList, *TrainQuota.getQutosName())
        val quotaSpinnerAdapter = QuotaSpinnerAdapter(this, arrayList)
        quotaSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spQuata.adapter = quotaSpinnerAdapter
        binding.spQuata.setSelection(0)
        binding.spQuata.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {

                speakfromlangcode = TrainQuota.getQutoscode(binding.spQuata.selectedItemPosition)
                getTrainCalender(speakfromlangcode)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }






}