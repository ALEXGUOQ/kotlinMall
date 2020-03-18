package com.gryphon.usercenter.service

import rx.Observable

interface RegisterService {
    /**
     * 注册
     */
    fun register(phone:String,pwd:String,verifyCode:String): Observable<Boolean>
}