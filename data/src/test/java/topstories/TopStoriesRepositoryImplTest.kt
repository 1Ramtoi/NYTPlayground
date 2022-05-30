package topstories

import com.example.data.common.model.mapToLocalArticles
import com.example.data.features.datasource.cache.ArticleDao
import com.example.data.features.datasource.remote.topstories.TopStoriesService
import com.example.data.features.repositories.TopStoriesRepositoryImpl
import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.spy

@ExperimentalCoroutinesApi
class TopStoriesRepositoryImplTest {
    private val mostViewedMock = listOf(
        Article(id = "1", title = "", abstract = "", published_date = ""),
        Article(id = "2", title = "", abstract = "", published_date = ""),
        Article(id = "3", title = "", abstract = "", published_date = "")
    )

    private val mostSharedMock = listOf(
        Article(id = "2", title = "", abstract = "", published_date = ""),
        Article(id = "3", title = "", abstract = "", published_date = ""),
        Article(id = "1", title = "", abstract = "", published_date = "")
    )

    private val mostEmailedMock = listOf(
        Article(id = "3", title = "", abstract = "", published_date = ""),
        Article(id = "2", title = "", abstract = "", published_date = ""),
        Article(id = "1", title = "", abstract = "", published_date = "")
    )

    @Test
    fun `refresh invokes service and dao`() =
        runTest {
            val topStoriesServiceMock = mockk<TopStoriesService>() {
                coEvery { fetchTopViewed() } returns mostViewedMock
                coEvery { fetchTopShared() } returns mostSharedMock
                coEvery { fetchTopEmailed() } returns mostEmailedMock
            }

            // this did not work, room dao issues?
//            val topStoriesDaoMock = mockk<ArticleDao>() {
//                coEvery { insertAllReplace(mostViewedMock.mapToLocalArticles(TopStoriesSortBy.MOST_VIEWED)) }
//                coEvery { insertAllReplace(mostSharedMock.mapToLocalArticles(TopStoriesSortBy.MOST_SHARED)) }
//                coEvery { insertAllReplace(mostEmailedMock.mapToLocalArticles(TopStoriesSortBy.MOST_EMAILED)) }
//            }

            val topStoriesDaoSpy = spy<ArticleDao> {

            }

            val topStoriesRepository = TopStoriesRepositoryImpl(topStoriesServiceMock, topStoriesDaoSpy)

            // viewed
            coVerify(exactly = 0) {
                topStoriesServiceMock.fetchTopViewed()
                topStoriesDaoSpy.insertAllReplace(mostViewedMock.mapToLocalArticles(TopStoriesSortBy.MOST_VIEWED))
            }
            topStoriesRepository.refreshMostViewed()
            coVerify(exactly = 1) {
                topStoriesServiceMock.fetchTopViewed()
                topStoriesDaoSpy.insertAllReplace(mostViewedMock.mapToLocalArticles(TopStoriesSortBy.MOST_VIEWED))
            }

            // shared
            coVerify(exactly = 0) {
                topStoriesServiceMock.fetchTopShared()
                topStoriesDaoSpy.insertAllReplace(mostSharedMock.mapToLocalArticles(TopStoriesSortBy.MOST_SHARED))
            }
            topStoriesRepository.refreshMostShared()
            coVerify(exactly = 1) {
                topStoriesServiceMock.fetchTopShared()
                topStoriesDaoSpy.insertAllReplace(mostSharedMock.mapToLocalArticles(TopStoriesSortBy.MOST_SHARED))
            }

            // emailed
            coVerify(exactly = 0) {
                topStoriesServiceMock.fetchTopEmailed()
                topStoriesDaoSpy.insertAllReplace(mostEmailedMock.mapToLocalArticles(TopStoriesSortBy.MOST_EMAILED))
            }
            topStoriesRepository.refreshMostEmailed()
            coVerify(exactly = 1) {
                topStoriesServiceMock.fetchTopEmailed()
                topStoriesDaoSpy.insertAllReplace(mostEmailedMock.mapToLocalArticles(TopStoriesSortBy.MOST_EMAILED))
            }
        }

    @Test
    fun `observe returns flow`() =
        runTest {

        }
}