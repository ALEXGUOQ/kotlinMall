package com.gryphon.usercenter.injection.component

import com.gryphon.usercenter.injection.module.UserModule
import com.gryphon.usercenter.ui.activity.RegisterActivity
import dagger.Component

/**
 * @className: UserComponent
 * @description:
 * @author: gqy
 * @date: 2020-03-20 16:24
 */
@Component(modules = [UserModule::class])
interface UserComponent {
    fun inject(activity: RegisterActivity)
}