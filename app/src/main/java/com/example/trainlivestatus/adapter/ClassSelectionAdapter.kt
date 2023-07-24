package com.example.trainlivestatus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.databinding.ClassSelectionItemBinding
import com.example.trainlivestatus.model.ClassSelectionModel

class ClassSelectionAdapter(
    val context: Context,
    val list: List<ClassSelectionModel>
) : RecyclerView.Adapter<ClassSelectionAdapter.ClassViewHolder>() {

    private var checkedPosition = -1

    class ClassViewHolder(itemView: ClassSelectionItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        val itemLayoutBinding: ClassSelectionItemBinding = itemView
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClassViewHolder {

        return ClassViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.class_selection_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {

        holder.itemLayoutBinding.apply {

            if (checkedPosition == -1) {

                cb.isChecked = false

            } else {

                cb.isChecked = checkedPosition == holder.adapterPosition
            }

            cb.text = list[position].name

            holder.itemView.setOnClickListener {

                cb.isChecked = true

                if (checkedPosition != holder.adapterPosition) {

                    notifyItemChanged(checkedPosition)

                    checkedPosition = holder.adapterPosition

                } else {

                    checkedPosition = -1
                    cb.isChecked = false
                    notifyItemChanged(checkedPosition)
                }
            }


        }
    }

    override fun getItemCount(): Int {

        return list.size
    }


    fun getSelected(): String? {

        val s: String?

        if (checkedPosition != -1) {

            s = list[checkedPosition].name

            return s
        }
        return null
    }
}