package com.example.tabbar

import android.graphics.Color

/**
 * 数据类
 */
data class ItemModel(
    var normalIcon:Int,
    var selectIcon:Int,
    var title:String,
    var highlightColor:Int = Color.RED,
    var selected:Boolean
)