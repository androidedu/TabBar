package com.example.tabbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //配置显示的属性
        mTabBar.models = arrayOf(
            ItemModel(
                R.drawable.home,
                R.drawable.home_selected,
                "主页",
                Color.RED,
                true
            ),

            ItemModel(
                R.drawable.video,
                R.drawable.video_selected,
                "视频",
                Color.RED,
                false
            ),
            ItemModel(
                R.drawable.circle,
                R.drawable.circle_selected,
                "圈子",
                selected = false
            ),
            ItemModel(
                R.drawable.me,
                R.drawable.me_selected,
                "我",
                Color.RED,
                false
            )
        )
    }
}