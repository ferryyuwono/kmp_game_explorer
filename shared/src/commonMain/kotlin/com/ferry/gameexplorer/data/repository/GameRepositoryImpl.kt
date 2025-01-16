package com.ferry.gameexplorer.data.repository

import com.ferry.gameexplorer.domain.model.Game
import com.ferry.gameexplorer.domain.model.GetGameListInput
import com.ferry.gameexplorer.domain.model.GetGameListOutput
import com.ferry.gameexplorer.domain.repository.GameRepository
import kotlinx.datetime.Clock
import kotlin.random.Random

class GameRepositoryImpl: GameRepository {
    override suspend fun getGameList(
        input: GetGameListInput,
    ): Result<GetGameListOutput> {
        val total = 10
        val data = mutableListOf<Game>()
        for (num in 1..total) {
            val gameId = Random.Default.nextInt(Int.MAX_VALUE)
            val metacritic = Random.Default.nextInt(5)
            data.add(
                Game(
                    id = gameId,
                    name = "Test $gameId",
                    backgroundImage = "",
                    metacritic = metacritic,
                    released = Clock.System.now().epochSeconds,
                )
            )
        }
        val newData = GetGameListOutput(
            data = data,
            page = input.page,
            isLastPage = false,
            lastData = input.lastData,
        )
        return Result.success(newData)
    }
}