/*
 * Copyright (C) 2015 tyrantgit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.app.test.bubble;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;

import com.app.test.R;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lcx
 * Created at 2020.3.26
 * Describe:
 */

public abstract class AbstractPathAnimator {
    private final Random mRandom;
    protected final Config mConfig;


    public AbstractPathAnimator(Config config) {
        mConfig = config;
        mRandom = new Random();
    }

    public float randomRotation() {
        return mRandom.nextFloat() * 28.6F - 14.3F;
    }

    public Path createPath(AtomicInteger counter, View view, int factor) {
        Random r = mRandom;
        int x = r.nextInt(mConfig.xRand);
        int x2 = r.nextInt(mConfig.xRand);
        int y = view.getHeight() - mConfig.initY;
        int y2 = counter.intValue() * 15 + mConfig.animLength * factor + r.nextInt(mConfig.animLengthRand);
        factor = y2 / mConfig.bezierFactor;
        x = mConfig.xPointFactor + x;
        x2 = mConfig.xPointFactor + x2;
        int y3 = y - y2;
        y2 = y - y2 / 2;
        Path p = new Path();
        p.moveTo(mConfig.initX, y);
        p.cubicTo(mConfig.initX, y - factor, x, y2 + factor, x, y2);
        p.moveTo(x, y2);
        p.cubicTo(x, y2 - factor, x2, y3 + factor, x2, y3);
        return p;
    }

    public abstract void start(View child, ViewGroup parent);

    public static class Config {
        public int initX;
        public int initY;
        public int xRand;
        public int animLengthRand;
        public int bezierFactor;
        public int xPointFactor;
        public int animLength;
        public int heartWidth;
        public int heartHeight;
        public int animDuration;

        static Config fromTypeArray(TypedArray typedArray) {
            Config config = new Config();
            Resources res = typedArray.getResources();
            config.initX = (int) typedArray.getDimension(R.styleable.BubbleLayout_initX,
                    res.getDimensionPixelOffset(R.dimen.dp50));
            config.initY = (int) typedArray.getDimension(R.styleable.BubbleLayout_initY,
                    res.getDimensionPixelOffset(R.dimen.dp25));
            config.xRand = (int) typedArray.getDimension(R.styleable.BubbleLayout_xRand,
                    res.getDimensionPixelOffset(R.dimen.dp40));
            config.animLength = (int) typedArray.getDimension(R.styleable.BubbleLayout_animLength,
                    res.getDimensionPixelOffset(R.dimen.dp100));
            config.animLengthRand = (int) typedArray.getDimension(R.styleable.BubbleLayout_animLengthRand,
                    res.getDimensionPixelOffset(R.dimen.dp150));
            config.bezierFactor = typedArray.getInteger(R.styleable.BubbleLayout_bezierFactor, 6);
            config.xPointFactor = (int) typedArray.getDimension(R.styleable.BubbleLayout_xPointFactor,
                    res.getDimensionPixelOffset(R.dimen.dp30));
            config.heartWidth = (int) typedArray.getDimension(R.styleable.BubbleLayout_heart_width,
                    res.getDimensionPixelOffset(R.dimen.dp26));
            config.heartHeight = (int) typedArray.getDimension(R.styleable.BubbleLayout_heart_height,
                    res.getDimensionPixelOffset(R.dimen.dp30));
            config.animDuration = typedArray.getInteger(R.styleable.BubbleLayout_anim_duration, 3000);
            return config;
        }


    }


}

