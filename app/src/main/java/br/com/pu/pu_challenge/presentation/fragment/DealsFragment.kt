package br.com.pu.pu_challenge.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pu.pu_challenge.R
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
                progress.visibility = View.GONE
                dealsAdapter.submitList(deals)
            })

        with (dealsRecyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = dealsAdapter
        }
    }
}