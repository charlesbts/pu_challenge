package br.com.pu.pu_challenge.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pu.pu_challenge.R
import br.com.pu.pu_challenge.domain.entity.Deal
import br.com.pu.pu_challenge.presentation.ScreenState
import br.com.pu.pu_challenge.presentation.activity.DealsActivity
import br.com.pu.pu_challenge.presentation.adapter.DealsAdapter
import br.com.pu.pu_challenge.presentation.viewmodel.DealsViewModel
import kotlinx.android.synthetic.main.fragment_deals.*
import org.koin.android.viewmodel.ext.android.viewModel

class DealsFragment : BaseFragment() {

    private val viewModel : DealsViewModel by viewModel()

    private lateinit var dealsAdapter : DealsAdapter

    override fun layoutId() = R.layout.fragment_deals

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress.visibility = View.VISIBLE
        dealsAdapter = DealsAdapter(context!!,
            {deal -> (activity as DealsActivity).navigateToDealDetailsActivity(deal) })

        viewModel.dealsResult.observe(viewLifecycleOwner,
            Observer {deals ->
                processResult(deals)
            })

        with (dealsRecyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = dealsAdapter
        }
    }

    private fun processResult(screenState: ScreenState<PagedList<Deal>>) {
        when (screenState) {
            ScreenState.NetworkError -> {
                progress.visibility = View.GONE
                genericDialog(getString(R.string.error), getString(R.string.network_error_message))
            }
            is ScreenState.Render -> {
                progress.visibility = View.GONE
                dealsAdapter.submitList(screenState.renderState)
            }
        }
    }
}