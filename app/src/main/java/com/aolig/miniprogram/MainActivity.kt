package com.aolig.miniprogram

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aolig.popup.ModelDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        open.setOnClickListener {
            MiniProgramActivity.start(this, "Niu.MiniProgram")
        }
        get_permission.setOnClickListener {
            val textView = TextView(this)
            textView.layoutParams =
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            textView.text = "请您同意以下权限".repeat(100)
            ModelDialog(this)
                .setTitle("权限申请")
                .setContent(textView)
                .hideCancel()
                .onConfirm {
                    Log.d("Niu", "confirm")
                    it.dismiss()
                }
                .setConfirmText("授权")
                .show()
        }
    }
}