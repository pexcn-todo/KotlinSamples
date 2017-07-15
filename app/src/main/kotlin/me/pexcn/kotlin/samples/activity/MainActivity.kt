package me.pexcn.kotlin.samples.activity

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import me.pexcn.kotlin.samples.R
import me.pexcn.kotlin.samples.base.BaseActivity

class MainActivity : BaseActivity(), AdapterView.OnItemClickListener {
    private lateinit var mListView: ListView
    private lateinit var mItems: Array<String>

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()

        mListView = findViewById(R.id.listview) as ListView
        mItems = resources.getStringArray(R.array.sub_activities)
        mListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mItems)
        mListView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
            0 -> setTargetActivity(position, ImageActivity::class.java)
        }
    }

    fun setTargetActivity(position: Int, clazz: Class<*>) {
        val intent = Intent()
        intent.setClass(this, clazz)
        intent.putExtra(BaseActivity.KEY_ACTIVITY_TITLE, mItems[position])
        startActivity(intent)
    }
}
