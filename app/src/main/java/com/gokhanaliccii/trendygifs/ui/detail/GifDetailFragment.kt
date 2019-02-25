package com.gokhanaliccii.trendygifs.ui.detail

import android.os.Bundle
import com.gokhanaliccii.trendygifs.R
import com.gokhanaliccii.trendygifs.core.base.BindableFragment
import com.gokhanaliccii.trendygifs.databinding.FragmentGifDetailBinding
import com.gokhanaliccii.trendygifs.util.gifloader.GlideGifLoader

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifDetailFragment : BindableFragment<FragmentGifDetailBinding>() {

    companion object {
        private const val ARG_GIF_URL = "gif_url"

        fun newInstance(gifUrl: String): GifDetailFragment {
            return GifDetailFragment().apply {
                this.gifUrl = gifUrl
            }
        }
    }

    override val layoutRes: Int
        get() = R.layout.fragment_gif_detail

    private val gifLoader = GlideGifLoader()
    private lateinit var gifUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let {
            gifUrl = it.getString(ARG_GIF_URL)
        }
    }

    override fun onViewBindingReady(savedInstanceState: Bundle?) {
        gifLoader.loadGif(viewBinding.imageGif, gifUrl)

        viewBinding.imageGif
    }

    override fun onDestroyView() {
        super.onDestroyView()
        gifLoader.cancelGifLoading(viewBinding.imageGif, gifUrl)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ARG_GIF_URL, gifUrl)
    }
}