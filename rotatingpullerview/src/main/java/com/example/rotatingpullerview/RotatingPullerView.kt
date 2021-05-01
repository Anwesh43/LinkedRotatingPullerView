package com.example.rotatingpullerview

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Canvas
import android.graphics.RectF
import android.app.Activity
import android.content.Context

val colors : Array<Int> = arrayOf(
    "",
    "",
    "",
    "",
    ""
).map {
    Color.parseColor(it)
}.toTypedArray()
val parts : Int = 4
val scGap : Float = 0.02f / parts
val delay : Long = 20
val strokeFactor : Float = 90f
val backColor : Int = Color.parseColor("#BDBDBD")
val blockFactor : Float = 0.2f
val hFactor : Float = 4.9f
