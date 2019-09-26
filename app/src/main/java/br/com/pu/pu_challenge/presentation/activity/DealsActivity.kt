package br.com.pu.pu_challenge.presentation.activity

import android.content.Intent
import android.os.Bundle
import br.com.pu.pu_challenge.domain.entity.Deal
import br.com.pu.pu_challenge.presentation.fragment.DealsFragment
import br.com.pu.pu_challenge.presentation.toBundle
import androidx.core.app.ActivityOptionsCompat
import br.com.pu.pu_challenge.R
import kotlinx.android.synthetic.main.recycler_deals.*


class DealsActivity : BaseActivity() {

    companion object {
        const val DEAL_EXTRA = "deal_extra"
    }

    override fun layout() = R.layout.activity_layout

    override fun fragment() = DealsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.app_name)
    }

    fun navigateToDealDetailsActivity(deal: Deal) {
        val intent = Intent(this, DealDetailsActivity::class.java)
        intent.putExtra(DEAL_EXTRA, deal.toBundle())
        startActivity(intent)
    }
}