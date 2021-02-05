package com.example.niuminiprogram

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    }

    override fun onBackPressed() {
        moveTaskToBack(false)
        NiuLog.d("Hide MiniProgram：${mpPackageName}")
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        NiuLog.d("Hide MiniProgram：${mpPackageName}")
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Niu", "Mp process is destroy")
        exitProcess(0);
    }

    private fun close() {
        Log.d("Niu", "The Mp:${mpPackageName} will be close")
        finish()
    }

    private fun menu(){

    }
}