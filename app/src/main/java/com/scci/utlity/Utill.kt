package com.scci.utlity

import android.content.Context
import java.text.DecimalFormat


object Utill {

    var mSound: SoundPlay? = null
    var precision: DecimalFormat = DecimalFormat("0.00")


    fun ringSoundFail(context: Context?) {
        try {
            if (mSound == null) {
                mSound = SoundPlay(context)
            }
            mSound!!.playSuccess()
        } catch (exception: java.lang.Exception) {
            exception.printStackTrace()
        }

    }
    fun change(valuedata: Double, decimalpoint: Int): String {

        // Using the pow() method
        try {
            var value = valuedata
            value = value * Math.pow(10.0, decimalpoint.toDouble())
            value = Math.floor(value)
            value = value / Math.pow(10.0, decimalpoint.toDouble())

            return precision.format(value)
        } catch (e: Exception) {
            return precision.format(valuedata)
        }
    }


}