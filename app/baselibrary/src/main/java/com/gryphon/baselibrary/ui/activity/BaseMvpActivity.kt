package com.gryphon.baselibrary.ui.activity

import android.os.Bundle
import com.gryphon.baselibrary.injection.component.ActivityComponent
import com.gryphon.baselibrary.injection.component.DaggerActivityComponent
import com.gryphon.baselibrary.injection.module.ActivityModule
import com.gryphon.baselibrary.injection.module.LifeCycleProviderModule
import com.gryphon.baselibrary.presenter.BasePresenter
import com.gryphon.baselibrary.presenter.view.BaseView
import com.gryphon.baselibrary.ui.BaseApplication
import javax.inject.Inject

open class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView {
    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
    }

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((application as BaseApplication).appComponent)
            .activityModule(ActivityModule(this))
            .lifeCycleProviderModule(LifeCycleProviderModule(this))
            .build()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(msg: String) {
    }
}