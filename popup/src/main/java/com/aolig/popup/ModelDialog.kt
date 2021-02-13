package com.aolig.popup

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.aolig.popup.widget.AutoMaxHeightScrollView
import com.aolig.popup.widget.ChainQueue
import com.aolig.popup.widget.ContentViewAdapter

class ModelDialog(private val ctx: Context) : AlertDialog(ctx, R.style.style_dialog) {
    private val queue = ChainQueue()
    private lateinit var titleView: TextView
    private lateinit var contentView: AutoMaxHeightScrollView
    private lateinit var cancelButton: Button
    private lateinit var confirmButton: Button
    private var cancelCb: ((ModelDialog) -> Unit) = { dismiss() }
    private var confirmCb: ((ModelDialog) -> Unit) = { dismiss() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_base_layout)
        setCancelable(true)
    }

    private fun initView() {
        titleView = findViewById(R.id.dialog_title) ?: TextView(ctx)
        contentView = findViewById(R.id.dialog_content) ?: AutoMaxHeightScrollView(
            ctx
        )
        cancelButton = findViewById(R.id.cancel_button) ?: Button(ctx)
        confirmButton = findViewById(R.id.confirm_button) ?: Button(ctx)
        cancelButton.setOnClickListener { cancelCb(this) }
        confirmButton.setOnClickListener { confirmCb(this) }
        contentView.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
    }

    fun notCancel(): ModelDialog {
        queue.push { setCancelable(false) }
        return this
    }

    fun hideCancel(): ModelDialog {
        queue.push { cancelButton.visibility = View.GONE }
        return this
    }

    fun setTitle(title: String): ModelDialog {
        queue.push { titleView.text = title }
        return this
    }

    fun setContent(content: View): ModelDialog {
        queue.push { contentView.adapter =
            ContentViewAdapter(content)
        }
        return this
    }

    fun onCancel(fn: (dismiss: ModelDialog) -> Unit): ModelDialog {
        queue.push { cancelCb = fn }
        return this
    }

    fun onConfirm(fn: (dismiss: ModelDialog) -> Unit): ModelDialog {
        queue.push { confirmCb = fn }
        return this
    }


    fun show(fn: () -> Unit) {
        show()
        fn()
    }

    override fun show() {
        super.show()
        initView()
        queue.doQueue()
    }

    fun setCancelText(s: String): ModelDialog {
        queue.push { cancelButton.text = s }
        return this
    }

    fun setConfirmText(s: String): ModelDialog {
        queue.push { confirmButton.text = s }
        return this
    }

}