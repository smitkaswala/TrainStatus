package com.example.trainlivestatus.clicklistner

import com.example.trainlivestatus.model.NameOrCodeModelItem

interface TrainSeatClick {

    fun click(photo: NameOrCodeModelItem)
}