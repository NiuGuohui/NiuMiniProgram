package com.aolig.miniprogram.providers

import android.app.Activity

object StatusBar {

    /**
     * get status bar height
     */
    fun getStatusBarHeight(activity: Activity): Int {
        val resourceId =
            activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) activity.resources.getDimensionPixelSize(resourceId) else 60
    }
}