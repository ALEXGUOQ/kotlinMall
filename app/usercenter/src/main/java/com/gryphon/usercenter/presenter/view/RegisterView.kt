package com.gryphon.usercenter.presenter.view

import com.gryphon.baselibrary.presenter.view.BaseView

interface RegisterView : BaseView {
    /**
     * 注册回调
     */
    fun onRegisterResult(result: Boolean)
}