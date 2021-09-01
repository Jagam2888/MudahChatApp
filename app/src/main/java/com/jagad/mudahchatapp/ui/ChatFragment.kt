package com.jagad.mudahchatapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.jagad.mudahchatapp.R
import com.jagad.mudahchatapp.databinding.FragmentChatBinding
import com.jagad.mudahchatapp.listener.SimpleListener
import com.jagad.mudahchatapp.network.Resource
import com.jagad.mudahchatapp.ui.adapter.ChatAdapter
import com.jagad.mudahchatapp.ui.viewmodel.ChatViewModel
import com.jagad.mudahchatapp.utils.hide
import com.jagad.mudahchatapp.utils.hideKeyBoard
import com.jagad.mudahchatapp.utils.show
import com.jagad.mudahchatapp.utils.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat),SimpleListener {

    private val chatViewModel: ChatViewModel by viewModels()

    private lateinit var chatBinding:FragmentChatBinding

    private lateinit var chatAdapter:ChatAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatBinding = FragmentChatBinding.bind(view)

        chatBinding.chatviewmodel = chatViewModel

        chatViewModel.simpleListener = this

        chatAdapter = ChatAdapter()
        chatBinding.adapter = chatAdapter

        viewModelObserver()
    }


    private fun viewModelObserver(){
        chatViewModel.messageList.observe(viewLifecycleOwner, {list->
            chatAdapter.chatList = list
        })

        chatViewModel.postMessageResponse.observe(viewLifecycleOwner,{response ->
            when(response){
                is Resource.Loading -> show(chatBinding.progressCircular)
                is Resource.Success -> {
                    hide(chatBinding.progressCircular)
                    activity?.hideKeyBoard()
                }
                is Resource.Failure -> {
                    hide(chatBinding.progressCircular)
                    activity?.hideKeyBoard()
                    context?.toast(response.message!!)
                }
            }
        })
    }

    override fun onTimeFinished() {
        chatAdapter.addNewItem(chatViewModel.addTempMsg())
    }
}