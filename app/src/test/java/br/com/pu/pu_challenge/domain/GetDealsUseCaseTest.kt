package br.com.pu.pu_challenge.domain

import androidx.paging.PagedList
import br.com.pu.pu_challenge.domain.entity.Deal
import br.com.pu.pu_challenge.domain.repository.DealRepository
import br.com.pu.pu_challenge.domain.usecase.GetDealsUseCase
import br.com.pu.pu_challenge.domain.usecase.UseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

class GetDealsUseCaseTest {

    private lateinit var getDealsUseCase : GetDealsUseCase

    private lateinit var dealRepository : DealRepository

    @Before
    fun setUp() {
        dealRepository = Mockito.mock(DealRepository::class.java)
        getDealsUseCase = GetDealsUseCase(dealRepository)
        Assert.assertNotNull(getDealsUseCase)
    }

    @Test
    fun `test the use case`() {
        val dealsExpected = listOf(Deal("someId", listOf("someThumb"), "someTitle",
            "someDescription", 100.0.toFloat()))

        val dealsReturned = listOf(Deal("someId", listOf("someThumb"), "someTitle",
            "someDescription", 100.0.toFloat()))

        val pglistExpected = mockPagedList(dealsExpected)
        val pglistReturned = mockPagedList(dealsReturned)
        val listener : UseCase.Listener<PagedList<Deal>> = mock()

        whenever(dealRepository.getDeals()).thenReturn(pglistReturned)
        Dispatchers.setMain(newSingleThreadContext("UI Thread"))
        getDealsUseCase.invoke("TAG", listener)
        Assert.assertEquals(1, getDealsUseCase.listenerMap.size)
        getDealsUseCase.release("TAG")
        Assert.assertEquals(0, getDealsUseCase.listenerMap.size)
        Assert.assertEquals(pglistExpected.snapshot(), pglistReturned.snapshot())
    }

    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        Mockito.`when`(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        Mockito.`when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }
}