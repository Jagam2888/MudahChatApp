package com.jagad.mudahchatapp.Data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by jagad on 8/9/2021
 */
@Database(
    entities = [Chat::class],
    version = 1,
    exportSchema = false
)
abstract class ChatDatabase: RoomDatabase() {

    abstract fun getDao() : ChatDao

    companion object{
        @Volatile
        private var instance: ChatDatabase?=null
        private val LOCK = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ChatDatabase::class.java,
                "mudah_chat"
            ).allowMainThreadQueries().build()
    }
}