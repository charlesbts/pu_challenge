package br.com.pu.pu_challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import br.com.pu.pu_challenge.domain.entity.Deal
import br.com.pu.pu_challenge.domain.usecase.GetDealsUseCase
import br.com.pu.pu_challenge.domain.usecase.UseCase
import br.com.pu.pu_challenge.presentation.ScreenState

class DealsViewModel(private val getDealsUseCase: GetDealsUseCase): ViewModel() {

    companion object {
        const val DEALS_USE_CASE_TAG = "deals_use_case"
    }

    var dealsResult : MutableLiveData<ScreenState<PagedList<Deal>>> = MutableLiveData()

    init {
        resume()
    }

    private fun resume() {
        getDealsUseCase(DEALS_USE_CASE_TAG, this.GetDealsListener())
    }

    override fun onCleared() {
        getDealsUseCase.release(DEALS_USE_CASE_TAG)
        super.onCleared()
    }

    inner class GetDealsListener : UseCase.Listener<PagedList<Deal>> {

        override fun onSuccess(result: PagedList<Deal>) {
            dealsResult.value = ScreenState.Render(result)
        }

        override fun onError(throwable: Throwable) {
            dealsResult.value = ScreenState.NetworkError
        }
    }
}