package com.zain.fly365.base.domain.usecases


/**
 * interface for all use cases to extend
 * @param P  use case parameters
 * @param R  use case return type
 */
interface UseCase<in P, R> {
    fun execute(param: P?): R
}