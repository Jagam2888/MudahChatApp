package com.jagad.mudahchatapp.repositary

import com.jagad.mudahchatapp.Data.local.ChatDatabase
import com.jagad.mudahchatapp.Data.remote.ChatPostService
import com.jagad.mudahchatapp.model.request.ChatRequest
import com.jagad.mudahchatapp.Data.local.Chat
import com.jagad.mudahchatapp.model.response.ChatPostMessageResponse
import com.jagad.mudahchatapp.network.SafeApiRequest
import javax.inject.Inject

/**
 * Created by jagad on 8/10/2021
 */
class ChatRepositary @Inject constructor(
    private val api:ChatPostService,
    private val database:ChatDatabase
):SafeApiRequest() {

    suspend fun postMessage(chatRequest: ChatRequest):ChatPostMessageResponse{
        return apiRequest {
            api.postChatMessage(chatRequest)
        }
    }

    suspend fun insertPreLoadChatMessage(chat: Chat){
        database.getDao().insertPreLoadMessages(chat)
    }

    suspend fun getMessages():List<Chat>{
        return database.getDao().getMessages()
    }

}