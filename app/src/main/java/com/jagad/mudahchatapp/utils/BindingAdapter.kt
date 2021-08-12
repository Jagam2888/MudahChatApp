package com.jagad.mudahchatapp.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by jagad on 8/12/2021
 */
@BindingAdapter("ScrollToBottom")
fun scrollToBottom(recyclerView: RecyclerView,position:Int){
    recyclerView.scrollToPosition(position)
}