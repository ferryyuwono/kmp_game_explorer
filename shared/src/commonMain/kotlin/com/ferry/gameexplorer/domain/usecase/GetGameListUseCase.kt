package com.ferry.gameexplorer.domain.usecase

import com.ferry.gameexplorer.domain.model.GetGameListInput
import com.ferry.gameexplorer.domain.model.GetGameListOutput
import com.ferry.gameexplorer.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetGameListUseCase(
    private val repository: GameRepository,
) {
    fun invoke(input: Flow<GetGameListInput>): Flow<Result<GetGameListOutput>> {
        return input.map {
            if (it.page == 0) {
                //Reset
                return@map Result.success(GetGameListOutput())
            }

            return@map repository.getGameList(it)
        }
    }
}
