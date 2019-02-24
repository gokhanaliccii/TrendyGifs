package com.gokhanaliccii.trendygifs.ui.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gokhanaliccii.trendygifs.R
import com.gokhanaliccii.trendygifs.databinding.FragmentGifDetailBinding
import com.gokhanaliccii.trendygifs.util.gifloader.GlideGifLoader

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifDetailFragment : Fragment() {

    companion object {
        private const val ARG_GIF_URL = "gif_url"

        fun newInstance(gifUrl: String): GifDetailFragment {
            return GifDetailFragment().apply {
                this.gifUrl = gifUrl
            }
        }
    }

    private val gifLoader = GlideGifLoader()
    private lateinit var viewBinding: FragmentGifDetailBinding
    private lateinit var gifUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let {
            gifUrl = it.getString(ARG_GIF_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_gif_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentGifDetailBinding.bind(view)
        gifLoader.loadGif(viewBinding.imageGif, gifUrl)

        viewBinding.imageGif
    }

    override fun onDestroyView() {
        super.onDestroyView()

        gifLoader.cancelGifLoading(viewBinding.imageGif,gifUrl)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ARG_GIF_URL, gifUrl)
    }
}