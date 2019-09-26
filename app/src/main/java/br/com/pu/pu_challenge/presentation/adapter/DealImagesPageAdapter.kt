package br.com.pu.pu_challenge.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import br.com.pu.pu_challenge.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class DealImagesPageAdapter(private val context: Context, private val thumbnails: List<String>) :
    PagerAdapter() {

    override fun getCount() = thumbnails.size

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.page_viewer_image, null)
        val thumbImageView: ImageView = view.findViewById(R.id.dealImageView)

        Glide.with(context)
            .load(thumbnails[position])
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(thumbImageView)
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
