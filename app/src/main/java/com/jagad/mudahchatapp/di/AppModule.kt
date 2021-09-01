package com.jagad.mudahchatapp.di

import android.content.Context
import com.jagad.mudahchatapp.Data.local.ChatDao
import com.jagad.mudahchatapp.Data.local.ChatDatabase
import com.jagad.mudahchatapp.Data.remote.ChatPostService
import com.jagad.mudahchatapp.network.RetrofitClientInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by jagad on 8/11/2021
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun providePostMsgApi(
        @ApplicationContext context: Context,
        retrofitClientInstance: RetrofitClientInstance
    ):ChatPostService{
        return retrofitClientInstance.buildApi(ChatPostService::class.java,context)
    }

    @Provides
    fun getChatDatabase(@ApplicationContext context: Context):ChatDatabase{
        return ChatDatabase.invoke(context)
    }

    @Provides
    fun getChatDao(chatDatabase: ChatDatabase):ChatDao{
        return chatDatabase.getDao()
    }
}