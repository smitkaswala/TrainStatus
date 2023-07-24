package com.example.trainlivestatus.model

data class LiveModel(

    var trainName: String? = null,
    var trainNumber: String? = null,
    var scheduleArr: String? = null,
    var expectedArr: String? = null,
    var expectedArrColor: String? = null,
    var delayArr: String? = null,
    var delayArrColor: String? = null,
    var scheduleDep: String? = null,
    var expectedDep: String? = null,
    var expectedDepColor: String? = null,
    var delayDep: String? = null,
    var delayDepColor: String? = null,
    var expPF: String? = null

)
