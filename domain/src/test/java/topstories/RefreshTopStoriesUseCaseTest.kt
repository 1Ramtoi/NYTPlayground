package topstories

import com.example.domain.features.model.TopStoriesSortBy
import com.example.domain.features.repositories.TopStoriesRepository
import com.example.domain.features.usecases.topstories.RefreshTopStoriesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RefreshTopStoriesUseCaseTest {
    private val topStoriesRepoMock = mockk<TopStoriesRepository> {
        coEvery { refreshMostViewed() } returns Unit
        coEvery { refreshMostShared() } returns Unit
        coEvery { refreshMostEmailed() } returns Unit
    }

    private val refreshTopStoriesUseCase = RefreshTopStoriesUseCase(topStoriesRepoMock)

    @Test
    fun `invoke refresh most viewed in repository`() =
        runTest {
            coVerify(exactly = 0) {
                topStoriesRepoMock.refreshMostViewed()
            }
            refreshTopStoriesUseCase.invokeSuspend(TopStoriesSortBy.MOST_VIEWED)
            coVerify(exactly = 1) {
                topStoriesRepoMock.refreshMostViewed()
            }
        }

    @Test
    fun `invoke refresh most shared in repository`() =
        runTest {
            coVerify(exactly = 0) {
                topStoriesRepoMock.refreshMostShared()
            }
            refreshTopStoriesUseCase.invokeSuspend(TopStoriesSortBy.MOST_SHARED)
            coVerify(exactly = 1) {
                topStoriesRepoMock.refreshMostShared()
            }
        }

    @Test
    fun `invoke refresh most emailed in repository`() =
        runTest {
            coVerify(exactly = 0) {
                topStoriesRepoMock.refreshMostEmailed()
            }
            refreshTopStoriesUseCase.invokeSuspend(TopStoriesSortBy.MOST_EMAILED)
            coVerify(exactly = 1) {
                topStoriesRepoMock.refreshMostEmailed()
            }
        }
}