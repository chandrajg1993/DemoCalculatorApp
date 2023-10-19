package com.scci.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.scci.adapter.HomeDataAdapter
import com.scci.adapter.ViewPagerAdapter

import com.scci.democalculator.R
import com.scci.democalculator.databinding.ActivityMainBinding
import com.scci.repository.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var homeDataAdapter: HomeDataAdapter
    lateinit var viewPagerAdapter: ViewPagerAdapter
    private var dotscount = 0
    var dots: Array<ImageView?> = arrayOfNulls(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeViewModel.MainViewModel()

        viewPagerAdapter = ViewPagerAdapter(this)

        binding.viewpager.setAdapter(viewPagerAdapter)
        viewPagerAdapter.viewPagerItem(homeViewModel.sliderImageList!!)


        binding.rvListData.apply {
            adapter = homeDataAdapter
            layoutManager = LinearLayoutManager(context)
            homeDataAdapter.updateUserList(homeViewModel.userArrayList)

        }


        binding.etSearchHistory.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(arg0: Editable) { // TODO Auto-generated method stub
                //  if (apiResponse.data.size() != 0) {
                try {

                    val text = binding.etSearchHistory.getText().toString().toLowerCase(Locale.getDefault())
                    homeDataAdapter.filter(text)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            override fun beforeTextChanged(arg0: CharSequence,arg1: Int,arg2: Int,arg3: Int) { // TODO Auto-generated method stub
            }

            override fun onTextChanged(arg0: CharSequence,arg1: Int,arg2: Int,arg3: Int) { // TODO Auto-generated method stub
            }
        })







        dotscount = viewPagerAdapter.count
        dots = arrayOfNulls(dotscount)
        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.nonactive_dot))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8,0,8,0)
            binding.SliderDots?.addView(dots[i],params)
        }
        dots[0]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.active_dot))

        binding.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                Toast.makeText(this@MainActivity,"notifyDataSetChanged",Toast.LENGTH_SHORT).show()
                homeDataAdapter.notifyDataSetChanged()
            }

            override fun onPageScrolled(position: Int,positionOffset: Float,positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.nonactive_dot))
                }
                dots[position]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.active_dot))

            }

        })

    }

}