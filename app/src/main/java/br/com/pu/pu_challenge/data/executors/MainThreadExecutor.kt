package br.com.pu.pu_challenge.data.executors

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class MainThreadExecutor : Executor {
    private val handler = Handler(Looper.getMainLooper())

    override fun execute(r: Runnable) {
        handler.post(r)
    }
}