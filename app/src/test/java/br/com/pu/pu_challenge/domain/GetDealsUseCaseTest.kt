package br.com.pu.pu_challenge.domain

import br.com.pu.pu_challenge.domain.repository.DealRepository
import br.com.pu.pu_challenge.domain.usecase.GetDealsUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
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

    }
}