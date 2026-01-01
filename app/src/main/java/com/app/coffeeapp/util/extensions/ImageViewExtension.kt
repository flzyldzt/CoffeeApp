package com.app.coffeeapp.util.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.app.coffeeapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun ImageView.loadImageWithProgress(
    url: String?,
    progressBar: ProgressBar
) {
    progressBar.visibility = View.VISIBLE
    this.visibility = View.INVISIBLE

    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .listener(object : RequestListener<Drawable> {


            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                this@loadImageWithProgress.visibility = View.VISIBLE
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                return false
            }
        })
        .into(this)
}
