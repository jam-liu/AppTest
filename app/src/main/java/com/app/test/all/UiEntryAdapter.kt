package com.app.test.all

import android.content.Intent
import com.app.test.R
import com.app.ui.PathAnimActivity
import com.app.ui.material.SysUiActivity
import com.app.ui.video.VideoActivity
import com.app.ui.zhibo.LiveMainActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class UiEntryAdapter : BaseQuickAdapter<Int, BaseViewHolder>(R.layout.view_main_item) {
    private val texts: MutableList<Int> = ArrayList()
    private val clzs: ArrayList<Class<*>> = ArrayList()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder?.also {
            it.setText(R.id.textView, it.itemView?.context?.resources?.getString(texts[position]))
            it.itemView?.setOnClickListener {
                holder.itemView?.context?.startActivity(
                        Intent(holder.itemView?.context, clzs[position]))
            }
        }
    }

    override fun convert(helper: BaseViewHolder, item: Int?) {

    }

    private fun setData() {
        texts.add(R.string.PathAnimActivity)
        texts.add(R.string.LiveMainActivity)
        texts.add(R.string.VideoActivity)
        texts.add(R.string.SysUiActivity)

        clzs.add(PathAnimActivity::class.java) //
        clzs.add(LiveMainActivity::class.java) //
        clzs.add(VideoActivity::class.java) //
        clzs.add(SysUiActivity::class.java) //
    }

    init {
        setData()
        setNewData(texts)
    }


}