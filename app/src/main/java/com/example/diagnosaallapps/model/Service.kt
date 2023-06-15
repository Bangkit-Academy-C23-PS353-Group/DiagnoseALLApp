package com.example.diagnosaallapps.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.diagnosaallapps.R

data class Service(
    val imageService: Int,
    val textService: String,
)

val dummyServices = listOf(
    Service(R.drawable.bloodcheck, "Blood Check"),
    Service(R.drawable.bloodcheck, "Diagnose"),
    Service(R.drawable.bloodcheck, "History"),
)
