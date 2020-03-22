package com.gryphon.baselibrary.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/**
 * @className: PerComponentScope
 * @description:
 * @author: gqy
 * @date: 2020/3/21 11:47
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PerComponentScope