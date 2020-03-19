package com.gryphon.baselibrary.ext

import com.gryphon.baselibrary.rx.BaseSubscriber
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Observable 扩展方法
 */
fun <T> Observable<T>.execute(subscriber:BaseSubscriber<T>){
    this.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(subscriber)
}