package br.com.pu.pu_challenge.presentation

sealed class ScreenState<out T> {
    object NetworkError: ScreenState<Nothing>()
    class Render<T>(val renderState: T) : ScreenState<T>()
}