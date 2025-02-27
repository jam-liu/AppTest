package com.app.test.mvvm.databinding

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.app.test.R
import com.app.test.mvvm.databinding.adapter.MyAdapter
import com.app.test.mvvm.databinding.bean.CommentBean
import com.app.test.mvvm.databinding.bean.DataBean
import com.app.test.mvvm.databinding.viewmodel.DataViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_data_binding.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by jam on 2019-09-29.
 *
 * @describe
 */
class DataBindingActivity : androidx.fragment.app.FragmentActivity() {

    private val urlData: ArrayList<String> = ArrayList()
    private val random = Random();

    val adapter: MyAdapter by lazy {
        MyAdapter(dataList).also {
            it.setOnLoadMoreListener({
                mDataViewModel.loadImage()
            }, data_recyclerView)
        }
    }
    internal var uuid: String? = null
    internal var courseId: String? = null

    private val dataList = ArrayList<CommentBean?>()

    val mDataViewModel: DataViewModel by lazy {
        ViewModelProviders.of(this).get(DataViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)
        initView()
        initDate()
        handleData()
        mDataViewModel.getSmsCode("d")
    }

    private fun handleData() {
//        mDataModel.commentData.observe(this, object : Observer<DataBean<CommentBean>> {
//            override fun onChanged(t: DataBean<CommentBean>?) {
//
//            }
//
//        })
        mDataViewModel.commentData.observe(this, Observer<DataBean<List<CommentBean?>?>> {
            it?.also {
                if (it.isSuccess) {
                    it.getmData()?.let { it1 -> dataList.addAll(it1) }
                    adapter.setNewData(dataList)
                    adapter.loadMoreComplete()
                } else {
                    Toast.makeText(this, it.getmInfo(), Toast.LENGTH_LONG).show()
                }
            }
        })
        mDataViewModel.smsCode.observe(this, Observer<String> {
            it?.also {
                tv_title.text = it;
            }
        })
    }

    private fun initView() {
        data_swipeRefreshLayout?.setOnRefreshListener {
            dataList.clear()
            initDate()
            data_swipeRefreshLayout?.isRefreshing = false
        }
        data_recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
        data_recyclerView.adapter = adapter
    }

    private fun initDate() {
        urlData.add("http://c.hiphotos.baidu.com/image/pic/item/9c16fdfaaf51f3de1e296fa390eef01f3b29795a.jpg")
        urlData.add("https://sport.mop.com/headimg/11fd78636f11f42f42e4c0c782fd44b30e55257c8bffebcd7377d19174a54e21.jpg")
        urlData.add("https://sport.mop.com/headimg/adb353a72f04c9ea6cb00e243cdff9a9512d21da50c3efe3a4b45830040740ad.jpg")
        urlData.add("https://sport.mop.com/headimg/b321d56c2626ea77575d0e869914c427e312193d956db251d4bf846e0491e4d9.jpg")
        urlData.add("https://sport.mop.com/headimg/8AFD548C3071D8D6B67283899A477F16.png")
        urlData.add("https://sport.mop.com/headimg/014d19868b864ff84c2f1a7de4a5088289e42bfba71e5ea4cc4feb546e3064cd.jpg")
        urlData.add("https://sport.mop.com/headimg/669ecd2e5284f64c58be7a1fde6bc2b702ca4403eeef1809a934772b72357343.jpg")
        urlData.add("https://sport.mop.com/headimg/28cad4e2a33ce0b52467e33552f36612765495966f16d372fab262b1878cef44.jpg")
        urlData.add("https://sport.mop.com/headimg/b4d6946f4b3f5cd0a678cada1c42fe72ace9e36f8614ebdbd0e8da8b54b65f45.jpg")
        urlData.add("https://sport.mop.com/headimg/b31312b3f06aeddfbfb0884376f3973ab5f274d70fe2584a08c65ee12f65b2f7.jpg")
        urlData.add("https://sport.mop.com/headimg/88185ee4d5ed6a122d337074b34bd6ce8193759b4fcdc0e00ac548dfb56983f8.jpg")
        urlData.add("https://sport.mop.com/headimg/526D319DEE1DC7AFF72A4A58EFECF7D7.png")
        urlData.add("https://sport.mop.com/headimg/5c0fc81cb803de57a1ec030a9fbc53135978621d4371516ca28ac1b50bb9ee76.jpg")
        urlData.add("https://sport.mop.com/headimg/86DAA1338E58AC16C6F3141D6E6375DC.png")
        urlData.add("https://sport.mop.com/headimg/c287b0bc0c59515a25bc4998a655524dbab97ff6f00591b0e374c7d82a47a24d.jpg")
        uuid = ""
        courseId = ""
        for (i in 0..19) {
            val dto = CommentBean()
            dto.content = i.toString() + "内容"
            dto.commentId = i.toString() + ""
            dto.userName = i.toString() + "name"
            dto.userAvatar = urlData[random.nextInt(urlData.size - 1)]
            if (i % 2 == 0) {
                dto.isShowAll = true
            }
            dataList.add(dto)
        }
        dataList.forEach {
            Log.e("jam", it?.userAvatar)
        }

        dataList.forEachIndexed { index, commentBean ->
            Log.e("jam", index.toString())
        }
        dataList.take(3).forEach {

        }
        adapter.setNewData(dataList)
        Observable.fromIterable(dataList).subscribe(object : io.reactivex.Observer<CommentBean?> {
            override fun onComplete() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(d: Disposable?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(value: CommentBean?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(e: Throwable?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }

    private fun loadMore() {

        adapter.setNewData(dataList)
    }
}
