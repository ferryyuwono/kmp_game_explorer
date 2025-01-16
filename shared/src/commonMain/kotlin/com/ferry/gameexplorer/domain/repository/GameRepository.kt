package com.ferry.gameexplorer.domain.repository

import com.ferry.gameexplorer.domain.model.GetGameListInput
import com.ferry.gameexplorer.domain.model.GetGameListOutput

interface GameRepository {
    suspend fun getGameList(
        input: GetGameListInput
    ): Result<GetGameListOutput>
}
