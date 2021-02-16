package com.aolig.miniprogram

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.aolig.miniprogram.helper.SlideAnimationHelper
import com.aolig.miniprogram.widget.Capsule
import com.aolig.miniprogram.widget.MiniProgramPage

/**
 * 页面管理器，页面的堆叠方式遵循后进先出的栈原则
 */
class PageManager(
    private val activity: MiniProgramActivity,
    private val webviewContainer: ConstraintLayout,
    private val capsuleView: Capsule
) : IPageManager {
    // 页面栈
    private val pageStack: MutableList<MiniProgramPage> = mutableListOf()

    // 索引位置
    private var stackIndex = 0


    override fun pushNewPage(href: String): MiniProgramPage {
        val page = MiniProgramPage(stackIndex, activity, href, capsuleView)
        pageStack.add(stackIndex, page)
        stackIndex++

        // 模拟页面入场动画
        SlideAnimationHelper.slideInFromRight(activity, page)
        webviewContainer.addView(
            page,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return page
    }

    override fun removePage(index: Int) {
        val page = pageStack.removeAt(index)
        SlideAnimationHelper.slideOutToRight(activity, page)
        webviewContainer.removeView(page)
        stackIndex--
    }

    override fun getPage(index: Int): MiniProgramPage {
        return pageStack[stackIndex]
    }

    override fun getStackLength(): Int {
        return pageStack.size
    }

    override fun exitLastPage() {
        removePage(stackIndex - 1)
    }

}