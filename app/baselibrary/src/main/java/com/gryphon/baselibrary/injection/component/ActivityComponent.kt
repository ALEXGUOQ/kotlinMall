package com.gryphon.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.gryphon.baselibrary.injection.ActivityScope
import com.gryphon.baselibrary.injection.module.ActivityModule
import com.gryphon.baselibrary.injection.module.LifeCycleProviderModule
import com.trello.rxlifecycle3.LifecycleProvider
import dagger.Component

/**
 * @className: ActivityComponent
 * @description:
 * @author: gqy
 * @date: 2020/3/21 11:38
 */
@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class,LifeCycleProviderModule::class ])
interface ActivityComponent {
    fun activity():Activity
    fun context():Context
    fun lifeCycleProvider():LifecycleProvider<*>
}