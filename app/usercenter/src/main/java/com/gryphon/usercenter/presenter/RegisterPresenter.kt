package com.gryphon.usercenter.presenter

import com.gryphon.baselibrary.presenter.BasePresenter
import com.gryphon.usercenter.presenter.view.RegisterView
import com.gryphon.usercenter.service.RegisterService
import com.gryphon.usercenter.service.impl.RegisterServiceImpl
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RegisterPresenter : BasePresenter<RegisterView>() {
    /**
     * 注册
     */
    fun register(phone: String, pwd: String, verifyCode: String) {

        val userService: RegisterService = RegisterServiceImpl()
        userService.register(phone, pwd, verifyCode)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Subscriber<Boolean>() {
                override fun onNext(t: Boolean?) {
                    t?.let { mView.onRegisterResult(it) }
                }

                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    mView.onError("注册失败!")
                }

            })
    }
}