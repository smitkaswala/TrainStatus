package com.example.trainlivestatus.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.databinding.FindStationItemBinding
import com.example.trainlivestatus.model.FindStationModel
import java.util.*

class StationAdapter constructor(val context: Activity) :
    ListAdapter<FindStationModel, RecyclerView.ViewHolder>(DiffUtils), Filterable {

    private var list = mutableListOf<FindStationModel>()
    var contactListFiltered=mutableListOf<FindStationModel>()
    object DiffUtils : DiffUtil.ItemCallback<FindStationModel>() {

        override fun areItemsTheSame(
            oldItem: FindStationModel,
            newItem: FindStationModel
        ): Boolean {
            return oldItem.citycode == newItem.citycode
        }

        override fun areContentsTheSame(
            oldItem: FindStationModel,
            newItem: FindStationModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding: FindStationItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.find_station_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val photo = getItem(position)

        val holder: MyViewHolder = holder as MyViewHolder

        var cityname = photo.ecityname
        cityname = cityname!!.replace("\"", "")
        var citycode = photo.citycode
        citycode = citycode!!.replace("\"", "")


        holder.itemLayoutBinding.cityname.text = cityname + "\u0020" + citycode

        var citylocal = photo.citylocale
        citylocal = citylocal!!.replace("\"", "")
        holder.itemLayoutBinding.citylocal.text = citylocal

        holder.itemView.setOnClickListener {
            val intent = Intent()
            intent.putExtra("citycode", photo.citycode)
            intent.putExtra("cityname", photo.ecityname)
            context.setResult(Activity.RESULT_OK, intent)
            context.finish()
        }

    }

    class MyViewHolder(itemview: FindStationItemBinding) : RecyclerView.ViewHolder(itemview.root) {

        val itemLayoutBinding: FindStationItemBinding = itemview
    }

    override fun getFilter(): Filter {

        return customFilter
    }

    fun setdata(it: List<FindStationModel>?) {

        this.list = it as MutableList<FindStationModel>
        this.contactListFiltered=list
        submitList(list)
    }


    private val customFilter = object : Filter() {

        override fun performFiltering(p0: CharSequence?): FilterResults {

            val results = FilterResults()

            if (p0 != null && p0.isNotEmpty()) {

                val filterList = mutableListOf<FindStationModel>()


                for (i in contactListFiltered.indices) {

                    if (contactListFiltered[i].ecityname!!.lowercase(Locale.getDefault()).contains(p0.toString().lowercase(Locale.getDefault()))
                    ) {
                        filterList.add(contactListFiltered[i])
                    }
                }

                results.count = filterList.size
                results.values = filterList

            } else {

                results.count = contactListFiltered.size

                results.values = contactListFiltered
            }

            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(p0: CharSequence?, results: FilterResults?) {

            list = results?.values as MutableList<FindStationModel>
            submitList(list)
        }


    }


}

