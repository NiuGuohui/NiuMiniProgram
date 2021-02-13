package com.aolig.popup.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView


class AutoMaxHeightScrollView(private val ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    RecyclerView(ctx, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null) {}

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        try {
            heightMeasureSpec =
                MeasureSpec.makeMeasureSpec(dp2px(300.toFloat()), MeasureSpec.AT_MOST)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        // 重新计算控件的宽高
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun dp2px(dp: Float): Int {
        val scale = ctx.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}