package com.gryphon.baselibrary.presenter

import com.gryphon.baselibrary.presenter.view.BaseView
import com.trello.rxlifecycle3.LifecycleProvider
import javax.inject.Inject

open class BasePresenter<T : BaseView> {
    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
}