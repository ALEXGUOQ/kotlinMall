package com.gryphon.baselibrary.injection.component

import android.content.Context
import com.gryphon.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * @className: AppComponent
 * @description:
 * @author: gqy
 * @date: 2020/3/21 11:38
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun context():Context
}