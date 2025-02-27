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
package com.app.test.bubble

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.widget.AppCompatImageView
import android.util.AttributeSet
import com.app.test.R

/**
 * @author lcx
 * Created at 2020.3.26
 * Describe:
 */
class BubbleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {
    private var mHeartResId = R.drawable.heart
    private var mHeartBorderResId = R.drawable.heart_border

    fun setColor(color: Int) {
        val heart = createHeart(color)
        setImageDrawable(BitmapDrawable(resources, heart))
    }

    fun setColorAndDrawables(color: Int, heartResId: Int, heartBorderResId: Int) {
        if (heartResId != mHeartResId) {
            sHeart = null
        }
        if (heartBorderResId != mHeartBorderResId) {
            sHeartBorder = null
        }
        mHeartResId = heartResId
        mHeartBorderResId = heartBorderResId
        setColor(color)
    }

    fun createHeart(color: Int): Bitmap? {
        if (sHeart == null) {
            sHeart = BitmapFactory.decodeResource(resources, mHeartResId)
        }
        if (sHeartBorder == null) {
            sHeartBorder = BitmapFactory.decodeResource(resources, mHeartBorderResId)
        }
        val heart = sHeart
        val heartBorder = sHeartBorder
        val bm = createBitmapSafely(heartBorder!!.width, heartBorder.height) ?: return null
        val canvas = sCanvas
        canvas.setBitmap(bm)
        val p = sPaint
        canvas.drawBitmap(heartBorder, 0f, 0f, p)
        p.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        val dx = (heartBorder.width - heart!!.width) / 2f
        val dy = (heartBorder.height - heart.height) / 2f
        canvas.drawBitmap(heart, dx, dy, p)
        p.colorFilter = null
        canvas.setBitmap(null)
        return bm
    }

    companion object {
        private val sPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        private var sHeart: Bitmap? = null
        private var sHeartBorder: Bitmap? = null
        private val sCanvas = Canvas()
        private fun createBitmapSafely(width: Int, height: Int): Bitmap? {
            try {
                return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            } catch (error: OutOfMemoryError) {
                error.printStackTrace()
            }
            return null
        }
    }
}