package com.example.trainlivestatus.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.databinding.FindStationItemBinding
import com.example.trainlivestatus.model.SearchStationsItem
import java.util.*
import java.util.regex.Pattern

class FindStationAdapter(
    var context: Activity,
    var arrayList: MutableList<SearchStationsItem>?) : RecyclerView.Adapter<FindStationAdapter.Holder>() {

    /*init {

        this.contactListFiltered = arrayList
    }*/

    companion object {

        var code: String? = null
    }

    class Holder(itemview: FindStationItemBinding) : RecyclerView.ViewHolder(itemview.root) {

        val itemLayoutBinding: FindStationItemBinding = itemview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding: FindStationItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.find_station_item,
            parent,
            false
        )
        return Holder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        var cityname = arrayList?.get(position)?.E
        cityname = cityname!!.replace("\"", "")

        holder.itemLayoutBinding.cityname.text = cityname

        val citylocal = arrayList?.get(position)?.E
        val regex = "^([^()]*)\\(([^()]*)\\)(.*)$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(citylocal.toString())


        holder.itemView.setOnClickListener {

            if (matcher.matches()) {
                code = matcher.group(2)?.trim()
                val intent = Intent()
                intent.putExtra("citycode", code)
                intent.putExtra("cityname", arrayList?.get(position)?.E)
                context.setResult(Activity.RESULT_OK, intent)
                context.finish()
            }


        }

    }

    override fun getItemCount(): Int {

        return arrayList?.size!!
    }


    @SuppressLint("NotifyDataSetChanged")
    fun clear() {

        arrayList?.clear()
        notifyDataSetChanged()
    }



}