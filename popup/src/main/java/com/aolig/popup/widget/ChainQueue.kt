package com.aolig.popup.widget

class ChainQueue {

    private val queue = mutableListOf<Function>()

    fun push(f: () -> Unit) {
        queue.add(Function(f))
    }

    fun doQueue() {
        for (f in queue) f.function()
    }

    fun clear() {
        queue.clear()
    }


    data class Function(var function: () -> Any? = {})

}