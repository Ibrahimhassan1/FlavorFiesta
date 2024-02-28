package com.softups.flavorfiesta.common

// Attribute: https://www.droidcon.com/2022/04/26/modeling-retrofit-responses-with-sealed-classes-and-coroutines/
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}