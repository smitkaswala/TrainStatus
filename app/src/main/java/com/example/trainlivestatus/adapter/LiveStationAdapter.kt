package com.example.trainlivestatus.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.databinding.LiveStation1Binding
import com.example.trainlivestatus.livestatus.LiveTrainActivity
import com.example.trainlivestatus.model.LiveModel


class LiveStationAdapter(val context: Context, val list: List<Any?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class LiveStationHolder(itemView: LiveStation1Binding) :
        RecyclerView.ViewHolder(itemView.root) {

        val itemLayoutBinding: LiveStation1Binding = itemView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return LiveStationHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.live_station_1,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val liveModel: LiveModel = list[position] as LiveModel
        val holder1 = holder as LiveStationHolder

        holder1.itemLayoutBinding.apply {

            if (!TextUtils.isEmpty(liveModel.scheduleArr)) {

                tvScheduleArr.text = liveModel.scheduleArr

            } else {

                tvScheduleArr.text = ""
            }


            if (!TextUtils.isEmpty(liveModel.expectedArr)) {

                tvExpArr.setTextColor(Color.parseColor(liveModel.expectedArrColor))
                tvExpArr.text = liveModel.expectedArr

            } else {

                tvExpArr.text = ""
            }

            if (!TextUtils.isEmpty(liveModel.delayArr)) {

                tvDelayArr.setTextColor(Color.parseColor(liveModel.delayArrColor))
                tvDelayArr.text = liveModel.delayArr

            } else {

                tvDelayArr.text = ""
            }


            if (!TextUtils.isEmpty(liveModel.scheduleDep)) {
                tvSchDep.text = liveModel.scheduleDep
            } else {
                tvSchDep.text = ""
            }


            if (!TextUtils.isEmpty(liveModel.expectedDep)) {
                tvExpDep.setTextColor(Color.parseColor(liveModel.expectedDepColor))
                tvExpDep.text = liveModel.expectedDep
            } else {
                tvExpDep.text = ""
            }


            if (!TextUtils.isEmpty(liveModel.delayDep)) {
                tvDelayDep.setTextColor(Color.parseColor(liveModel.delayDepColor))
                tvDelayDep.text = liveModel.delayDep
            } else {
                tvDelayDep.text = ""
            }


            if (!TextUtils.isEmpty(liveModel.trainName)) {
                tvTrainName.text = liveModel.trainName
            } else {
                tvTrainName.text = ""
            }


            liveStatusDirect.setOnClickListener {

                val intent = Intent(context, LiveTrainActivity::class.java)
                intent.putExtra("trainNo", liveModel.trainNumber)
                context.startActivity(intent)

            }

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}