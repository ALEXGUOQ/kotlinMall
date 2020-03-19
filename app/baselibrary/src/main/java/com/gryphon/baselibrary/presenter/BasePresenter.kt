package com.gryphon.baselibrary.presenter

import com.gryphon.baselibrary.presenter.view.BaseView

open class BasePresenter<T : BaseView> {
    lateinit var mView: T
}