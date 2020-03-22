package com.gryphon.usercenter.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.gryphon.baselibrary.ui.activity.BaseMvpActivity
import com.gryphon.usercenter.R
import com.gryphon.usercenter.injection.component.DaggerUserComponent
import com.gryphon.usercenter.injection.module.UserModule
import com.gryphon.usercenter.presenter.RegisterPresenter
import com.gryphon.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

/**
 * 注册页面
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initInjection()

        registerBtn.setOnClickListener {
            mPresenter.register("11", "11", "11")
        }
    }

    private fun initInjection() {
        DaggerUserComponent.builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build()
            .inject(this)

        mPresenter.mView = this
    }

    override fun onRegisterResult(result: Boolean) {
        if (result) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onError(msg: String) {

    }
}
