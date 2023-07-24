package com.example.trainlivestatus.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trainlivestatus.R
import com.example.trainlivestatus.databinding.TableItemBinding
import com.example.trainlivestatus.livestatus.TrainTimeActivity
import com.example.trainlivestatus.trainavaimodel.SeatAvailabilityModel
import com.example.trainlivestatus.utils.PrettyTimeAgo
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TrainTimeAdapter(
    val context: Context,
    val list: List<SeatAvailabilityModel>,
    private val datelist: List<Date>
) :
    RecyclerView.Adapter<TrainTimeAdapter.CalenderViewHolder>() {


    class CalenderViewHolder(itemView: TableItemBinding) : RecyclerView.ViewHolder(itemView.root) {

        val binding: TableItemBinding = itemView
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalenderViewHolder {

        return CalenderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.table_item,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {

        holder.binding.apply {

            try {

                tvday.text = setDateFormat(TrainTimeActivity.DATE_FORMAT.format(datelist[position]))
                val dayweek = setDateDay(TrainTimeActivity.DATE_FORMAT.format(datelist[position]))
                weekday.text = dayweek?.substring(0, 3)
                Log.e("TAG", "onBindViewHolder: ${dayweek?.substring(0, 3)}" )

            } catch (e: ParseException) {
                e.printStackTrace()
            }

            list[position].also {

                if (it.jsonMember1A != null) {

                    val str1date = timeage(it.jsonMember1A.cacheTime)

                    tvnotrun.visibility = View.GONE
                    tv1aPrediction.visibility = View.VISIBLE
                    a1CurrentStatus.visibility = View.VISIBLE
                    tv1ago.visibility = View.VISIBLE

                    if (it.jsonMember1A.currentStatus != null) {

                        a1CurrentStatus.text = it.jsonMember1A.currentStatus

                    } else {

                        a1CurrentStatus.visibility = View.VISIBLE
                        a1CurrentStatus.text = "-"
                    }

                    if (it.jsonMember1A.prediction != null) {

                        if (it.jsonMember1A.prediction.equals("Available", ignoreCase = true)) {

                            tv1aPrediction.setTextColor(context.resources.getColor(R.color.green))

                        } else {

                            tv1aPrediction.setTextColor(context.resources.getColor(R.color.app_red))
                        }

                        tv1aPrediction.text = it.jsonMember1A.prediction

                    } else {

                        tv1aPrediction.visibility = View.VISIBLE
                        tv1aPrediction.text = "--"
                    }

                    if (str1date != null) {

                        tv1ago.text = "($str1date)"

                    } else {

                        tv1ago.visibility = View.VISIBLE
                        tv1ago.text = "---"
                    }

                } else {

                    tvnotrun.visibility = View.VISIBLE
                    tv1aPrediction.visibility = View.GONE
                    a1CurrentStatus.visibility = View.GONE
                    tv1ago.visibility = View.GONE
                }


                if (it.jsonMember2A != null) {

                    val str2date = timeage(it.jsonMember2A.cacheTime)

                    tv2notrun.visibility = View.GONE
                    tv2aPrediction.visibility = View.VISIBLE
                    a2CurrentStatus.visibility = View.VISIBLE
                    tv2ago.visibility = View.VISIBLE

                    if (it.jsonMember2A.currentStatus != null) {

                        a2CurrentStatus.text = it.jsonMember2A.currentStatus

                    } else {

                        a2CurrentStatus.visibility = View.VISIBLE
                        a2CurrentStatus.text = "-"
                    }

                    if (it.jsonMember2A.prediction != null) {

                        if (it.jsonMember2A.prediction.equals(
                                "Available",
                                ignoreCase = true
                            )
                        ) {
                            tv2aPrediction.setTextColor(context.resources.getColor(R.color.green))
                        } else {
                            tv2aPrediction.setTextColor(context.resources.getColor(R.color.app_red))
                        }

                        tv2aPrediction.text = it.jsonMember2A.prediction

                    } else {

                        tv2aPrediction.visibility = View.VISIBLE
                        tv2aPrediction.text = "--"
                    }
                    if (str2date != null) {

                        tv2ago.text = "($str2date)"

                    } else {

                        tv2ago.visibility = View.VISIBLE
                        tv2ago.text = "---"
                    }
                } else {

                    tv2notrun.visibility = View.VISIBLE
                    tv2aPrediction.visibility = View.GONE
                    a2CurrentStatus.visibility = View.GONE
                    tv2ago.visibility = View.GONE
                }


                if (it.jsonMember3A != null) {

                    val str3date = timeage(it.jsonMember3A.cacheTime)

                    tv3notrun.visibility = View.GONE
                    tv3aPrediction.visibility = View.VISIBLE
                    a3CurrentStatus.visibility = View.VISIBLE
                    tv3ago.visibility = View.VISIBLE

                    if (it.jsonMember3A.currentStatus != null) {

                        a3CurrentStatus.text = it.jsonMember3A.currentStatus

                    } else {

                        a3CurrentStatus.visibility = View.VISIBLE
                        a3CurrentStatus.text = "-"
                    }

                    if (it.jsonMember3A.prediction != null) {

                        if (it.jsonMember3A.prediction.equals(
                                "Available",
                                ignoreCase = true
                            )
                        ) {
                            tv3aPrediction.setTextColor(context.resources.getColor(R.color.green))
                        } else {
                            tv3aPrediction.setTextColor(context.resources.getColor(R.color.app_red))
                        }
                        tv3aPrediction.text = it.jsonMember3A.prediction
                    } else {

                        tv3aPrediction.visibility = View.VISIBLE
                        tv3aPrediction.text = "--"
                    }
                    if (str3date != null) {

                        tv3ago.text = "($str3date)"

                    } else {

                        tv3ago.visibility = View.VISIBLE
                        tv3ago.text = "---"
                    }
                } else {

                    tv3notrun.visibility = View.VISIBLE
                    tv3aPrediction.visibility = View.GONE
                    a3CurrentStatus.visibility = View.GONE
                    tv3ago.visibility = View.GONE
                }


                if (it.sL != null) {

                    val strsldate = timeage(it.sL.cacheTime)

                    tvslnotrun.visibility = View.GONE
                    tvSlPrediction.visibility = View.VISIBLE
                    slCurrentStatus.visibility = View.VISIBLE
                    tvslago.visibility = View.VISIBLE

                    if (it.sL.currentStatus != null) {

                        slCurrentStatus.text = it.sL.currentStatus
                    } else {

                        slCurrentStatus.visibility = View.VISIBLE
                        slCurrentStatus.text = "-"
                    }
                    if (it.sL.prediction != null) {

                        if (it.sL.prediction.equals("Available", ignoreCase = true)) {

                            tvSlPrediction.setTextColor(context.resources.getColor(R.color.green))

                        } else {

                            tvSlPrediction.setTextColor(context.resources.getColor(R.color.app_red))
                        }
                        tvSlPrediction.text = it.sL.prediction
                    } else {

                        tvSlPrediction.visibility = View.VISIBLE
                        tvSlPrediction.text = "--"
                    }
                    if (strsldate != null) {
                        tvslago.text = "($strsldate)"
                    } else {
                        tvslago.visibility = View.VISIBLE
                        tvslago.text = "---"
                    }
                } else {

                    tvslnotrun.visibility = View.VISIBLE
                    tvSlPrediction.visibility = View.GONE
                    slCurrentStatus.visibility = View.GONE
                    tvslago.visibility = View.GONE
                }


            }


            /* item.monthlyAvaModel.let {

               *//*  if (it?.get(position)?.jsonMember1A != null) {

                    val str_1date = timeage(it[position]?.jsonMember1A?.cacheTime)

                    tvnotrun.visibility = View.GONE
                    tv1aPrediction.visibility = View.VISIBLE
                    a1CurrentStatus.visibility = View.VISIBLE
                    tv1ago.visibility = View.VISIBLE

                    if (it[position]?.jsonMember1A?.currentStatus != null) {

                        a1CurrentStatus.text = it[position]?.jsonMember1A?.currentStatus
                        Log.e("ss", "onBindViewHolder: ${it[position]?.jsonMember1A?.currentStatus}" )

                    } else {

                        a1CurrentStatus.visibility = View.VISIBLE
                        a1CurrentStatus.text = "-"
                    }

                    if (it[position]?.jsonMember1A?.prediction != null) {

                        if (it[position]?.jsonMember1A?.prediction.equals(
                                "Available",
                                ignoreCase = true
                            )
                        ) {

                            tv1aPrediction.setTextColor(context.resources.getColor(R.color.green))

                        } else {

                            tv1aPrediction.setTextColor(context.resources.getColor(R.color.app_red))
                        }

                        tv1aPrediction.text = it[position]?.jsonMember1A?.prediction

                    } else {

                        tv1aPrediction.visibility = View.VISIBLE
                        tv1aPrediction.text = "--"
                    }

                    if (str_1date != null) {

                        tv1ago.text = "($str_1date)"

                    } else {

                        tv1ago.visibility = View.VISIBLE
                        tv1ago.text = "---"
                    }

                } else {

                    tvnotrun.visibility = View.VISIBLE
                    tv1aPrediction.visibility = View.GONE
                    a1CurrentStatus.visibility = View.GONE
                    tv1ago.visibility = View.GONE
                }
*//*


                *//*if (it?.get(position)?.jsonMember2A != null) {

                    val str_2date = timeage(it[position]?.jsonMember2A?.cacheTime)

                    tv2notrun.visibility = View.GONE
                    tv2aPrediction.visibility = View.VISIBLE
                    a2CurrentStatus.visibility = View.VISIBLE
                    tv2ago.visibility = View.VISIBLE

                    if (it[position]?.jsonMember2A?.currentStatus != null) {

                        a2CurrentStatus.text = it[position]?.jsonMember2A?.currentStatus

                    } else {

                        a2CurrentStatus.visibility = View.VISIBLE
                        a2CurrentStatus.text = "-"
                    }

                    if (it[position]?.jsonMember2A?.prediction != null) {

                        if (it[position]?.jsonMember2A?.prediction.equals(
                                "Available",
                                ignoreCase = true
                            )
                        ) {
                            tv2aPrediction.setTextColor(context.resources.getColor(R.color.green))
                        } else {
                            tv2aPrediction.setTextColor(context.resources.getColor(R.color.app_red))
                        }

                        tv2aPrediction.text = it[position]?.jsonMember2A?.prediction

                    } else {

                        tv2aPrediction.visibility = View.VISIBLE
                        tv2aPrediction.text = "--"
                    }
                    if (str_2date != null) {

                        tv2ago.text = "($str_2date)"

                    } else {

                        tv2ago.visibility = View.VISIBLE
                        tv2ago.text = "---"
                    }
                } else {

                    tv2notrun.visibility = View.VISIBLE
                    tv2aPrediction.visibility = View.GONE
                    a2CurrentStatus.visibility = View.GONE
                    tv2ago.visibility = View.GONE
                }


                if (it?.get(position)?.jsonMember3A != null) {

                    val str_3date = timeage(it[position]?.jsonMember3A?.cacheTime)

                    tv3notrun.visibility = View.GONE
                    tv3aPrediction.visibility = View.VISIBLE
                    a3CurrentStatus.visibility = View.VISIBLE
                    tv3ago.visibility = View.VISIBLE

                    if (it[position]?.jsonMember3A?.currentStatus != null) {

                        a3CurrentStatus.text = it[position]?.jsonMember3A?.currentStatus

                    } else {

                        a3CurrentStatus.visibility = View.VISIBLE
                        a3CurrentStatus.text = "-"
                    }

                    if (it[position]?.jsonMember3A?.prediction != null) {

                        if (it[position]?.jsonMember3A?.prediction.equals(
                                "Available",
                                ignoreCase = true
                            )
                        ) {
                            tv3aPrediction.setTextColor(context.resources.getColor(R.color.green))
                        } else {
                            tv3aPrediction.setTextColor(context.resources.getColor(R.color.app_red))
                        }
                        tv3aPrediction.text = it[position]?.jsonMember3A?.prediction
                    } else {

                        tv3aPrediction.visibility = View.VISIBLE
                        tv3aPrediction.text = "--"
                    }
                    if (str_3date != null) {

                        tv3ago.text = "($str_3date)"

                    } else {

                        tv3ago.visibility = View.VISIBLE
                        tv3ago.text = "---"
                    }
                } else {

                    tv3notrun.visibility = View.VISIBLE
                    tv3aPrediction.visibility = View.GONE
                    a3CurrentStatus.visibility = View.GONE
                    tv3ago.visibility = View.GONE
                }



                if (it?.get(position)?.sL != null) {

                    val str_sldate = timeage(it[position]?.sL?.cacheTime)

                    tvslnotrun.visibility = View.GONE
                    tvSlPrediction.visibility = View.VISIBLE
                    slCurrentStatus.visibility = View.VISIBLE
                    tvslago.visibility = View.VISIBLE

                    if (it[position]?.sL?.currentStatus != null) {

                        slCurrentStatus.text = it[position]?.sL?.currentStatus
                    } else {

                        slCurrentStatus.visibility = View.VISIBLE
                        slCurrentStatus.text = "-"
                    }
                    if (it[position]?.sL?.prediction != null) {

                        if (it[position]?.sL?.prediction.equals("Available", ignoreCase = true)) {

                            tvSlPrediction.setTextColor(context.resources.getColor(R.color.green))

                        } else {

                            tvSlPrediction.setTextColor(context.resources.getColor(R.color.app_red))
                        }
                        tvSlPrediction.text = it[position]?.sL?.prediction
                    } else {

                        tvSlPrediction.visibility = View.VISIBLE
                        tvSlPrediction.text = "--"
                    }
                    if (str_sldate != null) {
                        tvslago.text = "($str_sldate)"
                    } else {
                        tvslago.visibility = View.VISIBLE
                        tvslago.text = "---"
                    }
                } else {

                    tvslnotrun.visibility = View.VISIBLE
                    tvSlPrediction.visibility = View.GONE
                    slCurrentStatus.visibility = View.GONE
                    tvslago.visibility = View.GONE
                }*//*


            }*/


        }


    }

    override fun getItemCount(): Int {

        return list.size
    }


    @SuppressLint("SimpleDateFormat")
    @Throws(ParseException::class)
    fun setDateFormat(unformattedDate: String?): String? {
        @SuppressLint("SimpleDateFormat") val dateformat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(unformattedDate.toString())
        return SimpleDateFormat("dd MMM").format(dateformat)
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(ParseException::class)
    fun setDateDay(unformattedDate: String?): String? {
        @SuppressLint("SimpleDateFormat") val dateformat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(unformattedDate.toString())
        return SimpleDateFormat("EEEE").format(dateformat)
    }

    fun timeage(settime: String?): String? {

        @SuppressLint("SimpleDateFormat") val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        var timago: String? = null
        var date: Date? = null
        try {
            date = formatter.parse(settime.toString()) as Date
            var output: Long = 0
            output = date.time / 1000L
            val str = output.toString()
            val timestamp = str.toLong()
            timago = PrettyTimeAgo.getTimeAgo(timestamp)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return timago
    }

}