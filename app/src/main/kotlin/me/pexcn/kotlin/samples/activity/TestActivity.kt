package me.pexcn.kotlin.samples.activity

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.pexcn.kotlin.samples.R
import me.pexcn.kotlin.samples.base.BaseActivity
import java.util.*

/**
 * Created by pexcn on 2017-05-03.
 */
class TestActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.test
    }

    override fun init() {
        super.init()

        var datas = SparseArray<Int>()

        var oldList = Arrays.asList(0, 1, 1, 0, 0, 0, 0, 1, 0, 1,0,0,0,0,0,0)

        var leftIndex = 0
        var rightIndex = 1
        for (i in oldList){
            if (i == 0) {
                datas.put(leftIndex,i)
                leftIndex+=2
            }else {
                datas.put(rightIndex,i)
                rightIndex +=2
            }
        }

        println(datas)

        var count = datas.size()

        for (i in 0..count){
            println("$i >> ${datas[i]}")
        }


        val viewPager: ViewPager = findViewById(R.id.view_pager) as ViewPager
        viewPager.adapter = pagerAdapter
    }

    override fun isSubActivity(): Boolean {
        return true
    }

    val pagerAdapter = object : PagerAdapter() {
        override fun isViewFromObject(view: View?, obj: Any?): Boolean {
            return view === obj
        }

        override fun getCount(): Int = 10

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
            val view = LayoutInflater.from(container?.context).inflate(R.layout.viewpager, container, false)
            container?.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup?, position: Int, obj: Any?) {
            if (obj is View) {
                container?.removeView(obj)
            }
        }
    }
}