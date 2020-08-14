package com.ajithvgiri.wred.utils

import android.widget.ImageView
import com.ajithvgiri.wred.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadProfile(url: String?) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.placeholder_user)
        .apply(RequestOptions.circleCropTransform())
        .circleCrop()
        .into(this)
}