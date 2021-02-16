package com.aolig.miniprogram.widget

import android.content.pm.ApplicationInfo
import android.view.LayoutInflater
import android.webkit.WebView
import androidx.constraintlayout.widget.ConstraintLayout
import com.aolig.miniprogram.MiniProgramActivity
import com.aolig.miniprogram.R
import com.aolig.miniprogram.bridge.UI2Native
import com.aolig.miniprogram.helper.AoligLog
import kotlinx.android.synthetic.main.mini_program_page.view.*



class MiniProgramPage(
    val index: Int,
    private var activity: MiniProgramActivity,
    private var url: String,
    private var capsule: Capsule
) : ConstraintLayout(activity) {
    var pageWebView: WebView
    var navBar: CustomNavBar
    val webviewId = "__webview__" + Integer.toHexString(System.currentTimeMillis().toInt())

    init {
        LayoutInflater.from(context).inflate(R.layout.mini_program_page, this)

        AoligLog.d("创建中")

        pageWebView = page_webview
        navBar = nav_bar

        pageWebView.post {
            val lp = pageWebView.layoutParams
            lp.height = pageWebView.height - navBar.navHeight - navBar.statusBarHeight
            pageWebView.layoutParams = lp
            // 设置capsule top
            reLayoutCapsule()
            //配置webview
            initWebViewSetting()
            // 注入webview接口
            injectJsInterface()

            pageWebView.loadUrl(url)
        }
    }

    /**
     * 设置capsule top
     */
    private fun reLayoutCapsule() {
        capsule.translationY =
            (navBar.statusBarHeight + (navBar.navHeight - capsule.height) / 2).toFloat()
    }

    /**
     * 配置webview
     */
    private fun initWebViewSetting() {
        val setting = pageWebView.settings

        if (0 != activity.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        setting.javaScriptEnabled = true

    }

    /**
     * 注入Native接口到webview，实现
     */
    private fun injectJsInterface() {
        val ui2Native = UI2Native(this, activity.pageManager)
        pageWebView.addJavascriptInterface(ui2Native, ui2Native.nameSpace)
    }
}