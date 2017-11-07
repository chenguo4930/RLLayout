package com.example.chengguo.rllayout

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 *
 * @author Chengguo on 2017/11/7.
 */
abstract class EndOnScrollListener : RecyclerView.OnScrollListener() {

    //用来判断是否正在向上滑动
    private var isSlidingUpward = false

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val manager = recyclerView?.layoutManager as LinearLayoutManager
        //当不滑动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //获取最后一个完全显示的itemPosition
            val lastItemPosition = manager.findLastCompletelyVisibleItemPosition()
            val itemCount = manager.itemCount
            if (lastItemPosition == (itemCount - 1) && isSlidingUpward) {
                onLoadMore()
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        //大于0表示正在向上滑动，小于等于0表示停止或者向下滑动,向左滑动dx>0
        isSlidingUpward = dy > 0
    }

    /**
     * 加载更多回调
     */
    abstract fun onLoadMore()
}