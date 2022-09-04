package tech.antee.stock.domain.repositories

import kotlinx.coroutines.flow.Flow

interface RobotRepository {

    val workingStateFlow: Flow<Boolean>

    suspend fun updateState(isWorking: Boolean)
}
