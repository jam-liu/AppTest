package com.app.test.flexbox;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.app.test.R;
import com.app.test.path.ViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author lcx
 * Created at 2020.4.1
 * Describe:
 */
public class FlexboxActivity extends Activity {
    private ViewPager vpView;
    private ViewAdapter adapter;
    private ArrayList<View> dataList = new ArrayList<>();
    private FlexboxLayoutView flexboxLayoutView;
    private FlexboxManagerView flexboxManagerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        initView();
    }

    private void initView() {
        vpView = (ViewPager) findViewById(R.id.vp_view);
        flexboxLayoutView = new FlexboxLayoutView(this);
        flexboxManagerView = new FlexboxManagerView(this);
        dataList.add(flexboxLayoutView);
        dataList.add(flexboxManagerView);
        adapter = new ViewAdapter(dataList);
        vpView.setAdapter(adapter);
    }

    private int[] width = {10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80,};
    private int[] image = {
            R.drawable.share_fb,
            R.drawable.share_kongjian,
            R.drawable.share_pyq,
            R.drawable.share_qq,
            R.drawable.share_tw,
            R.drawable.share_wechat,
            R.drawable.a1,
            R.drawable.a2,
            R.drawable.a3,
            R.drawable.a4,
            R.drawable.a5,
            R.drawable.a6,
            R.drawable.a7,
            R.drawable.a8
    };

    private List<BoxBean> getList() {
        Random random = new Random();
        List<BoxBean> list = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            BoxBean boxBean = new BoxBean();
            int index = random.nextInt(width.length);
            int imageIndex = random.nextInt(image.length);
            boxBean.id = image[imageIndex];
            boxBean.with = width[index];
            list.add(boxBean);
        }
        return list;
    }
}
