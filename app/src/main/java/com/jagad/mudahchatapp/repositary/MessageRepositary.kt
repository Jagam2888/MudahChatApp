package com.jagad.mudahchatapp.repositary

import android.content.Context
import com.jagad.mudahchatapp.utils.loadJsonDataFromAssets

/**
 * Created by jagad on 8/11/2021
 */
class MessageRepositary(
    private val context: Context
) {

    fun preLoadMessages():String{
        return context.loadJsonDataFromAssets()
    }
}