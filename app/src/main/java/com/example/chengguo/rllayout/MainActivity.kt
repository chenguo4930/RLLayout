package com.example.chengguo.rllayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var loadMoreWrapperAdapter: LoadMoreWrapperAdapter
    lateinit var loadMoreWrapper: LoadMoreWrapper

    var dataList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        init()
    }

    private fun getData() {
        var letter = 'A'
        for (i in 0..26) {
            dataList.add(letter.toString())
            letter++
        }
    }

    fun init() {

        loadMoreWrapperAdapter = LoadMoreWrapperAdapter(dataList)

        loadMoreWrapper = LoadMoreWrapper(loadMoreWrapperAdapter)

        recycleView.run {
            layoutManager = GridLayoutManager(this@MainActivity,2)
            adapter = loadMoreWrapper
            addOnScrollListener(object : EndOnScrollListener() {
                override fun onLoadMore() {
                    loadMoreWrapper.setLoadState(LoadMoreWrapper.LOADING)
                    if (dataList.size < 55) {
                        //模拟获取网络数据,延迟1秒
                        Timer().schedule(object : TimerTask() {
                            override fun run() {
                                doAsync {
                                    getData()
                                    uiThread {
                                        loadMoreWrapper.setLoadState(LoadMoreWrapper.LOADING_COMPLETE)
                                    }
                                }
                            }
                        }, 1000)
                    } else {
                        //显示加载到底部的提示
                        loadMoreWrapper.setLoadState(LoadMoreWrapper.LOADING_END)
                    }
                }
            })
        }
    }
}
