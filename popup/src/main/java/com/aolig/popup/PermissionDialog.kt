package com.aolig.popup

import android.Manifest
import android.content.Context

object PermissionDialog {

    class GetPermissionResult() {
        var grantCb: (() -> Unit)? = null
        var deniedCb: (() -> Unit)? = null

        fun onGrant(cb: () -> Unit) {
            grantCb = cb
        }

        fun onDenied(cb: () -> Unit) {
            deniedCb = cb
        }
    }

    fun getPermissions(ctx: Context, permissions: List<Manifest.permission>): GetPermissionResult {
        val res = GetPermissionResult()

        ModelDialog(ctx)
            .setTitle("权限申请")
//            .setContent()
            .onConfirm {
                res.grantCb?.let { it() }
            }
            .onCancel {
                res.deniedCb?.let { it() }
            }
            .setConfirmText("授权")
            .show()

        return res
    }
}