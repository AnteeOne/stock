package tech.antee.stock.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import tech.antee.stock.domain.repositories.RobotRepository
import javax.inject.Inject

class RobotRepositoryImpl @Inject constructor() : RobotRepository {

    private val _workingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val workingStateFlow: Flow<Boolean> = _workingStateFlow

    override suspend fun updateState(isWorking: Boolean) {
        _workingStateFlow.emit(isWorking)
    }
}
