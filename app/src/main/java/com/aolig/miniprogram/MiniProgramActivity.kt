package com.aolig.miniprogram

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import com.aolig.miniprogram.utils.AoligLog
import com.aolig.miniprogram.widget.Capsule
import com.aolig.miniprogram.widget.MiniProgramPage
import kotlinx.android.synthetic.main.activity_mini_program.*
import java.lang.Thread.sleep
import kotlin.system.exitProcess

class MiniProgramActivity : AppCompatActivity() {
    // miniprogram's package name
    private lateinit var mpPackageName: String;
    private var exitThread: Thread? = null
    private lateinit var capsuleView: Capsule

    // start this Activity
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
        // set capsule's event
        capsuleView.setOnMenuClickListener { }
        capsuleView.setOnCloseClickListener { close() }

        pushNewWebView("https://www.baidu.com")
    }

    fun pushNewWebView(href: String) {
        val page = MiniProgramPage(this, href, capsuleView)

        webview_container.addView(page, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    override fun onBackPressed() {
        close()
    }

    override fun onResume() {
        super.onResume()
        exitThread = null
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
        moveTaskToBack(false)
        AoligLog.d("The Mp:${mpPackageName} will be close after 10s")
        // wait 10s to destroy program
        exitThread = Thread {
            sleep(10000)
            if (exitThread != null) {
                finish()
                AoligLog.d("The Mp:${mpPackageName} is close")
                exitProcess(0)
            }
        }
        exitThread!!.start()
    }
}