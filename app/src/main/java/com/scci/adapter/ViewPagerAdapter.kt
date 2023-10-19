package com.scci.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.scci.democalculator.R
import com.scci.model.SliderData

class ViewPagerAdapter(private val context: Context) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null

    //  private val images = arrayOf(R.drawable.user, R.drawable.user, R.drawable.user)
    private var mSliderItems: MutableList<SliderData> = ArrayList()


    override fun getCount(): Int {
        return mSliderItems.size
    }

    fun viewPagerItem(sliderItems: MutableList<SliderData>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View,viewFrom: Any): Boolean {
        return view === viewFrom
    }

    override fun instantiateItem(container: ViewGroup,position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.custom_layout,null)
        val imageView = view.findViewById<View>(R.id.imageView) as ImageView // imageView.setImageResource(mSliderItems[position].Image)
        Glide.with(context).load(mSliderItems[position].Image).fitCenter().into(imageView)
        val vp = container as ViewPager
        vp.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup,position: Int,destroyItem: Any) {
        val vp = container as ViewPager
        val view = destroyItem as View
        vp.removeView(view)
    }
}