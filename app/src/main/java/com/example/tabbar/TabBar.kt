package com.example.tabbar

import android.app.Person
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout

class TabBar:LinearLayout {
    //保存所有item的模型数据
    var models:Array<ItemModel> = emptyArray()
        set(value) {
            field = value
            updateUI()
        }
    private var number = 0 //记录有多少个栏目
    private val items = mutableListOf<BarItem>()
    private var current = 0 //记录当前选中的栏目的索引

    constructor(context: Context):super(context){
        initView()
    }
    constructor(context: Context, attrs: AttributeSet?):super(context,attrs){
        parseAttr(attrs)
        initView()
    }

    private fun parseAttr(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.TabBar)
        number = typedArray.getInteger(R.styleable.TabBar_number,4)
        typedArray.recycle()
    }

    private fun initView() {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        for (i in 0 until number){
            //创建BarItem
            BarItem(context).also {
                val lp = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    weight = 1f
                }
                addView(it,lp)
                items.add(it)

                //监听点击的事件
                it.selectCallback = { index->
                    //还原之前选中的栏目的状态
                    items[current].mIsSelected = false
                    //保存索引
                    current = index
                }
            }
        }
    }

    private fun updateUI(){
        for ((i,item) in items.withIndex()){
            val model = models[i]

            item.index = i
            item.normalIconId = model.normalIcon
            item.selectIconId = model.selectIcon
            item.name = model.title
            item.highlightTextColor = model.highlightColor
            item.mIsSelected = model.selected
        }
    }
}













