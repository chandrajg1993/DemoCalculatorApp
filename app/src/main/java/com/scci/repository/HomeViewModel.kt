package com.scci.repository

import androidx.lifecycle.ViewModel
import com.scci.model.CalculatorBonusListData
import com.scci.model.CalculatorListData
import com.scci.model.HomeData
import com.scci.model.SliderData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    var userArrayList: ArrayList<HomeData>? = null
    var calculatorListData: MutableList<CalculatorListData>? = null
    var calculatorBonusListData: MutableList<CalculatorBonusListData>? = null
    var sliderImageList: MutableList<SliderData>? = null


    fun MainViewModel() {
        userDataList()
        calculatorListData()
        sliderImageData()
        calculatorBonusListData()

    }


    fun calculatorBonusListData() {

        calculatorBonusListData = ArrayList()/*   calculatorBonusListData!!.add(CalculatorBonusListData("<7\n Bonus 0", 0))
        calculatorBonusListData!!.add(CalculatorBonusListData(">7\n Bonus 1%", 1))
        calculatorBonusListData!!.add(CalculatorBonusListData(">14\n Bonus 2%", 2))
        calculatorBonusListData!!.add(CalculatorBonusListData(">21\n Bonus 3%", 3))*/ //  calculatorBonusListData!!.add(CalculatorBonusListData("Less than ₹3000",0))

        calculatorBonusListData!!.add(CalculatorBonusListData("01-24",61.20,26.40))
        calculatorBonusListData!!.add(CalculatorBonusListData("24-49",68.85,29.70))
        calculatorBonusListData!!.add(CalculatorBonusListData("50-74",79.05,34.10))
        calculatorBonusListData!!.add(CalculatorBonusListData("75-100",89.25,38.50))

    }

    fun calculatorListData() {

        calculatorListData = ArrayList()
        calculatorListData!!.add(CalculatorListData("Engine Oil",61.20,0,0,0.0,3.5))
        calculatorListData!!.add(CalculatorListData("Coolant",26.40,0,0,0.0,4.0))
        calculatorListData!!.add(CalculatorListData("Brake Fluid",40.0,0,0,0.0,0.5))
        calculatorListData!!.add(CalculatorListData("Transmission Oil",50.0,0,0,0.0,3.0))

    }

    fun userDataList() {
        var imageData = "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png"

        userArrayList = ArrayList()
        userArrayList!!.add(HomeData("Lable1",imageData))
        userArrayList!!.add(HomeData("Lable2",imageData))
        userArrayList!!.add(HomeData("Lable3",imageData))
        userArrayList!!.add(HomeData("Lable4",imageData))
        userArrayList!!.add(HomeData("Lable5",imageData))
        userArrayList!!.add(HomeData("Lable6",imageData))
    }

    fun sliderImageData() {
        val sliderData = SliderData("https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png")

        sliderImageList = ArrayList()
        sliderImageList!!.add(sliderData)
        sliderImageList!!.add(sliderData)
        sliderImageList!!.add(sliderData)
        sliderImageList!!.add(sliderData)
        sliderImageList!!.add(sliderData)
        sliderImageList!!.add(sliderData)
    }
}