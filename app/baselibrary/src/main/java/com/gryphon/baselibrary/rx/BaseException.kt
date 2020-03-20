package com.gryphon.baselibrary.rx


/**
 * @className: BaseException
 * @description:
 * @author: gqy
 * @date: 2020-03-20 14:10
 */
class BaseException(val status: Int, val msg: String) : Throwable() {
}