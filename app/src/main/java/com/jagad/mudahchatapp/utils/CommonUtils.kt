package com.jagad.mudahchatapp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by jagad on 8/10/2021
 */

fun Context.toast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
}


fun show(progressBar: ProgressBar){
    if (progressBar.visibility == View.GONE)
        progressBar.visibility = View.VISIBLE
}

fun hide(progressBar: ProgressBar){
    if (progressBar.visibility == View.VISIBLE)
        progressBar.visibility = View.GONE
}

fun Context.loadJsonDataFromAssets():String{
    var jsonData = ""
    try {
        val inputStream = assets.open("chat.json")
        val size = inputStream.available()

        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        jsonData = String(buffer,Charsets.UTF_8)
    }catch (io:IOException){
        io.printStackTrace()
    }

    return jsonData

}

fun changeDateFormat(date:String):String{
    var result = ""
    val currentDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val resultDateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss")
    try {
        val currentDate = currentDateFormat.parse(date)
        result = resultDateFormat.format(currentDate)
    }catch (e:Exception){
        e.printStackTrace()
    }
    return result
}

fun currentDateTime():String{
    var result = ""
    val calender = Calendar.getInstance()
    val currentDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    try {
        result = currentDateFormat.format(calender.time)
    }catch (e:Exception){
        e.printStackTrace()
    }
    return result
}

fun Activity.hideKeyBoard(){
    val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}