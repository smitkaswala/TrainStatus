package com.example.trainlivestatus.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.clicklistner.LiveTrainClick
import com.example.trainlivestatus.databinding.LiveTrainItemBinding
import com.example.trainlivestatus.model.StationsItem

class LiveTrainAdapter(
    val context: Context,
    private val stationsItemList: List<StationsItem>,
    val p: Int,
    itemClickLisner: LiveTrainClick
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {

        if (p >= 0 && p != 0) {

            itemClickLisner.click(
                p + 1,
                stationsItemList[p].stnCodeName.toString() + " - " + stationsItemList[p].stnCode,
                stationsItemList[p].actDep,
                stationsItemList[p].delayDep
            )

        } else {

            itemClickLisner.click(p + 1, null, null, 0)
        }
    }


    class LiveTrainViewHolder(itemview: LiveTrainItemBinding) :
        RecyclerView.ViewHolder(itemview.root) {

        val itemLayoutBinding: LiveTrainItemBinding = itemview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return LiveTrainViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.live_train_item,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val liveTrainViewHolder = holder as LiveTrainViewHolder

        liveTrainViewHolder.itemLayoutBinding.apply {

            tvStationName.text = stationsItemList[position].stnCodeName.toString() + " (" + stationsItemList[position].stnCode + ")"
            tvEstArrival.text = stationsItemList[position].schArrTime
            tvActArrival.text = stationsItemList[position].actArr
            tvEstDepart.text = stationsItemList[position].schDepTime
            tvActDepa.text = stationsItemList[position].actDep
            tvDistance.text = stationsItemList[position].distance.toString() + " kms"
            tvDelay.text = getTime(stationsItemList[position].delayArr)

            val pf = stationsItemList[position].pfNo!!

            if (pf == 0) tvPf.text = "PF -" else tvPf.text = "PF $pf"

            if (position == p && p != 0) {

                ivDot.visibility = View.GONE
                dotAnim.visibility = View.VISIBLE
                tvStationName.setTextColor(Color.parseColor("#000000"))

            } else {

                tvStationName.setTextColor(Color.parseColor("#5B5B7E"))
                dotAnim.visibility = View.GONE
                ivDot.visibility = View.VISIBLE
            }
        }

    }

    override fun getItemCount(): Int {

        return stationsItemList.size
    }

    private fun getTime(time: Int?): String {

        return if (time != 0) {
            val hours = time?.div(60)
            val minute = time?.rem(60)
            "$hours:$minute"
        } else {
            "-"
        }
    }
}
