package com.example.niuminiprogram

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.niuminiprogram.utils.NiuLog
import kotlin.system.exitProcess

class MiniProgramActivity : AppCompatActivity() {
    // miniprogram's package name
    private lateinit var mpPackageName: String;

    // start this Activity
    companion object {
        fun start(context: Context, mpPackageName: String) {
            val i = Intent(context, MiniProgramActivity::class.java)
            i.putExtra("package", mpPackageName)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
            NiuLog.d("Open MiniProgram：${mpPackageName}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mini_program)
        mpPackageName = intent.getStringExtra("package") ?: "";

        NiuLog.d("Load MiniProgram：${mpPackageName}")
    }

    override fun onBackPressed() {
        moveTaskToBack(false)
        NiuLog.d("Hide MiniProgram：${mpPackageName}")
    }

    override fun onResume() {
        super.onResume()
        NiuLog.d("Show MiniProgram：${mpPackageName}")
    }

    override fun onPause() {
        super.onPause()
        NiuLog.d("Pause MiniProgram：${mpPackageName}")
    }

    override fun onStop() {
        super.onStop()
        NiuLog.d("Stop MiniProgram：${mpPackageName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        NiuLog.d("Mp process is destroy")
        exitProcess(0);
    }

    private fun close() {
        NiuLog.d("The Mp:${mpPackageName} will be close")
        finish()
    }

    private fun menu() {

    }
}