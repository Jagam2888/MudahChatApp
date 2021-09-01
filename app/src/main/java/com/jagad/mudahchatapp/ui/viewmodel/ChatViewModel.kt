package com.jagad.mudahchatapp.ui.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.google.gson.Gson
import com.jagad.mudahchatapp.listener.SimpleListener
import com.jagad.mudahchatapp.model.request.ChatRequest
import com.jagad.mudahchatapp.Data.local.Chat
import com.jagad.mudahchatapp.model.response.ChatResponse
import com.jagad.mudahchatapp.network.Resource
import com.jagad.mudahchatapp.repositary.ChatRepositary
import com.jagad.mudahchatapp.repositary.MessageRepositary
import com.jagad.mudahchatapp.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * Created by jagad on 8/10/2021
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepositary: ChatRepositary,
    private val messageRepositary: MessageRepositary
):ViewModel() {

    private val _messageList:MutableLiveData<List<Chat>> = MutableLiveData()
    val messageList:LiveData<List<Chat>>
    get() = _messageList

    private val _postMessageResponse:MutableLiveData<Resource> = MutableLiveData()
    val postMessageResponse:LiveData<Resource>
    get() = _postMessageResponse

    val txtSendMsg = ObservableField<String>()

    var simpleListener:SimpleListener?=null

    private var isCancel = false

    init {
        preLoadMsgFromAssets()
        timer()
    }

    fun loadMessageList() = viewModelScope.launch{
        _messageList.value = chatRepositary.getMessages()
    }

    fun insertNewChat(chat: Chat) = viewModelScope.launch {
        chatRepositary.insertPreLoadChatMessage(chat)
    }

    private fun preLoadMsgFromAssets()  = viewModelScope.launch {
                if (chatRepositary.getMessages().isEmpty()){
                    val parseChatJson = Gson().fromJson(messageRepositary.preLoadMessages(),ChatResponse::class.java)
                    parseChatJson.chat.forEach {insertNewChat(it)}
                    _messageList.value = parseChatJson.chat
                }else {
                    loadMessageList()
                }
        }


    private fun timer() = viewModelScope.launch {
            delay(60000)
        if (!isCancel) {
            simpleListener?.onTimeFinished()
        }
    }

    private fun cancelJob(){
        isCancel = true
    }

    fun sendMsg(){
        if (!txtSendMsg.get().isNullOrEmpty()) {

            Couritnes.main {
                try {
                    _postMessageResponse.value = Resource.Loading()

                    val response =  chatRepositary.postMessage(ChatRequest(txtSendMsg.get()!!))
                    if (!response.message.isNullOrEmpty()) {
                        insertNewChat(Chat("OUTGOING",txtSendMsg.get()!!,response.createdAt))
                        txtSendMsg.set("")
                    }
                    cancelJob()
                    _postMessageResponse.value = Resource.Success("Success")
                    loadMessageList()
                } catch (e: APIException) {
                    _postMessageResponse.value = Resource.Failure(e.message!!)
                } catch (e: NoInternetException) {
                    _postMessageResponse.value = Resource.Failure(e.message!!)
                }
            }
        }
    }



    fun addTempMsg(): Chat {
        return Chat("INCOMING","Are You there?",currentDateTime())
    }
}