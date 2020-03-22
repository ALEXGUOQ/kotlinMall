package com.gryphon.baselibrary.injection.module

import com.trello.rxlifecycle3.LifecycleProvider
import dagger.Module
import dagger.Provides


/**
 * @className: LifeCycleProviderModule
 * @description:
 * @author: gqy
 * @date: 2020/3/23 07:14
 */
@Module
class LifeCycleProviderModule (private val lifecycleProvider: LifecycleProvider<*> ){
    @Provides
    fun providersLifecycleProvider():LifecycleProvider<*>{
        return lifecycleProvider
    }
}