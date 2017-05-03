package me.pexcn.kotlin.samples

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import me.pexcn.kotlin.samples.activity.Test2Activity
import me.pexcn.kotlin.samples.activity.TestActivity
import me.pexcn.kotlin.samples.base.BaseActivity

class MainActivity : BaseActivity(), AdapterView.OnItemClickListener {
    private var mItems: Array<String>? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()

        val listView: ListView = findViewById(R.id.list_view) as ListView
        mItems = resources.getStringArray(R.array.sub_activities)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mItems)
        listView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
            0 ->
                setTargetActivity(position, TestActivity::class.java)
            1 ->
                setTargetActivity(position, Test2Activity::class.java)
        }
    }

    fun setTargetActivity(position: Int, clazz: Class<*>) {
        val intent = Intent()
        intent.setClass(this, clazz)
        intent.putExtra(BaseActivity.KEY_ACTIVITY_TITLE, mItems?.get(position))
        startActivity(intent)
    }
}
