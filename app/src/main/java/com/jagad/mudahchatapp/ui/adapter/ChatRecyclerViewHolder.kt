package com.jagad.mudahchatapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.jagad.mudahchatapp.databinding.ChatItemIncomingBinding
import com.jagad.mudahchatapp.databinding.ChatItemOutgoingBinding
import com.jagad.mudahchatapp.Data.local.Chat
import com.jagad.mudahchatapp.utils.changeDateFormat

/**
 * Created by jagad on 8/10/2021
 */
sealed class ChatRecyclerViewHolder(
    binding:ViewBinding
):RecyclerView.ViewHolder(binding.root) {

    class IncomingMessageHolder(private val binding:ChatItemIncomingBinding):ChatRecyclerViewHolder(binding){
        fun bind(receiveMsg: Chat){
            binding.textView.text = receiveMsg.message
            binding.txtDate.text = changeDateFormat(receiveMsg.timestamp)
        }
    }

    class OutGoingMessageHolder(private val binding: ChatItemOutgoingBinding):ChatRecyclerViewHolder(binding){
        fun bind(sendMsg: Chat){
            binding.textView.text = sendMsg.message
            binding.txtDate.text = changeDateFormat(sendMsg.timestamp)
        }
    }
}