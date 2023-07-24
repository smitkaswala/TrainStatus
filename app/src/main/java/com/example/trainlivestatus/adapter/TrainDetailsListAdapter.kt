package com.example.trainlivestatus.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.databinding.ScheduleDetailsItemBinding
import com.example.trainlivestatus.model.ScheduleItem


class TrainDetailsListAdapter(val context: Context, private val schedule: List<ScheduleItem?>) :
    RecyclerView.Adapter<TrainDetailsListAdapter.TrainDetailsListViewHolder>() {

    class TrainDetailsListViewHolder(itemview: ScheduleDetailsItemBinding) :
        RecyclerView.ViewHolder(itemview.root) {

        val binding: ScheduleDetailsItemBinding = itemview

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainDetailsListViewHolder {

        return TrainDetailsListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.schedule_details_item, parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TrainDetailsListViewHolder, position: Int) {

        holder.binding.apply {

            tvStationName.text = schedule[position]?.stationName

            if (schedule[position]?.arrivalTime.equals(""))
                tvArrivalTime.text = "Source" else tvArrivalTime.text =
                schedule[position]?.arrivalTime

            if (schedule[position]?.departureTime.equals("")) tvDepTime.text =
                "Source" else tvDepTime.text = schedule[position]?.departureTime

            tvDistance.text = schedule[position]?.distance

            if (schedule[position]?.expectedPlatformNo != null) {

                tvPlatformNo.text = schedule[position]?.expectedPlatformNo.toString()
            }

            if (schedule[position]?.arrivalDelay != null) {

                tvDelay.text = schedule[position]?.arrivalDelay

            } else {

                tvDelay.text = "-"
            }

            tvDay.text = schedule[position]?.day.toString()

        }

    }

    override fun getItemCount(): Int {

        return schedule.size
    }
}