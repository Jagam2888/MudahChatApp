package com.jagad.mudahchatapp.network

import com.jagad.mudahchatapp.utils.APIException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

/**
 * Created by jagad on 8/10/2021
 */
abstract class SafeApiRequest {
    suspend fun <T:Any>apiRequest(call : suspend () -> Response<T>): T {
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            val error = response.errorBody()?.string()
            val msg = StringBuilder()
            error?.let {
                try {
                    msg.append(JSONObject(it).getString("id"))
                }catch (e : JSONException){

                }
            }
            throw APIException(msg.toString())
        }
    }
}