package com.example.trainlivestatus.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.databinding.RouteDetailsListItemBinding
import com.example.trainlivestatus.livestatus.IntermediatestnActivity
import com.example.trainlivestatus.livestatus.LiveTrainActivity
import com.example.trainlivestatus.livestatus.TopSeatCalenderActivity
import com.example.trainlivestatus.model.TrainBtwnStnsListItem

class RouteDetailsListAdapter(
    val context: Context,
    private val filteredList: List<TrainBtwnStnsListItem>,
    val date: String?
) : RecyclerView.Adapter<RouteDetailsListAdapter.TrainDetailsViewHolder>() {


    companion object {

        var avalClass = ArrayList<String>()
    }

    class TrainDetailsViewHolder(itemView: RouteDetailsListItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        val binding: RouteDetailsListItemBinding = itemView

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainDetailsViewHolder {

        return TrainDetailsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.route_details_list_item, parent, false
            )
        )

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TrainDetailsViewHolder, position: Int) {

        val listItem: TrainBtwnStnsListItem = filteredList[position]

        holder.binding.apply {


            if (filteredList[position].runningSun.equals("Y")) {

                cbsun.background =
                    ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                cbsun.setTextColor(context.resources.getColor(R.color.white))
            }

            if (filteredList[position].runningMon.equals("Y")) {

                cbmon.background =
                    ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                cbmon.setTextColor(context.resources.getColor(R.color.white))
            }

            if (filteredList[position].runningTue.equals("Y")) {

                cbtue.background =
                    ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                cbtue.setTextColor(context.resources.getColor(R.color.white))
            }

            if (filteredList[position].runningWed.equals("Y")) {

                cbwed.background =
                    ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                cbwed.setTextColor(context.resources.getColor(R.color.white))
            }

            if (filteredList[position].runningThu.equals("Y")) {

                cbthu.background =
                    ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                cbthu.setTextColor(context.resources.getColor(R.color.white))
            }

            if (filteredList[position].runningFri.equals("Y")) {

                cbfri.background =
                    ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                cbfri.setTextColor(context.resources.getColor(R.color.white))
            }

            if (filteredList[position].runningSat.equals("Y")) {

                cbsat.background =
                    ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                cbsat.setTextColor(context.resources.getColor(R.color.white))
            }

            avalClass.clear()
            avalClass = listItem.avlClasses?.array as ArrayList<String>

            for (i in avalClass.indices) {

                if (avalClass[i] == "1A") {

                    ch1a.background =
                        ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                    ch1a.setTextColor(context.resources.getColor(R.color.white))
                }

                if (avalClass[i] == "2A") {

                    ch2a.background =
                        ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                    ch2a.setTextColor(context.resources.getColor(R.color.white))
                }

                if (avalClass[i] == "3A") {

                    ch3a.background =
                        ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                    ch3a.setTextColor(context.resources.getColor(R.color.white))
                }

                if (avalClass[i] == "CC") {

                    chCc.background =
                        ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                    chCc.setTextColor(context.resources.getColor(R.color.white))
                }

                if (avalClass[i] == "EA") {

                    chEa.background =
                        ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                    chEa.setTextColor(context.resources.getColor(R.color.white))
                }

                if (avalClass[i] == "EC") {

                    chEc.background =
                        ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                    chEc.setTextColor(context.resources.getColor(R.color.white))
                }

                if (avalClass[i] == "2S") {

                    ch2s.background =
                        ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                    ch2s.setTextColor(context.resources.getColor(R.color.white))
                }

                if (avalClass[i] == "SL") {

                    chSl.background =
                        ContextCompat.getDrawable(context, R.drawable.custom_checkbox_full)
                    chSl.setTextColor(context.resources.getColor(R.color.white))
                }
            }


            
            farerv.layoutManager = GridLayoutManager(context, 2)
            farerv.adapter =
                FareAdapter(context, filteredList[position].avaiblitycache, avalClass)

            tvsource.text = listItem.fromStnCode
            tvdestinations.text = listItem.toStnCode
            tvdep.text = listItem.departureTime
            tvarrive.text = listItem.arrivalTime
            tvtrainname.text = listItem.trainName.toString() + "-" + listItem.trainNumber
            tvdistance.text = listItem.distance.toString() + " KM"
            tvDuration.text = listItem.duration.toString() + " HRS"

            val time: List<String> = listItem.duration?.split(":")!!

            val hrs = time[0].toInt()
            val minute = time[1].toInt()
            val totalMinute = hrs * 60 + minute
            val i = listItem.distance!! * 60 / totalMinute.toFloat()
            tvSpeed.text = String.format("%.2f", i) + " Km/hr"

            btnlive.setOnClickListener {

                val intent = Intent(context, LiveTrainActivity::class.java)
                intent.putExtra("trainNo", listItem.trainNumber)
                intent.putExtra("trainName", listItem.trainName)
                context.startActivity(intent)

            }


            btnSeatAvab.setOnClickListener {

                val intent = Intent(context, TopSeatCalenderActivity::class.java)
                intent.putExtra("type", 1)
                intent.putExtra("trainNo", listItem.trainNumber)
                intent.putExtra("from", listItem.fromStnCode)
                intent.putExtra("to", listItem.toStnCode)
                intent.putExtra("date", date)
                intent.putExtra("trainName", listItem.trainName)
                intent.putExtra("mon", listItem.runningMon)
                intent.putExtra("tue", listItem.runningTue)
                intent.putExtra("wed", listItem.runningWed)
                intent.putExtra("thu", listItem.runningThu)
                intent.putExtra("fri", listItem.runningFri)
                intent.putExtra("sat", listItem.runningSat)
                intent.putExtra("sun", listItem.runningSun)
                context.startActivity(intent)
            }

            btnRouteSchedule.setOnClickListener {

                val intent = Intent(context, IntermediatestnActivity::class.java)
                intent.putExtra("trainNo", listItem.trainNumber)
                context.startActivity(intent)
            }
        }


    }

    override fun getItemCount(): Int {

        return filteredList.size
    }


}