package com.gryphon.baselibrary.injection.module

import android.content.Context
import com.gryphon.baselibrary.ui.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * @className: AppModule
 * @description:
 * @author: gqy
 * @date: 2020/3/21 11:39
 */
@Module
class AppModule (private val context: BaseApplication){
    @Provides
    @Singleton
    fun providersContext():Context{
        return context
    }
}