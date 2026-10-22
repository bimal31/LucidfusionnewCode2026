package com.org.lucidfusion

import android.app.Application
import com.org.lucidfusion.dbhelper.DBAdapter
import com.org.lucidfusion.dbhelper.DBQueries
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        dbAdapter = DBAdapter.getInstance(this)
        dbQueries = DBQueries(this)
    }

    companion object {
        @JvmStatic lateinit var instance: ApplicationClass
            private set

        @JvmStatic var dbAdapter: DBAdapter? = null
        @JvmStatic var dbQueries: DBQueries? = null
        @JvmStatic var boundNetwork: android.net.Network? = null
    }
}
