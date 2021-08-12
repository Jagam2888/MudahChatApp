package com.jagad.mudahchatapp.network


/**
 * Created by jagad on 8/11/2021
 */
sealed class Resource(
    val message: String?=null
) {

    class Loading : Resource()
    class Success(message:String):Resource(message)
    class Failure(message: String):Resource(message)
}