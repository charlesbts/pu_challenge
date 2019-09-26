package br.com.pu.pu_challenge.data.executors

import java.util.concurrent.Executor
import java.util.concurrent.Executors

internal class BackgroundExecutor : Executor {
    private val executorService = Executors.newFixedThreadPool(2)

    override fun execute(command: Runnable) {
        executorService.execute(command)
    }
}