package com.example.domain.common.interactors

abstract class CoroutineCompletableUseCase<in Params> {

    protected abstract suspend fun execute(params: Params?)

    suspend fun invokeSuspend(params: Params?) {
        execute(params)
    }

    suspend fun invokeWithoutDispatcher(params: Params?) {
        execute(params)
    }
}