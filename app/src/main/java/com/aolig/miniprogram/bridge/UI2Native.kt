package com.aolig.miniprogram.bridge

import android.webkit.JavascriptInterface
import com.aolig.miniprogram.PageManager
import com.aolig.miniprogram.widget.MiniProgramPage

class UI2Native(private val currentPage: MiniProgramPage, private val pageManager: PageManager) {
    val nameSpace = "__mp"

    @JavascriptInterface
    fun openNewPage(url: String) {
        currentPage.post { pageManager.pushNewPage(url) }
    }
}