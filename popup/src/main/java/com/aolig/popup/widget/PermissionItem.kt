package com.aolig.popup.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.aolig.popup.R


class PermissionItem(private val ctx: Context, attrs: AttributeSet) : RelativeLayout(ctx, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.permission_item, this)

    }

}