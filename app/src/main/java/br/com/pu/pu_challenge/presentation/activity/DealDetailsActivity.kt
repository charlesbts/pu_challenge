package br.com.pu.pu_challenge.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import br.com.pu.pu_challenge.R
import br.com.pu.pu_challenge.domain.entity.Deal
import br.com.pu.pu_challenge.presentation.fragment.BaseFragment
import br.com.pu.pu_challenge.presentation.fragment.DealDetailsFragment
import br.com.pu.pu_challenge.presentation.toEntity

class DealDetailsActivity : BaseActivity() {

    private lateinit var deal: Deal

    override fun layout() = R.layout.activity_layout

    override fun fragment() : BaseFragment{
        deal = intent.getBundleExtra(DealsActivity.DEAL_EXTRA).toEntity()
        return DealDetailsFragment.newInstance(deal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = deal.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            supportFinishAfterTransition()
        }
        return super.onOptionsItemSelected(item)
    }

}