package com.example.trainlivestatus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.trainlivestatus.R
import com.example.trainlivestatus.clicklistner.ItemClick
import com.example.trainlivestatus.model.CategoryModel

class DashbAdapter(val context: Context, val list: List<CategoryModel>, private val itemclick: ItemClick) : RecyclerView.Adapter<DashbAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_category_icon, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        list[position].image?.let { holder.ivimage.load(it) }

        holder.itemView.setOnClickListener {

            itemclick.click(position)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(Itemview: View) : RecyclerView.ViewHolder(Itemview) {

        val ivimage: ImageView = itemView.findViewById(R.id.ivimage)
    }
}
