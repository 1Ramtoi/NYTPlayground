package com.example.domain.common.interactors

import kotlinx.coroutines.flow.Flow

/**
Retrieves changes from the data layer.
It returns an {Flow} that emits updates for the retrieved object. The returned {Flowable} will never complete, but it can
error if there are any problems performing the required actions to serve the data.
 */

abstract class FlowUseCase<T, in Params>  {

    abstract fun buildStream(params: Params?): Flow<T>

    fun buildUseCaseFlow(params: Params?): Flow<T> {
        return buildStream(params)
    }
}