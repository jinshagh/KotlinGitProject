package com.btlm.kotlingitprojec.adapter.discover.section

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.btlm.kotlingitprojec.adapter.discover.GameCenterBookGiftAdapter
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.discover.GameCenter
import com.btlm.kotlingitproject.widget.section.StatelessSection
import com.btlm.kotlingitproject.widget.section.ViewHolder


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/30 21:44
 * * 描述:新游预约
 */
class GameCenterBookGiftSection(private val list: List<GameCenter.BookGiftBean>) : StatelessSection<Nothing>(R.layout.layout_item_game_center_head, R.layout.layout_item_game_center_book_gift, R.layout.layout_empty) {

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        holder.setText(R.id.tv_title, "新游预约")
    }

    override fun onBindFooterViewHolder(holder: ViewHolder) {
        val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = GameCenterBookGiftAdapter(list)

    }

}
