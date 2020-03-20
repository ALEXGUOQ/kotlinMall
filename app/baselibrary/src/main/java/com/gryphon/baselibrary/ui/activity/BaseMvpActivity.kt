package com.gryphon.baselibrary.ui.activity

import com.gryphon.baselibrary.presenter.BasePresenter
import com.gryphon.baselibrary.presenter.view.BaseView
import javax.inject.Inject

open class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView {
    @Inject
    lateinit var mPresenter: T

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(msg: String) {
    }
}