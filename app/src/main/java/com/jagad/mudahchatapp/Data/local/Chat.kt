package com.jagad.mudahchatapp.Data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chat(
    val direction: String,
    val message: String,
    val timestamp: String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}