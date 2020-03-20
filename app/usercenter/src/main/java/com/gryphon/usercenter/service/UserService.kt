package com.gryphon.usercenter.service

import rx.Observable

interface UserService {
    /**
     * 注册
     */
    fun register(phone: String, pwd: String, verifyCode: String): Observable<Boolean>
}