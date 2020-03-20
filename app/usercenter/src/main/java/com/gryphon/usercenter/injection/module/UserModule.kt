package com.gryphon.usercenter.injection.module

import com.gryphon.usercenter.service.UserService
import com.gryphon.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides


/**
 * @className: UserModule
 * @description:
 * @author: gqy
 * @date: 2020-03-20 16:20
 */
@Module
class UserModule {

    @Provides
    fun providersUserService(service: UserServiceImpl): UserService {
        return service
    }
}