package com.example.domain.common.interactors

abstract class CoroutineSingleUseCase<T, in Params> {
    protected abstract suspend fun execute(params: Params?): T

    suspend fun invokeSuspend(params: Params?): T {
        return execute(params)
    }
}