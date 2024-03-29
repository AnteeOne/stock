package tech.antee.stock.common_ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<State, Event> : ViewModel() {

    protected abstract val _uiState: MutableStateFlow<State>
    val uiState: StateFlow<State> get() = _uiState

    private val _uiEvents = Channel<Event>(capacity = Channel.UNLIMITED)
    val uiEvents: Flow<Event> = _uiEvents.receiveAsFlow()

    protected open fun onLoading(inProgress: Boolean) {}

    protected open fun onError(exception: Throwable) {
        exception.printStackTrace()
    }

    protected fun launchSafely(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        onLoading: (Boolean) -> Unit = ::onLoading,
        onError: (Throwable) -> Unit = ::onError,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(context, start) {
            try {
                onLoading(true)
                block()
            } catch (e: Throwable) {
                if (e is CancellationException) throw e
                e.printStackTrace()
                onError(e)
            } finally {
                onLoading(false)
            }
        }
    }

    protected inline fun <reified T : State> updateIfStateIs(
        afterUpdate: (currentState: T) -> Unit = {},
        newState: (currentState: T) -> T
    ) {
        updateState(
            afterUpdate = { state ->
                if (state is T) afterUpdate(state)
            }
        ) { currentState ->
            if (currentState is T) {
                newState(currentState)
            } else {
                Log.w(
                    javaClass.simpleName,
                    "updateState: current state = $currentState, expected = ${T::class.qualifiedName}"
                )
                currentState
            }
        }
    }

    protected inline fun <reified Old : State, reified New : State> replaceIfStateIs(
        crossinline afterUpdate: (currentState: New) -> Unit = {},
        newState: (currentState: Old) -> New
    ) {
        updateState(
            afterUpdate = { state ->
                if (state is New) afterUpdate(state)
            }
        ) { currentState ->
            if (currentState is Old) {
                newState(currentState)
            } else {
                Log.w(
                    javaClass.simpleName,
                    "replaceState: current state = $currentState, expected = ${Old::class.qualifiedName}"
                )
                currentState
            }
        }
    }

    protected inline fun updateState(afterUpdate: (State) -> Unit = {}, function: (State) -> State) {
        _uiState.update(function)
        afterUpdate(_uiState.value)
    }

    protected fun emitEvent(event: Event): ChannelResult<Unit> = _uiEvents.trySend(event)
}
