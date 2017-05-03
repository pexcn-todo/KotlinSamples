package me.pexcn.kotlin.samples.app

import android.app.Application
import me.pexcn.android.utils.Utils

/**
 * Created by pexcn on 2017-05-03.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}
