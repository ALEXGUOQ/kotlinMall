package com.gryphon.baselibrary.ext

import com.gryphon.baselibrary.rx.BaseSubscriber
import com.trello.rxlifecycle3.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Observable 扩展方法
 */
fun <T> Observable<T>.execute(subscriber:BaseSubscriber<T>,lifecycleProvider: LifecycleProvider<*>){
    this.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .compose(lifecycleProvider.bindToLifecycle())
        .subscribe(subscriber)
}