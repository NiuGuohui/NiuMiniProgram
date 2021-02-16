package com.aolig.miniprogram

import com.aolig.miniprogram.widget.MiniProgramPage

interface IPageManager {
    /**
     * 打开一个新页面
     */
    fun pushNewPage(href: String): MiniProgramPage

    /**
     * 推出指定位置的页面
     */
    fun removePage(index: Int)

    /**
     * 获取指定位置上的页面
     */
    fun getPage(index: Int): MiniProgramPage

    /**
     * 获取页面栈的堆叠数
     */
    fun getStackLength(): Int

    /**
     * 推出栈顶页面
     */
    fun exitLastPage()

}