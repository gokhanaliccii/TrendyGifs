package com.gokhanaliccii.trendygifs.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.gokhanaliccii.trendygifs.R
import com.gokhanaliccii.trendygifs.core.base.BindableViewModelFragment
import com.gokhanaliccii.trendygifs.core.router.GifDetailRouter
import com.gokhanaliccii.trendygifs.core.viewmodel.ViewModelFactory
import com.gokhanaliccii.trendygifs.databinding.FragmentGifListBinding
import com.gokhanaliccii.trendygifs.domain.model.GifUIItem
import com.gokhanaliccii.trendygifs.ui.list.adapter.GifListAdapter
import com.gokhanaliccii.trendygifs.util.getRandomColor
import com.gokhanaliccii.trendygifs.util.getWhiteColor
import com.gokhanaliccii.trendygifs.util.gifloader.GlideGifLoader
import com.gokhanaliccii.trendygifs.util.recyclerview.ItemClickListener
import com.gokhanaliccii.trendygifs.util.recyclerview.RecyclerViewLoadMoreListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifListFragment :
    BindableViewModelFragment<FragmentGifListBinding, GifListViewModel>(),
    ItemClickListener<GifUIItem> {

    override val layoutRes: Int
        get() = R.layout.fragment_gif_list

    override val viewModelFactory: ViewModelProvider.Factory?
        get() = ViewModelFactory(context!!)

    private lateinit var gifDetailRouter: GifDetailRouter

    private lateinit var loadMoreListener: RecyclerViewLoadMoreListener
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun getViewModel(): Class<GifListViewModel> = GifListViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gifDetailRouter = GifDetailRouter(fragmentManager!!)
    }

    override fun onViewBindingReady(savedInstanceState: Bundle?) {
        val gridLayoutManager = GridLayoutManager(context, getColumnCount())
        val gifListAdapter = GifListAdapter(GlideGifLoader(), this)

        loadMoreListener = RecyclerViewLoadMoreListener(gridLayoutManager) {
            viewModel.loadGifs()
        }

        viewBinding.recyclerViewGif.run {
            layoutManager = gridLayoutManager
            adapter = gifListAdapter
        }

        viewBinding.recyclerViewGif.addOnScrollListener(loadMoreListener)

        viewModel.gifList.observe(this,
            Observer {
                gifListAdapter.updateList(it!!)
            })

        viewModel.moreGifsLoadingObserver().observe(this, Observer {
            viewBinding.bottomLoadingVisible = it
        })

        viewModel.contentGifsLoadingObserver().observe(this, Observer {
            viewBinding.contentLoadingVisible = it

            if (it!!) {
                showRandomColorUntilDataLoad()
            } else {
                viewBinding.loadingColor = getWhiteColor()
                compositeDisposable.clear()
            }
        })

    }

    override fun onItemClick(gifUIItem: GifUIItem, view: View?) {
        gifDetailRouter.routeToDetail(gifUIItem)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loadGifs()
    }

    private fun getColumnCount(): Int {
        return resources.getInteger(R.integer.trend_list_column_count)
    }

    private fun showRandomColorUntilDataLoad() {
        viewBinding.contentLoadingVisible = true

        compositeDisposable.add(
            Observable.interval(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { a -> showRandomColorOnLoader() })
    }

    private fun showRandomColorOnLoader() {
        viewBinding.contentLoadingVisible = true
        viewBinding.loadingColor = getRandomColor()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewBinding.recyclerViewGif.removeOnScrollListener(loadMoreListener)
    }
}