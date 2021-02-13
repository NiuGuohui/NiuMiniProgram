package com.aolig.miniprogram.widget

import android.content.Context
import android.view.LayoutInflater
import android.webkit.WebView
import androidx.constraintlayout.widget.ConstraintLayout
import com.aolig.miniprogram.R
import kotlinx.android.synthetic.main.mini_program_page.view.*

class MiniProgramPage(private val ctx: Context) : ConstraintLayout(ctx) {
    lateinit var pageWebView: WebView
    lateinit var navBar: CustomNavBar
    private lateinit var url: String
    private lateinit var capsule: Capsule

    init {
        LayoutInflater.from(ctx).inflate(R.layout.mini_program_page, this)

        pageWebView = page_webview
        navBar = nav_bar

        pageWebView.post {
            val lp = pageWebView.layoutParams
            lp.height = pageWebView.height - navBar.navHeight - navBar.statusBarHeight
            pageWebView.layoutParams = lp
            // set capsule top
            reLayoutCapsule()

            pageWebView.loadUrl(url)
        }
    }

    constructor(ctx: Context, url: String, capsule: Capsule) : this(ctx) {
        this.url = url
        this.capsule = capsule
    }

    // set capsule top
    private fun reLayoutCapsule() {
        capsule.translationY =
            (navBar.statusBarHeight + (navBar.navHeight - capsule.height) / 2).toFloat()
    }
}