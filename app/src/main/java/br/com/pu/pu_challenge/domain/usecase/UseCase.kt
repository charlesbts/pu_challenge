package br.com.pu.pu_challenge.domain.usecase

import kotlinx.coroutines.*
import java.lang.Exception

/**
 * Necessary infrastructure to implement each use case. Every interactor must extends it. It calls
 * the repository on the background and update the viewmodel using the main thread.
 */
abstract class UseCase<T> {

    interface Listener<T> {
        fun onSuccess(result: T)
        fun onError(throwable: Throwable)
    }

    var listenerMap: MutableMap<String, Listener<T>?> = mutableMapOf()

    fun release(tag: String) {
        listenerMap[tag] = null
    }

    operator fun invoke(tag: String, listener: Listener<T>) {
        listenerMap[tag] = listener
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val res = withContext(Dispatchers.IO) {
                    async { run() }
                }

                val result = res.await()
                listenerMap[tag]?.onSuccess(result)
            } catch (e: Exception) {
                listenerMap[tag]?.onError(e)
            }
        }
    }

    /**
     * Method where the use case runs.
     */
    abstract fun run() : T
}