package me.pexcn.kotlin.samples.activity

import me.pexcn.kotlin.samples.R
import me.pexcn.kotlin.samples.base.BaseActivity

/**
 * Created by pexcn on 2017-05-03.
 */
class Test2Activity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()

    }

    override fun isSubActivity(): Boolean {
        return true
    }
}