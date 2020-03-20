package com.gryphon.usercenter.presenter.repository

import com.gryphon.baselibrary.data.net.RetrofitFactory
import com.gryphon.baselibrary.data.protocol.BaseResponse
import com.gryphon.usercenter.data.api.UserApi
import com.gryphon.usercenter.data.protocol.LoginRequest
import com.gryphon.usercenter.data.protocol.RegisterRequest
import rx.Observable
import javax.inject.Inject


/**
 * @className: UserRepository
 * @description:
 * @author: gqy
 * @date: 2020-03-20 13:55
 */
class UserRepository @Inject constructor() {

    /**
     * 注册
     */
    fun register(phone: String, pwd: String, verifyCode: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
            .register(RegisterRequest(phone, pwd, verifyCode))
    }

    /**
     * 登录
     */
    fun login(phone: String, pwd: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).login(LoginRequest(phone, pwd))
    }
}