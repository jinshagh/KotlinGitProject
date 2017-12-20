package com.btlm.kotlingitproject.base

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.utils.AppUtils
import org.jetbrains.anko.toast

/**
 * Created by Administrator on 2017/12/20.
 */
abstract class BaseRefreshFragment<T : BaseContract.BasePresenter<*>,K> : BaseInjectFragment<T>(),SwipeRefreshLayout.OnRefreshListener{
    protected var mRecycler : RecyclerView? = null
    protected var mRefresh : SwipeRefreshLayout? = null
    protected var mIsRefreshing = false
    protected var mList = mutableListOf<K>()

    override fun finishCreateView(state: Bundle?) {
        mRefresh = mRootView?.findViewById(R.id.refresh)
        mRecycler = mRootView?.findViewById(R.id.recycler)
        mIsPrepared = true
        lazyLoad()
    }

    override fun lazyLoad() {
        if(!mIsPrepared || !mIsVisible) return@lazyLoad
        initRefreshLayout()
        initRecyclerView()
        mRefresh ?: lazyLoadData()
        mIsPrepared = false
    }

    override fun complete() {
        super.complete()
        AppUtils.runOnUIDelayed({ mRefresh?.let { it.isRefreshing = false } }, 650)
        if (mIsRefreshing) {
            mList.clear()
            clear()
            getApplicationContext()?.toast("刷新成功")
        }
        mIsRefreshing = false
    }
    protected open fun clear(){

    }

    override fun initRefreshLayout() {
        mRefresh?.let {
            it.setColorSchemeResources(R.color.colorPrimary)
            mRecycler?.post {
                it.isRefreshing = true
                lazyLoadData()
            }
            it.setOnRefreshListener(this)
        }
    }

    override fun onRefresh() {
        clearData()
        lazyLoadData()
    }

    override fun clearData() {
        mIsRefreshing = true
    }

    override fun initWidget() {

    }
}