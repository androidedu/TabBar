package com.example.tabbar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class BarItem: LinearLayout {
    var selectCallback:((Int)->Unit)? = null
    var index = -1;
    var normalIconId:Int = 0 //正常图片
    var selectIconId:Int = 0 //选中图片
    var name:String = "null" //标题
        set(value) {
            field = value
            titleTextView?.text = value
        }
    var highlightTextColor:Int = 0 //选中的字体颜色
    var mIsSelected = false //记录是否选中
        set(value) {
            field = value
            updateUI()
        }

    private var iconImageView:ImageView? = null
    private var titleTextView:TextView? = null

    constructor(context: Context):super(context){
        initView()
    }

    constructor(context: Context,attrs:AttributeSet?):super(context,attrs){
        parseAttr(attrs)
        initView()
    }

    private fun parseAttr(attrs: AttributeSet?) {
        //将BarItem定义的属性从attrs里面解析出来
        val typedArray = context.obtainStyledAttributes(attrs,
            R.styleable.BarItem)

        //解析每一个属性
        normalIconId = typedArray.getResourceId(R.styleable.BarItem_normalIcon, R.drawable.home)
        selectIconId = typedArray.getResourceId(R.styleable.BarItem_selectIcon, R.drawable.home_selected)
        name = typedArray.getString(R.styleable.BarItem_title).toString()
        highlightTextColor = typedArray.getColor(R.styleable.BarItem_highlightTextColor, Color.RED)
        mIsSelected = typedArray.getBoolean(R.styleable.BarItem_selected,false)
        //回收
        typedArray.recycle()
    }

    //添加子视图
    private fun initView(){
        //配置布局方向
        orientation = VERTICAL
        gravity = Gravity.CENTER
        //添加图片
        iconImageView = ImageView(context).apply {
            //布局参数（尺寸）
            val lp = LinearLayout.LayoutParams(dp2px(32),dp2px(32))
            //设置图片
            //setImageResource(normalIconId)
            //将这个控件添加到容器中
            addView(this,lp)
        }

        //添加文字
        titleTextView = TextView(context).apply {
            val lp = LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            addView(this,lp)
        }
        //更新UI
        updateUI()
    }

    //将dp值 转化 为像素
    private fun dp2px(dp: Int) = (dp*context.resources.displayMetrics.density).toInt()

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN){
            if (!mIsSelected) {
                mIsSelected = true
                //回调被点击的事件
                selectCallback?.let {
                    it(index)
                }
            }
        }
        return true
    }
    //更新UI
    private fun updateUI() {
        if (mIsSelected){
            //选中状态
            iconImageView?.setImageResource(selectIconId)
            titleTextView?.setTextColor(highlightTextColor)
        }else{
            //正常状态
            iconImageView?.setImageResource(normalIconId)
            titleTextView?.setTextColor(Color.BLACK)
        }
    }
}















