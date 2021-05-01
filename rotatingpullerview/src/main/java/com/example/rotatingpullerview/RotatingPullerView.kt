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
    "#f44336",
    "#3F51B5",
    "#FF9800",
    "#006064",
    "#311B92"
).map {
    Color.parseColor(it)
}.toTypedArray()
val parts : Int = 4
val scGap : Float = 0.02f / parts
val delay : Long = 20
val strokeFactor : Float = 90f
val backColor : Int = Color.parseColor("#BDBDBD")
val blockFactor : Float = 9.2f
val hFactor : Float = 4.9f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawRotatingPuller(scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = Math.min(w, h) / blockFactor
    val depth : Float = h / hFactor
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, parts)
    val sf2 : Float = sf.divideScale(1, parts)
    val sf3 : Float = sf.divideScale(2, parts)
    val square : Float = size * sf1
    save()
    translate(w / 2, h / 2)
    drawLine(-w / 2, 0f, -w / 2 +(w / 2) * sf1, -depth * sf3, paint)
    save()
    translate(0f, -depth * sf3)
    drawLine(0f, 0f, 0f, (h / 2 - size) * sf2, paint)
    save()
    translate(0f, (h / 2 - size))
    drawRect(RectF(-square / 2, size / 2 - (square / 2), square / 2, size / 2 + square / 2 ), paint)
    restore()
    restore()
    restore()
}

fun Canvas.drawRPNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawRotatingPuller(scale, w, h, paint)
}
