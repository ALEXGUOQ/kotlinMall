package com.gryphon.usercenter.presenter

import com.gryphon.baselibrary.ext.execute
import com.gryphon.baselibrary.presenter.BasePresenter
import com.gryphon.baselibrary.rx.BaseSubscriber
import com.gryphon.usercenter.presenter.view.RegisterView
import com.gryphon.usercenter.service.RegisterService
import com.gryphon.usercenter.service.impl.RegisterServiceImpl

class RegisterPresenter : BasePresenter<RegisterView>() {
    /**
     * 注册
     */
    fun register(phone: String, pwd: String, verifyCode: String) {

        val userService: RegisterService = RegisterServiceImpl()
        userService.register(phone, pwd, verifyCode)
            .execute(object : BaseSubscriber<Boolean>(){
                override fun onNext(t: Boolean) {
                    with(mView) { onRegisterResult(t) }
                }

                override fun onError(e: Throwable?) {
                    with(mView) { onError("注册失败!") }
                }
            })
    }
}