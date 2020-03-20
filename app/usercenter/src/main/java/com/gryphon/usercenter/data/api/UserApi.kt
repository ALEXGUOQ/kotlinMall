package com.gryphon.usercenter.data.api

import com.gryphon.baselibrary.data.protocol.BaseResponse
import com.gryphon.usercenter.data.protocol.LoginRequest
import com.gryphon.usercenter.data.protocol.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * @className: UserApi
 * @description:
 * @author: gqy
 * @date: 2020-03-20 10:33
 */
interface UserApi {
    @POST("/userCenter/register")
    fun register(@Body req: RegisterRequest): Observable<BaseResponse<String>>

    @POST("/userCenter/login")
    fun login(@Body req: LoginRequest): Observable<BaseResponse<String>>
}