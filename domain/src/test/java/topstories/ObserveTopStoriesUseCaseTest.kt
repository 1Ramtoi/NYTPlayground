package topstories

import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy
import com.example.domain.features.repositories.TopStoriesRepository
import com.example.domain.features.usecases.topstories.ObserveTopStoriesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ObserveTopStoriesUseCaseTest {

    @Test
    fun `returns articles as flow`() =
        runTest {
            val mostViewedMock = listOf(
                Article(id = "1", title = "", abstract = "", published_date = ""),
                Article(id = "2", title = "", abstract = "", published_date = ""),
                Article(id = "3", title = "", abstract = "", published_date = "")
            )

            val mostSharedMock = listOf(
                Article(id = "2", title = "", abstract = "", published_date = ""),
                Article(id = "3", title = "", abstract = "", published_date = ""),
                Article(id = "1", title = "", abstract = "", published_date = "")
            )

            val mostEmailedMock = listOf(
                Article(id = "3", title = "", abstract = "", published_date = ""),
                Article(id = "2", title = "", abstract = "", published_date = ""),
                Article(id = "1", title = "", abstract = "", published_date = "")
            )

            val topStoriesRepoMock = mockk<TopStoriesRepository> {
                coEvery { observeMostViewed() } returns flowOf(mostViewedMock)
                coEvery { observeMostShared() } returns flowOf(mostSharedMock)
                coEvery { observeMostEmailed() } returns flowOf(mostEmailedMock)
            }

            val observeTopStoriesUseCase = ObserveTopStoriesUseCase(topStoriesRepoMock)

            val listMostViewed = observeTopStoriesUseCase.buildStream(TopStoriesSortBy.MOST_VIEWED).first()
            assertEquals(mostViewedMock, listMostViewed)

            val listMostShared = observeTopStoriesUseCase.buildStream(TopStoriesSortBy.MOST_SHARED).first()
            assertEquals(mostSharedMock, listMostShared)

            val listMostEmailed = observeTopStoriesUseCase.buildStream(TopStoriesSortBy.MOST_EMAILED).first()
            assertEquals(mostEmailedMock, listMostEmailed)
        }
}