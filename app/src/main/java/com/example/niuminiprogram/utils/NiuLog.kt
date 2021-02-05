package com.example.niuminiprogram.utils

import android.util.Log

object NiuLog {
    private const val TAG: String = "Niu";

    fun d(s: String) = Log.d(TAG, s)

    fun v(s: String) = Log.v(TAG, s)

    fun e(s: String) = Log.e(TAG, s)

    fun w(s: String) = Log.w(TAG, s)

    fun i(s: String) = Log.i(TAG, s)
}