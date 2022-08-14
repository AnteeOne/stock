package tech.antee.stock.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Returns an existing [ViewModel] or creates a new one in the [LocalViewModelStoreOwner] by using
 * [viewModelInstanceCreator]
 *
 * The created [ViewModel] is associated with the [LocalViewModelStoreOwner] and will be retained
 * as long as the owner is alive (e.g. if it is an activity, until it is
 * finished or process is killed).
 *
 * @param key The key to use to identify the [ViewModel].
 * @param viewModelInstanceCreator The  lambda that should be used in the [ViewModelProvider.Factory]
 * implementation to create the [ViewModel]
 * @return A [ViewModel] that is an instance of the given [VM] type.
 */
@Composable
inline fun <reified VM : ViewModel> injectedViewModel(
    key: String? = null,
    crossinline viewModelInstanceCreator: () -> VM
): VM = viewModel(
    key = key,
    factory = object : ViewModelProvider.Factory {
        override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
            @Suppress("UNCHECKED_CAST")
            return viewModelInstanceCreator() as VM
        }
    }
)
