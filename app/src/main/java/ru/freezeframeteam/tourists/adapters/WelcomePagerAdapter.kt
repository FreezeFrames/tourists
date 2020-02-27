package ru.freezeframeteam.technicalchat.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import ru.freezeframeteam.tourists.R

class WelcomePagerAdapter (var models: ArrayList<WelcomeModel>, var context: Context) : PagerAdapter() {

    lateinit var layIn : LayoutInflater
//    lateinit var tv: TextView
    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0.equals(p1)
    }
    override fun getCount(): Int {
        return if(models.size>5){5}else{models.size}
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layIn = LayoutInflater.from(context)

        val view : View = layIn.inflate(R.layout.item_welcome, container, false)
        val tv: TextView
        tv = view.findViewById(R.id.tv)
        val imageView : ImageView
        imageView = view.findViewById(R.id.imageView)
        Glide.with(context).load(models[position].image).into(imageView)
        container.addView(view, 0)
        tv.text = models[position].text
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as RelativeLayout)
    }


}