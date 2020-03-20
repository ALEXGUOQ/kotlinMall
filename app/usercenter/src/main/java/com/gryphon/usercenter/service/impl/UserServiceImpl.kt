package com.gryphon.usercenter.service.impl

import android.util.Log
import com.gryphon.baselibrary.data.protocol.BaseResponse
import com.gryphon.baselibrary.rx.BaseException
import com.gryphon.usercenter.presenter.repository.UserRepository
import com.gryphon.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

class UserServiceImpl @Inject constructor() : UserService {
    @Inject
    lateinit var repository: UserRepository

    override fun register(phone: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(phone, pwd, verifyCode)
            .flatMap(object : Func1<BaseResponse<String>, Observable<Boolean>> {
                override fun call(t: BaseResponse<String>): Observable<Boolean> {
                    Log.i("s20", t.message)
                    if (t.status != 0) {
                        return Observable.error(BaseException(t.status, t.message))
                    }

                    return Observable.just(true)
                }
            })
    }
}