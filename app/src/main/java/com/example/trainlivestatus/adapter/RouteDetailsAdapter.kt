package com.example.trainlivestatus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.databinding.ItemSeatRoutDetailsHeaderBinding
import com.example.trainlivestatus.databinding.TrainScheduledRvItemBinding
import com.example.trainlivestatus.model.TrainBtwnStnsListItem

class RouteDetailsAdapter(
    val context: Context,
    val list: List<TrainBtwnStnsListItem>,
    var date: String?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        const val TYPE_DESTINATION = 0
        const val TYPE_LIST = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TYPE_DESTINATION) {

            return DestinationViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_seat_rout_details_header,
                    parent,
                    false
                )
            )

        } else {

            return ScheduleListViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.train_scheduled_rv_item,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {

            TYPE_DESTINATION -> {

                val holder1 = holder as DestinationViewHolder
                holder1.binding.apply {

                    tvFromStation.text = list[0].fromStnCode
                    tvToStation.text = list[0].toStnCode
                }

            }

            TYPE_LIST -> {

                val holder2 = holder as ScheduleListViewHolder
                holder2.binding.apply {

                    rvScheduled.layoutManager = LinearLayoutManager(context)
                    rvScheduled.adapter = RouteDetailsListAdapter(context, list, date)
                }

            }
        }

    }

    override fun getItemCount(): Int {

        return 2
    }

    override fun getItemViewType(position: Int): Int {

        return if (position == TYPE_DESTINATION) TYPE_DESTINATION else TYPE_LIST
    }

    class DestinationViewHolder(itemView: ItemSeatRoutDetailsHeaderBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        val binding: ItemSeatRoutDetailsHeaderBinding = itemView
    }

    class ScheduleListViewHolder(itemView: TrainScheduledRvItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        val binding: TrainScheduledRvItemBinding = itemView
    }

}