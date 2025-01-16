package com.ferry.gameexplorer.feature.usecase

import com.ferry.gameexplorer.domain.model.Error
import com.ferry.gameexplorer.domain.model.GetGameListOutput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class GetGameListErrorUseCase {
    fun invoke(input: Flow<Result<GetGameListOutput>>): Flow<Error> {
        return input.map {
            if (it.isSuccess) {
                //No Error
                return@map Error()
            }

            val errorMessage = it.exceptionOrNull()?.message ?: ""
            return@map Error(
                code = 400,
                message = errorMessage,
            )
        }
    }
}
