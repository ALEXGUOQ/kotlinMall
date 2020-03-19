package com.gryphon.usercenter.service.impl

import com.gryphon.usercenter.service.RegisterService
import rx.Observable

class RegisterServiceImpl : RegisterService {
    override fun register(phone: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return Observable.just(false)
    }
}