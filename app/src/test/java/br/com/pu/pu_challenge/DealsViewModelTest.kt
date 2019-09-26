package br.com.pu.pu_challenge

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import br.com.pu.pu_challenge.domain.entity.Deal
import br.com.pu.pu_challenge.domain.usecase.GetDealsUseCase
import br.com.pu.pu_challenge.presentation.ScreenState
import br.com.pu.pu_challenge.presentation.viewmodel.DealsViewModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DealsViewModelTest {

    private lateinit var dealsViewModel: DealsViewModel

    private lateinit var getDealsUseCase: GetDealsUseCase

    @Before
    fun setUp() {
        getDealsUseCase = mock()
    }

    @Test
    fun `test the succeed case`() {
        val captor = argumentCaptor<DealsViewModel.GetDealsListener>()
        val pagedList : PagedList<Deal> = mock()


        whenever(getDealsUseCase.invoke(eq(DealsViewModel.DEALS_USE_CASE_TAG), any()))
            .thenAnswer { getDealsUseCase.listenerMap[DealsViewModel.DEALS_USE_CASE_TAG]?.onSuccess(pagedList)}

        dealsViewModel = DealsViewModel(getDealsUseCase)
        Assert.assertNotNull(dealsViewModel)

        println(getDealsUseCase.listenerMap.isEmpty().toString())

        verify(getDealsUseCase, times(1))
            .invoke(eq(DealsViewModel.DEALS_USE_CASE_TAG), captor.capture())

        //Assert.assertEquals(dealsViewModel.dealsResult.value, ScreenState.Render(pagedList))
    }
}