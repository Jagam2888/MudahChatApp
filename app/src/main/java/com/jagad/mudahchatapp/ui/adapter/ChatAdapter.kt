package com.jagad.mudahchatapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import com.jagad.mudahchatapp.R
import com.jagad.mudahchatapp.databinding.ChatItemIncomingBinding
import com.jagad.mudahchatapp.databinding.ChatItemOutgoingBinding
import com.jagad.mudahchatapp.Data.local.Chat
import java.lang.IllegalArgumentException

/**
 * Created by jagad on 8/10/2021
 */
class ChatAdapter():RecyclerView.Adapter<ChatRecyclerViewHolder>() {

    val scrollTo = ObservableInt()
    var chatList = listOf<Chat>()
    set(value) {
        field = value
        scrollTo.set(field.size-1)
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(holder: ChatRecyclerViewHolder, position: Int) {
        if (chatList[position].direction == "INCOMING"){
            (holder as ChatRecyclerViewHolder.IncomingMessageHolder).bind(chatList[position])
        }else{
            (holder as ChatRecyclerViewHolder.OutGoingMessageHolder).bind(chatList[position])
        }
    }

    override fun getItemCount() = chatList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRecyclerViewHolder {
        return when(viewType){
            R.layout.chat_item_incoming -> ChatRecyclerViewHolder.IncomingMessageHolder(
                ChatItemIncomingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.chat_item_outgoing -> ChatRecyclerViewHolder.OutGoingMessageHolder(
                ChatItemOutgoingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Error")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(chatList[position].direction){
            "INCOMING" -> R.layout.chat_item_incoming
            "OUTGOING" -> R.layout.chat_item_outgoing
            else -> throw IllegalArgumentException("Error")
        }
    }

    fun addNewItem(chat: Chat){
        val tempList = mutableListOf<Chat>()
        tempList.addAll(chatList)
        tempList.add(chat)
        chatList = tempList
    }

}