package com.jagad.mudahchatapp.Data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by jagad on 8/9/2021
 */
@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreLoadMessages(chat: Chat)

    @Query("SELECT * FROM Chat")
    suspend fun getMessages():List<Chat>

}