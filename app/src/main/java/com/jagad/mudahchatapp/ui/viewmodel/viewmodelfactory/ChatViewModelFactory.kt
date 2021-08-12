package com.jagad.mudahchatapp.ui.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jagad.mudahchatapp.repositary.ChatRepositary
import com.jagad.mudahchatapp.repositary.MessageRepositary
import com.jagad.mudahchatapp.ui.viewmodel.ChatViewModel

/**
 * Created by jagad on 8/10/2021
 */
class ChatViewModelFactory(
    private val chatRepositary: ChatRepositary,
    private val messageRepositary: MessageRepositary
):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel(chatRepositary,messageRepositary) as T
    }
}