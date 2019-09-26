package br.com.pu.pu_challenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import br.com.pu.pu_challenge.domain.entity.Deal
import br.com.pu.pu_challenge.domain.usecase.GetDealsUseCase
import br.com.pu.pu_challenge.domain.usecase.UseCase

class DealsViewModel(private val getDealsUseCase: GetDealsUseCase): ViewModel() {

    var dealsResult : MutableLiveData<PagedList<Deal>> = MutableLiveData()

    init {
        resume()
    }

    private fun resume() {
        getDealsUseCase(GetDealsUseCase::class.toString(), this.GetDealsListener())
    }

    override fun onCleared() {
        getDealsUseCase.release(GetDealsUseCase::class.toString())
        super.onCleared()
    }

    inner class GetDealsListener : UseCase.Listener<PagedList<Deal>> {

        override fun onSuccess(result: PagedList<Deal>) {
            dealsResult.value = result
        }

        override fun onError(throwable: Throwable) {
            Log.d("ERR", throwable.message)
        }
    }
}