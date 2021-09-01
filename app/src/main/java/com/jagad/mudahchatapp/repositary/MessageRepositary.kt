package com.jagad.mudahchatapp.repositary

import android.content.Context
import com.jagad.mudahchatapp.utils.loadJsonDataFromAssets
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by jagad on 8/11/2021
 */
class MessageRepositary @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun preLoadMessages():String{
        return context.loadJsonDataFromAssets()
    }
}