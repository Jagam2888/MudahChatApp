package com.jagad.mudahchatapp.ui.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.jagad.mudahchatapp.Data.local.Chat
import com.jagad.mudahchatapp.Data.local.ChatDatabase
import com.jagad.mudahchatapp.Data.remote.ChatPostService
import com.jagad.mudahchatapp.network.RetrofitClientInstance
import com.jagad.mudahchatapp.network.createOkHttp
import com.jagad.mudahchatapp.repositary.ChatRepositary
import com.jagad.mudahchatapp.repositary.MessageRepositary
import com.jagad.mudahchatapp.utils.currentDateTime
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jagad on 8/12/2021
 */
@RunWith(AndroidJUnit4::class)
class ChatViewModelTest : TestCase(){

    private lateinit var viewModel: ChatViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()

        val context = ApplicationProvider.getApplicationContext<Context>()

        val api = ChatPostService(context)

        val db = Room.inMemoryDatabaseBuilder(context,ChatDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        val repositary = ChatRepositary(api,db)
        val msgRepositary = MessageRepositary(context)
        viewModel = ChatViewModel(repositary,msgRepositary)
    }

    @Test
    fun testChatViewModel(){
        viewModel.insertNewChat(Chat("OUTGOING","Hi", currentDateTime()))
        viewModel.loadMessageList()
        val result = viewModel.messageList.getOrAwaitValue().find {
            it.direction == "OUTGOING" && it.message == "Hi"
        }


        assertThat(result != null).isTrue()
    }
}