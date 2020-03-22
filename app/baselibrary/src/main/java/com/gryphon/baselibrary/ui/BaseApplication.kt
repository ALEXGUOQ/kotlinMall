package com.gryphon.baselibrary.ui

import android.app.Application
import com.gryphon.baselibrary.injection.component.AppComponent
import com.gryphon.baselibrary.injection.component.DaggerAppComponent
import com.gryphon.baselibrary.injection.module.AppModule


/**
 * @className: BaseApplication
 * @description:
 * @author: gqy
 * @date: 2020/3/21 11:41
 */
class BaseApplication:Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection()
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}