package br.com.pu.pu_challenge.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import br.com.pu.pu_challenge.R
import br.com.pu.pu_challenge.domain.entity.Deal
import br.com.pu.pu_challenge.presentation.adapter.DealImagesPageAdapter
import br.com.pu.pu_challenge.presentation.toBundle
import br.com.pu.pu_challenge.presentation.toEntity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_deal_details.*


class DealDetailsFragment : BaseFragment() {

    private lateinit var deal: Deal

    companion object {
        fun newInstance(deal: Deal) : DealDetailsFragment {
            val dealDetailsFragment = DealDetailsFragment()

            dealDetailsFragment.arguments = deal.toBundle()
            return dealDetailsFragment
        }
    }

    override fun layoutId() = R.layout.fragment_deal_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deal = arguments?.toEntity() as Deal

        val dealImagesAdapter = DealImagesPageAdapter(context!!, deal.thumbUrls)
        (tabDots as TabLayout).setupWithViewPager(pager as ViewPager, true)

        pager.adapter = dealImagesAdapter

        dealTitleTextView.text = deal.title
        dealDescriptionTextView.text = deal.description
    }

}