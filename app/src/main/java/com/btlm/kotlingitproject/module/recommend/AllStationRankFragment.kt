package com.btlm.kotlingitproject.module.recommend

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.recommend.AllStationRankAdapter
import com.btlm.kotlingitproject.base.BaseRefreshFragment
import com.btlm.kotlingitproject.bean.recommend.AllStationRank
import com.btlm.kotlingitproject.constant.Constants
import com.btlm.kotlingitproject.mvp.contract.recommend.AllStationRankContract
import com.btlm.kotlingitproject.mvp.presenter.recommend.AllStationRankPresenter



/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/30 18:35
 * * 描述:全站排行
 */
class AllStationRankFragment : BaseRefreshFragment<AllStationRankPresenter, AllStationRank.RankBean.ListBean>(), AllStationRankContract.View {

    private lateinit var mType: String

    private var mAdapter: AllStationRankAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_region_type


    override fun initVariables() {
        arguments?.let {
            mType = it.getString(Constants.EXTRA_TYPE)
        }
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {

        mPresenter.getAllStationRankData(mType)
    }

    override fun initRecyclerView() {
        mAdapter = AllStationRankAdapter(mList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mRecycler?.layoutManager = layoutManager
        mRecycler?.adapter = mAdapter
    }


    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }


    override fun showAllStationRank(regionRank: List<AllStationRank.RankBean.ListBean>) {
        mList.addAll(regionRank)
        finishTask()
    }

    companion object {

        fun newInstance(type: String): AllStationRankFragment {
            val bundle = Bundle()
            bundle.putString(Constants.EXTRA_TYPE, type)
            val fragment = AllStationRankFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
