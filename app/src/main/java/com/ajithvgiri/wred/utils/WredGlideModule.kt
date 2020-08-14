package com.ajithvgiri.wred.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory.Builder
import com.bumptech.glide.signature.ObjectKey
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.concurrent.TimeUnit

@GlideModule
class WredGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        //use 25% of available heap size
        val cacheSize = Runtime.getRuntime().maxMemory() / 4
        builder.setMemoryCache(LruResourceCache(cacheSize.toLong()))
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, cacheSize.toLong()))
        builder.setDefaultRequestOptions(requestOptions())
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        val factory = OkHttpUrlLoader.Factory(client)
        glide.registry.replace(
            GlideUrl::class.java,
            InputStream::class.java, factory
        )
    }

    companion object {

        var drawableCrossFadeFactory: DrawableCrossFadeFactory = Builder().setCrossFadeEnabled(true).build()

        private fun requestOptions(): RequestOptions {
            return RequestOptions()
                .signature(ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000)))
                .override( Target.SIZE_ORIGINAL)
                .encodeQuality(100)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .format(DecodeFormat.PREFER_RGB_565)
                .skipMemoryCache(false)
        }
    }
}