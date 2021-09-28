package com.francis.roommigration

import android.app.Application

class AppController : Application() {


    private var roomDb: RoomDatabaseEx? = null

    override fun onCreate() {
        super.onCreate()
        appController = this
        roomDb = RoomController(this).roomInstance()
    }


    companion object {
        private lateinit var appController: AppController
        fun getAppController() = appController
    }


    internal fun getDb() = roomDb
}