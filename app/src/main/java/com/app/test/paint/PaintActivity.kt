package com.app.test.paint

import android.app.Activity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import android.view.View
import com.app.test.R
import com.app.test.path.ViewAdapter

/**
 *
 * @author lcx
 * Created at 2020.3.31
 * Describe:
 */
class PaintActivity : Activity() {
    var adapter: ViewAdapter? = null
    var viewPager: androidx.viewpager.widget.ViewPager? = null
    val dataList: ArrayList<View> by lazy { ArrayList<View>() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_path)
        initDate()
    }

    private fun initDate() {
        dataList.clear()
        dataList.add(PaintView(this))
        dataList.add(XfermodeView(this))
        adapter = ViewAdapter(dataList);

        viewPager = findViewById(R.id.vp_view)
        viewPager?.adapter = adapter;
    }
}