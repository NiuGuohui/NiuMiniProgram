package com.aolig.miniprogram

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aolig.miniprogram.helper.AoligLog
import com.aolig.miniprogram.widget.Capsule
import kotlinx.android.synthetic.main.activity_mini_program.*
import kotlin.system.exitProcess

class MiniProgramActivity : AppCompatActivity() {
    // 小程序 package name
    private lateinit var mpPackageName: String;
    private lateinit var capsuleView: Capsule

    lateinit var pageManager: PageManager

    // 启动这个Activity
    companion object {
        fun start(context: Context, mpPackageName: String) {
            val i = Intent(context, MiniProgramActivity::class.java)
            i.putExtra("package", mpPackageName)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
            AoligLog.d("Open MiniProgram：${mpPackageName}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mini_program)
        mpPackageName = intent.getStringExtra("package") ?: "";
        AoligLog.d("Load MiniProgram：${mpPackageName}")
        capsuleView = capsule
        // 设置capsule的菜单按钮点击事件
        capsuleView.setOnMenuClickListener { menu() }
        // 设置capsule的关闭按钮点击事件
        capsuleView.setOnCloseClickListener { close() }
        // 页面管理器
        pageManager = PageManager(this, webview_container, capsuleView)

        pageManager.pushNewPage("file:///android_asset/miniprogram/test/index.html")
    }

    override fun onBackPressed() {
        if (pageManager.getStackLength() == 1) {
            moveTaskToBack(false)
        } else {
            pageManager.exitLastPage()
        }
    }

    override fun onResume() {
        super.onResume()
        AoligLog.d("Show MiniProgram：${mpPackageName}")
    }

    override fun onPause() {
        super.onPause()
        AoligLog.d("Hide MiniProgram：${mpPackageName}")
    }

    override fun onStop() {
        super.onStop()
        AoligLog.d("Stop MiniProgram：${mpPackageName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        AoligLog.d("Mp process is destroy")
    }

    fun close() {
        finish()
        AoligLog.d("The Mp:${mpPackageName} is close")
        exitProcess(0)
    }

    fun menu() {

    }
}