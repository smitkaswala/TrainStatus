package com.example.trainlivestatus.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.model.SpinnerModel
import java.util.*

class SpinnerAdapters(val context: Activity, val list: ArrayList<SpinnerModel>) :
    ArrayAdapter<SpinnerModel>(
        context, R.layout.train_spinner_item,
        list
    ) {


    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val inflater = context.layoutInflater

        val rowView = inflater.inflate(R.layout.train_spinner_item, null, true)

        val textView = rowView.findViewById<TextView>(R.id.tv_train)

        Log.e("000", "getView: ${list[position].trainNo + "-" + list[position].trainName}")
        textView.text = list[position].trainNo + "-" + list[position].trainName

        return rowView
    }

    /*override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getViewItem(position, convertView!!, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getViewItem(position, convertView!!, parent)
    }

    @SuppressLint("SetTextI18n", "InflateParams")
    fun getViewItem(position: Int, view: View, parent: ViewGroup?): View {

        var view = view

        view = LayoutInflater.from(context).inflate(R.layout.train_spinner_item, null)

        val textView = view.findViewById<TextView>(R.id.tv_train)

        textView.text =
            list[position].trainNo.toString() + "-" + list[position].trainName.toString()
        return view
    }*/
}