package com.example.trainlivestatus.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.clicklistner.TrainClickListener
import com.example.trainlivestatus.databinding.TrainNameItemBinding
import java.util.*

class SearchTrainAdapter(
    val context: Context, var list: ArrayList<String>,
    private val trainClickListener: TrainClickListener,
    private var fList: ArrayList<String>? = null
) : RecyclerView.Adapter<SearchTrainAdapter.TrainNoViewHolder>(), Filterable {

    private var f1: String? = null
    private var f2: String? = null
    private var f3: String? = null
    private var f4: String? = null

    init {

        this.fList = list
    }

    class TrainNoViewHolder(itemView: TrainNameItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        val binding: TrainNameItemBinding = itemView

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainNoViewHolder {

        return TrainNoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.train_name_item,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TrainNoViewHolder, position: Int) {

        holder.binding.apply {

            val s = list[position]
            val split = s.split("/").toTypedArray()
            val s1 = split[0]
            val s2 = split[1]

            val t1 = s2.split("-").toTypedArray()

            when (t1.size) {
                2 -> {
                    f1 = t1[0]
                    f2 = t1[1]
                    tvTrainNo.text = s1
                    tvTrainName.text = "$f1-$f2"

                }
                3 -> {
                    f1 = t1[0]
                    f2 = t1[1]
                    f3 = t1[2]
                    tvTrainNo.text = s1
                    tvTrainName.text = "$f1-$f2-$f3"
                }
                4 -> {
                    f1 = t1[0]
                    f2 = t1[1]
                    f3 = t1[2]
                    f4 = t1[3]
                    tvTrainNo.text = s1
                    tvTrainName.text = "$f1-$f2-$f3-$f4"
                }
            }

            holder.itemView.setOnClickListener {


                trainClickListener.onTrainClick(s1, "$f3 - $f4")
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getFilter(): Filter {

        return imageFilter
    }

    fun clear() {

        list.clear()
    }

    private val imageFilter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<String> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(fList!!)
            } else {
                val filteredPattern =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (s in fList!!) {

                    if (s.lowercase(Locale.getDefault()).contains(filteredPattern)) {
                        filteredList.add(s)
                    }
                }
            }

            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {

            /*list.addAll(results.values as MutableList<String>)*/

            list = results.values as ArrayList<String>
            notifyDataSetChanged()
        }
    }
}