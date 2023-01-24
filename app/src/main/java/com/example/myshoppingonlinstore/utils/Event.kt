package com.example.myshoppingonlinstore.utils

open class Event<out T>(private val _content: T) {
    private var _hasBeenHandled = false
    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (_hasBeenHandled) {
            null
        } else {
            _hasBeenHandled = true
            _content
        }
    }
}