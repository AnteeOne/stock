package tech.antee.stock.domain.usecases

import kotlinx.coroutines.flow.Flow
import tech.antee.stock.domain.models.SubResult
import tech.antee.stock.domain.repositories.StockRepository
import javax.inject.Inject

class GetSubResultsUsecase @Inject constructor(
    private val stockRepository: StockRepository
) {

    suspend operator fun invoke(): Flow<List<SubResult>> = stockRepository.getSubResultsFlow()
}
