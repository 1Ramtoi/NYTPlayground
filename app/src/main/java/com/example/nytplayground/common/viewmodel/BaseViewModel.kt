package com.example.nytplayground.common.viewmodel

import androidx.lifecycle.*
import com.example.domain.common.interactors.CoroutineCompletableUseCase
import com.example.domain.common.interactors.CoroutineSingleUseCase
import com.example.domain.common.interactors.FlowUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, E>(s: S) : ViewModel() {

    val state = MutableStateFlow<S>(s)

    var stateCount = 0

    private val eventChannel = Channel<E>(Channel.BUFFERED)

    fun getViewModelState(): StateFlow<S> = state

    fun getEventLiveData(): Flow<E> = eventChannel.receiveAsFlow()


    fun <T> CoroutineCompletableUseCase<T>.invokeWithViewState(
        params: T
    ) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            handleException(throwable)
        }
        viewModelScope.launch(handler) { invokeSuspend(params) }
    }

    inline fun <T> CoroutineCompletableUseCase<T>.invoke(
        params: T,
        crossinline onComplete: () -> Unit,
        crossinline onError: (e: Throwable) -> Unit
    ) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            onError(throwable)
        }
        viewModelScope.launch(handler) {
            invokeSuspend(params)
            onComplete()
        }
    }


    inline fun <T, V> CoroutineSingleUseCase<T, V>.invoke(
        params: V,
        crossinline onSuccess: (t: T) -> Unit,
        crossinline onError: (e: Throwable) -> Unit
    ) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            onError(throwable)
        }
        viewModelScope.launch(handler) {
            val result = invokeSuspend(params)
            onSuccess(result)
        }
    }


    inline fun <T, V> FlowUseCase<T, V>.execute(
        params: V,
        crossinline stateReducer: S.(t: T) -> S,
        crossinline handleError: (t: Throwable?) -> Unit
    ) {
        viewModelScope.launch {
            buildUseCaseFlow(params)
                .catch { handleError(it) }
                .collect { state.value = stateReducer(requireData(), it) }
        }
    }


    inline fun <T, V> FlowUseCase<T, V>.executeWithViewState(
        params: V,
        crossinline stateReducer: S.(t: T) -> S
    ) {
        viewModelScope.launch {
            buildUseCaseFlow(params)
                .catch { handleException(it) }
                .collect { state.value = stateReducer(requireData(), it) }
        }
    }

    fun requireData(): S {
        return state.value!!
    }


    fun emitEvent(eventToEmit: E) {
        viewModelScope.launch {
            eventChannel.send(eventToEmit)
        }
    }


    fun handleException(e: Throwable?) {
    }
}
