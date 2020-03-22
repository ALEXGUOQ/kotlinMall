package com.gryphon.baselibrary.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides


/**
 * @className: ActivityModule
 * @description:
 * @author: gqy
 * @date: 2020/3/21 11:39
 */
@Module
class ActivityModule (private val activity: Activity){
    @Provides
    fun providersActivity():Activity{
        return activity
    }
}