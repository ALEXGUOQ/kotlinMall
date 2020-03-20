package com.gryphon.baselibrary.data.protocol


/**
 * @className: BaseResponse
 * @description:
 * @author: gqy
 * @date: 2020-03-20 10:32
 */
class BaseResponse<out T>(val status: Int, val message: String, val data: T)