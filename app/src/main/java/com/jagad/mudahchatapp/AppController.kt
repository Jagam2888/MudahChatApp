package com.jagad.mudahchatapp

import android.app.Application
import com.jagad.mudahchatapp.Data.local.ChatDatabase
import com.jagad.mudahchatapp.Data.remote.ChatPostService
import com.jagad.mudahchatapp.repositary.ChatRepositary
import com.jagad.mudahchatapp.repositary.MessageRepositary
import com.jagad.mudahchatapp.ui.viewmodel.viewmodelfactory.ChatViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by jagad on 8/10/2021
 */
class AppController:Application(),KodeinAware {

    override val kodein = Kodein.lazy {

        import(androidXModule(this@AppController))

        bind() from singleton { ChatPostService(instance()) }
        bind() from singleton { ChatDatabase(instance()) }

        bind() from singleton { MessageRepositary(instance()) }
        bind() from singleton { ChatRepositary(instance(),instance()) }
        bind() from provider { ChatViewModelFactory(instance(),instance()) }
    }
}