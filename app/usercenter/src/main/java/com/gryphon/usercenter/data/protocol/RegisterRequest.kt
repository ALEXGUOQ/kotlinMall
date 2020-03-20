package com.gryphon.usercenter.data.protocol


/**
 * @className: RegisterRequest
 * @description:
 * @author: gqy
 * @date: 2020-03-20 10:35
 */
data class RegisterRequest(val phone: String, val pwd: String, val verifyCode: String)