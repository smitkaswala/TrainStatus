package com.example.trainlivestatus.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.databinding.FareItemBinding
import com.example.trainlivestatus.model.Avaiblitycache
import com.example.trainlivestatus.model.FareModel

class FareAdapter(
    val context: Context,
    avaiblitycache: Avaiblitycache?,
    avalClass: ArrayList<String>

) : RecyclerView.Adapter<FareAdapter.FareItemViewHolder>() {

    var fareList = ArrayList<FareModel>()

    init {

        for (i in avalClass.indices) {

            if (avalClass[i] == "1A") {

                if (avaiblitycache!!.jsonMember1A != null) {

                    fareList.add(FareModel("1A", avaiblitycache.jsonMember1A?.fare))
                }
            }
            if (avalClass[i] == "2A") {

                if (avaiblitycache!!.jsonMember2A != null) {

                    if (avaiblitycache.jsonMember2A?.fare != null) {

                        fareList.add(FareModel("2A", avaiblitycache.jsonMember2A.fare))
                    }
                }
            }
            if (avalClass[i] == "3A") {

                if (avaiblitycache!!.jsonMember3A != null) {

                    fareList.add(FareModel("3A", avaiblitycache.jsonMember3A?.fare))
                }
            }

            if (avalClass[i] == "CC") {

                if (avaiblitycache!!.cC != null) {
                    fareList.add(FareModel("CC", avaiblitycache.cC?.fare))
                }
            }
            if (avalClass[i] == "EA") {

                if (avaiblitycache!!.eA != null) {
                    fareList.add(FareModel("EA", avaiblitycache.eA?.fare))
                }
            }
            if (avalClass[i] == "EC") {

                if (avaiblitycache!!.eC != null) {
                    fareList.add(FareModel("EC", avaiblitycache.eC?.fare))
                }
            }
            if (avalClass[i] == "2S") {

                if (avaiblitycache!!.jsonMember2S != null) {
                    fareList.add(FareModel("2S", avaiblitycache.jsonMember2S?.fare))
                }
            }
            if (avalClass[i] == "SL") {

                if (avaiblitycache!!.sL != null) {
                    fareList.add(FareModel("SL", avaiblitycache.sL?.fare))
                }
            }
        }

    }

    class FareItemViewHolder(itemView: FareItemBinding) : RecyclerView.ViewHolder(itemView.root) {

        val binding: FareItemBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FareItemViewHolder {

        return FareItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.fare_item,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FareItemViewHolder, position: Int) {

        holder.binding.apply {

            checkbox.text = fareList[position].text
            tvFare.text = "₹" + fareList[position].fare
        }


    }

    override fun getItemCount(): Int {

        return fareList.size
    }
}
