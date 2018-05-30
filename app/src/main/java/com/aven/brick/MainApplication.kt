package com.aven.brick

import android.app.Application
import android.content.Context
import com.aven.brick.core.Brick

/**
 *
 * @author : Aven
 * @date :  [2018-05-30]
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Brick.attachBaseContext(base)
    }
}