package com.app.test.flexbox;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.app.test.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;
import java.util.Random;

/**
 * @author lcx
 * Created at 2020.4.2
 * Describe:
 */
public class ViewAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    private int[] type = {AlignSelf.AUTO, AlignSelf.FLEX_START, AlignSelf.FLEX_END, AlignSelf.CENTER, AlignSelf.BASELINE, AlignSelf.BASELINE};
    private Random random = new Random();

    public ViewAdapter(@Nullable List<Integer> data) {
        super(R.layout.item_flexbox_manager, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Integer item) {
        helper.setImageResource(R.id.iv_pic, item);
        if (helper.itemView.getLayoutParams() instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams lp = (FlexboxLayoutManager.LayoutParams) helper.itemView.getLayoutParams();
            lp.setFlexGrow(10.0f);
            lp.setAlignSelf(type[random.nextInt(type.length)]);
        }
    }
}
