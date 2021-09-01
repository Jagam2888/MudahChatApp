package com.jagad.mudahchatapp.Data.remote

import android.content.Context
import com.jagad.mudahchatapp.model.request.ChatRequest
import com.jagad.mudahchatapp.model.response.ChatPostMessageResponse
import com.jagad.mudahchatapp.network.RetrofitClientInstance
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by jagad on 8/9/2021
 */
interface ChatPostService {

    @POST("users")
    suspend fun postChatMessage(@Body chatRequest: ChatRequest) : Response<ChatPostMessageResponse>
}