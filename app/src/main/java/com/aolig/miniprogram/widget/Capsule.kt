package com.aolig.miniprogram.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.aolig.miniprogram.R
import kotlinx.android.synthetic.main.capsule.view.*

class Capsule(private val ctx: Context, attrs: AttributeSet) :
    LinearLayout(ctx, attrs) {
    private var menuListener: (() -> Unit)? = null
    private var closeListener: (() -> Unit)? = null

    init {
        LayoutInflater.from(ctx).inflate(R.layout.capsule, this)

        capsule_menu.setOnClickListener { menuListener?.let { it() } }

        capsule_close.setOnClickListener { closeListener?.let { it() } }
    }

    fun setThemeMode(mode: ThemeColorMode) {
        if (mode == ThemeColorMode.LIGHT) {
            capsule_menu.setImageResource(R.mipmap.menu_light)
            capsule_close.setImageResource(R.mipmap.close_light)
            capsule_split_line.setBackgroundColor(ContextCompat.getColor(ctx, R.color.white))
        } else if (mode == ThemeColorMode.DARK) {
            capsule_menu.setImageResource(R.mipmap.menu_dark)
            capsule_close.setImageResource(R.mipmap.close_dark)
            capsule_split_line.setBackgroundColor(ContextCompat.getColor(ctx, R.color.black))
        }
    }

    fun setOnMenuClickListener(cb: () -> Unit) {
        menuListener = cb
    }

    fun setOnCloseClickListener(cb: () -> Unit) {
        closeListener = cb
    }

}
