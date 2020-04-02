package com.app.test.flexbox

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.app.test.R
import com.google.android.flexbox.*
import java.util.*

/**
 * @author lcx
 * Created at 2020.4.1
 * Describe:
 */
class FlexboxManagerView : FrameLayout {
    private var recyclerView: RecyclerView? = null
    private var tvPosition: TextView? = null
    private var adapter: ViewAdapter? = null

    private val imgs = arrayOf(
            R.drawable.share_fb,
            R.drawable.ic_game_gold_10,
            R.drawable.icon_pic3,
            R.drawable.share_weibo,
            R.drawable.ic_number_six,
            R.drawable.ic_game_gold_10,
            R.drawable.ic_share_bonus_cat_my_bonus,
            R.drawable.ic_game_gold_10,
            R.drawable.ic_game_gold_10,
            R.drawable.icon_pic3,
            R.drawable.share_kongjian,
            R.drawable.ic_game_gold_10,
            R.drawable.icon_pic3,
            R.drawable.share_pyq,
            R.drawable.share_tw,
            R.drawable.main_tab_home_selected,
            R.drawable.icon_pic7,
            R.drawable.ic_game_gold_02,
            R.drawable.icon_pic3,
            R.drawable.ic_game_gold_04,
            R.drawable.icon_pic5,
            R.drawable.main_tab_home_selected,
            R.drawable.ic_game_gold_07,
            R.drawable.icon_pic8,
            R.drawable.ic_game_gold_09,
            R.drawable.share_wechat,
            R.drawable.share_weibo,
            R.drawable.icon_pic5,
            R.drawable.ic_number_six,
            R.drawable.ic_game_gold_10,
            R.drawable.ic_share_bonus_cat_my_bonus,
            R.drawable.ic_game_gold_10,
            R.drawable.lib_picture_icon,
            R.drawable.rating_star_light,
            R.drawable.icon_pic5,
            R.drawable.idr,
            R.drawable.ic_game_gold_10,
            R.drawable.ic_game_progress_box_arrived,
            R.drawable.lib_video_icon,
            R.drawable.ic_game_gold_10,
            R.drawable.ic_body_bmi,
            R.drawable.icon_pic5,
            R.drawable.ilovecheese_heart,
            R.drawable.ic_game_fortune_bag,
            R.drawable.ic_number_four,
            R.drawable.ic_game_gold_10,
            R.drawable.lib_pdf_icon,
            R.drawable.share_weibo,
            R.drawable.icon_pic5,
            R.drawable.ic_mine_back,
            R.drawable.ic_game_header_coin,
            R.drawable.lib_excel_icon,
            R.drawable.ic_game_gold_10,
            R.drawable.ilovecheese_i,
            R.drawable.icon_pic5,
            R.drawable.ic_body_age,
            R.drawable.ic_game_gold_10,
            R.drawable.video_firstplay,
            R.drawable.ic_game_hand,
            R.drawable.ic_game_gold_11,
            R.drawable.share_qq,
            R.drawable.ic_game_gold_12,
            R.drawable.ic_number_eight,
            R.drawable.share_qq,
            R.drawable.ic_game_gold_14,
            R.drawable.ic_body_weight, R.drawable.icon_pic5,
            R.drawable.ic_body_age,
            R.drawable.ic_game_gold_10,
            R.drawable.ic_home_calories,
            R.drawable.ic_game_hand,
            R.drawable.ic_game_gold_11,
            R.drawable.share_qq,
            R.drawable.ic_game_gold_12,
            R.drawable.ic_number_eight,
            R.drawable.ic_walk_hot_activity,
            R.drawable.ic_walk_data,
            R.drawable.ic_body_weight, R.drawable.icon_pic5,
            R.drawable.ic_body_age,
            R.drawable.ic_game_gold_10,
            R.drawable.ic_home_distance,
            R.drawable.ic_game_hand,
            R.drawable.ic_game_gold_11,
            R.drawable.ic_home_time,
            R.drawable.ic_game_gold_12,
            R.drawable.ic_number_eight,
            R.drawable.share_qq,
            R.drawable.ic_game_gold_14,
            R.drawable.ic_body_weight, R.drawable.icon_pic5,
            R.drawable.ic_body_age,
            R.drawable.ic_game_gold_10,
            R.drawable.video_firstplay,
            R.drawable.ic_game_hand,
            R.drawable.ic_game_gold_11,
            R.drawable.ic_step_calories,
            R.drawable.ic_game_gold_12,
            R.drawable.ic_number_eight,
            R.drawable.share_qq,
            R.drawable.ic_game_gold_14,
            R.drawable.ic_body_weight,
            R.drawable.main_tab_mine_selected,
            R.drawable.icon_pic5,
            R.drawable.ic_game_gold_17,
            R.drawable.share_weibo
    )

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    fun initView() {
        View.inflate(context, R.layout.view_flexbox_manager, this)
        recyclerView = findViewById(R.id.rv_list)
        tvPosition = findViewById(R.id.tv_position)
        tvPosition?.setOnClickListener(OnClickListener {
            recyclerView?.also {
                val index = Random().nextInt(imgs.size)
                Toast.makeText(context, index.toString(), Toast.LENGTH_SHORT).show()
                it.scrollToPosition(index)
            }
        })
        val layoutManager = FlexboxLayoutManager()
        layoutManager.flexWrap = FlexWrap.WRAP //设置是否换行
        layoutManager.flexDirection = FlexDirection.ROW // 设置主轴排列方式
        layoutManager.alignItems = AlignItems.CENTER
        layoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
        recyclerView?.layoutManager = layoutManager
        adapter = ViewAdapter(listOf(*imgs))
        recyclerView?.adapter = adapter
        adapter?.setOnItemClickListener { adapter, view, position ->
            Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}