package com.aolig.miniprogram.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.aolig.miniprogram.R
import com.aolig.miniprogram.providers.StatusBar
import kotlinx.android.synthetic.main.custom_nav_bar.view.*


class CustomNavBar(private val ctx: Context, attrs: AttributeSet) : LinearLayout(ctx, attrs) {
    val statusBarHeight = StatusBar.getStatusBarHeight(ctx as Activity)
    var navHeight = 0
    var navColorMode = ThemeColorMode.DARK
    private var navIcon = NavButtonType.NONE
    private var title = "Aolig"
    private var background = R.color.main

    init {
        LayoutInflater.from(ctx).inflate(R.layout.custom_nav_bar, this)
        val linearParams = custom_nav_status_bar.layoutParams as LinearLayout.LayoutParams
        linearParams.height = statusBarHeight
        custom_nav_status_bar.layoutParams = linearParams

        navHeight = resources.getDimensionPixelSize(R.dimen.nav_bar_height)

        update()
    }

    fun setNavButton(type: NavButtonType) {
        navIcon = type
        update()
    }

    fun setTitle(title: String) {
        this.title = title
        update()
    }

    fun setStyle(mode: ThemeColorMode) {
        navColorMode = mode
        update()
    }

    fun setBackground(bg: Int) {
        background = bg
        update()
    }

    fun update() {
        var backIcon = R.drawable.back
        var homeIcon = R.drawable.home
        var homeBg = R.drawable.home_btn_bg
        var color = R.color.black
        if (navColorMode == ThemeColorMode.LIGHT) {
            backIcon = R.drawable.back_light
            homeIcon = R.drawable.home_light
            homeBg = R.drawable.home_btn_bg_light
            color = R.color.white
        }
        when (navIcon) {
            NavButtonType.NONE -> {
                navbar_icon.visibility = View.GONE
                custom_nav_left.visibility = View.INVISIBLE
            }

            NavButtonType.BACK -> {
                navbar_icon.visibility = View.VISIBLE
                custom_nav_left.visibility = View.GONE
                navbar_icon.setImageResource(backIcon)
            }

            NavButtonType.HOME -> {
                navbar_icon.visibility = View.VISIBLE
                custom_nav_left.visibility = View.GONE
                navbar_icon.setImageResource(homeIcon)
                navbar_icon.setBackgroundResource(homeBg)

            }
        }
        navbar_title.text = title
        navbar_title.setTextColor(ctx.getColor(color))
        custom_nav_bar.setBackgroundResource(background)
        custom_nav_status_bar.setBackgroundResource(background)
    }
}

enum class NavButtonType {
    BACK, HOME, NONE
}

enum class ThemeColorMode {
    LIGHT, DARK
}