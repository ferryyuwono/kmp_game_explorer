package com.ferry.gameexplorer.feature.usecase

import com.ferry.gameexplorer.domain.model.GetGameListOutput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class GetGameListSuccessUseCase {
    fun invoke(input: Flow<Result<GetGameListOutput>>): Flow<GetGameListOutput> {
        return input.filter {
            it.isSuccess
        }.map {
            val result = it.getOrDefault(
                GetGameListOutput()
            )
            return@map result.copy(
                data = result.lastData + result.data,
            )
        }
    }
}
