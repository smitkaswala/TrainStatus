package com.example.trainlivestatus.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.clicklistner.TrainSeatClick
import com.example.trainlivestatus.model.NameOrCodeModelItem

class RepoAdapter constructor(val context: Context,val trainSeatClick: TrainSeatClick) :
    ListAdapter<NameOrCodeModelItem, RecyclerView.ViewHolder>(DiffUtils) {


    object DiffUtils : DiffUtil.ItemCallback<NameOrCodeModelItem>() {

        override fun areItemsTheSame(
            oldItem: NameOrCodeModelItem,
            newItem: NameOrCodeModelItem
        ): Boolean {
            return oldItem.C == newItem.C
        }

        override fun areContentsTheSame(
            oldItem: NameOrCodeModelItem,
            newItem: NameOrCodeModelItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.train_autocomp_item, parent, false)

        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val photo = getItem(position)

        val holder: MyViewHolder = holder as MyViewHolder

        holder.tvTrainNo.text = photo.C

        holder.tv_train_name.text = photo.N

        holder.tv_source.text = "("+photo.origin+")"+" "+photo.originName

        holder.tv_destination.text = "("+photo.destination+")"+" "+photo.destinationName

        holder.itemView.setOnClickListener {

            trainSeatClick.click(photo)

        }

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvTrainNo: TextView = view.findViewById(R.id.tv_train_no)
        val tv_train_name: TextView = view.findViewById(R.id.tv_train_name)
        val tv_source: TextView = view.findViewById(R.id.tv_source)
        val tv_destination: TextView = view.findViewById(R.id.tv_destination)
    }

}