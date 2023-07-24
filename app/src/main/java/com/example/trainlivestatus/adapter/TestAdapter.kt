package com.example.trainlivestatus.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.clicklistner.TrainSeatClick
import com.example.trainlivestatus.model.NameOrCodeModelItem

class TestAdapter(val list: MutableList<NameOrCodeModelItem>, val trainSeatClick: TrainSeatClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.train_autocomp_item, parent, false)

        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val photo = list[position]

        val holder: MyViewHolder = holder as MyViewHolder

        holder.tvTrainNo.text = photo.C

        holder.tv_train_name.text = photo.N

        holder.tv_source.text = "("+photo.origin+")"+" "+photo.originName

        holder.tv_destination.text = "(" + photo.destination + ")" + " " + photo.destinationName

        holder.itemView.setOnClickListener {

            trainSeatClick.click(photo)
        }
    }

    override fun getItemCount(): Int {

        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {

        list.clear()
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvTrainNo: TextView = view.findViewById(R.id.tv_train_no)
        val tv_train_name: TextView = view.findViewById(R.id.tv_train_name)
        val tv_source: TextView = view.findViewById(R.id.tv_source)
        val tv_destination: TextView = view.findViewById(R.id.tv_destination)
    }
}