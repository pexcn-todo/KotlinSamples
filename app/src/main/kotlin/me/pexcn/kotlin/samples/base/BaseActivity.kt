package me.pexcn.kotlin.samples.base

import android.support.annotation.CallSuper
import me.pexcn.android.base.ui.BaseActivity

/**
 * Created by pexcn on 2017-05-03.
 */
abstract class BaseActivity : BaseActivity() {
    companion object {
        val KEY_ACTIVITY_TITLE = "activity_title"
    }

    @CallSuper
    override fun init() {
        val actionBar = supportActionBar
        val title = intent.getStringExtra(KEY_ACTIVITY_TITLE)
        if (actionBar != null && title != null && isSubActivity) {
            actionBar.title = title
        }
    }
}
