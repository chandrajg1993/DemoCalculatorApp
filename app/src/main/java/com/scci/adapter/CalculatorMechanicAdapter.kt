package com.scci.adapter

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.scci.democalculator.databinding.CalculatorListItemBinding
import com.scci.model.CalculatorListData
import com.scci.utlity.Utill
import hearsilent.discreteslider.DiscreteSlider
import java.text.DecimalFormat
import javax.inject.Inject


class CalculatorMechanicAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var userArrayList: MutableList<CalculatorListData>
    lateinit var context: Context
    var totalAddAmount: Int = 0
    var arraylist: MutableList<CalculatorListData>
    var isChange = false

    class HomeViewHolder(itemBinding: CalculatorListItemBinding) : RecyclerView.ViewHolder(itemBinding.getRoot()) {
        val binding = itemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return HomeViewHolder(CalculatorListItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,position: Int) {

        val viewHolder = holder as HomeViewHolder

        viewHolder.binding.tvTitle.text = userArrayList[position].itemNo
        if (userArrayList[position].enterValue != 0) {
            viewHolder.binding.etEnterValue.setText("${userArrayList[position].enterValue}")
        }

        if (userArrayList[position].enterValue != 0) {
            var totalAmount = userArrayList[position].margin * userArrayList[position].enterValue
            viewHolder.binding.tvTotalAmount.setText("₹${Utill.change(totalAmount,2)}")
         }
        viewHolder.binding.tvTotalAddAmount.text = "${totalAddAmount}"

        /*  if (position.equals(userArrayList.size - 1)) {
              viewHolder.binding.llTotalCount.visibility = View.VISIBLE
          }*/
        viewHolder.binding.discreteSlider.setOnValueChangedListener(object : DiscreteSlider.OnValueChangedListener() {
            var progressChangedValue = 0

            override fun onValueChanged(progress: Int,fromUser: Boolean) {
                super.onValueChanged(progress,fromUser)

                progressChangedValue = progress
                if (fromUser) {
                    userArrayList[position].oldValue = userArrayList[position].enterValue

                }
                try {
                    if (!isChange) {
                        var value = progressChangedValue.toString().toInt()

                        userArrayList[position].oldValue = userArrayList[position].enterValue
                        userArrayList[position].enterValue = value
                        if (value == 0) {
                            viewHolder.binding.etEnterValue.setText("")

                        } else {
                            viewHolder.binding.etEnterValue.setText(progressChangedValue.toString())
                            viewHolder.binding.etEnterValue.setSelection(viewHolder.binding.etEnterValue.length())

                        }
                    } else {
                        isChange = false
                    }
                    var valueData = 0.0
                    if (userArrayList[position].enterValue <= 24 && userArrayList[position].itemNo.equals("Engine Oil")) {
                        valueData += (userArrayList[position].enterValue * userArrayList[position].ltr) * (userArrayList[position].margin)
                        changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,24,position)
                    } else if (userArrayList[position].enterValue <= 49 && userArrayList[position].itemNo.equals("Engine Oil")) {
                        valueData += (userArrayList[position].enterValue * userArrayList[position].ltr) * (userArrayList[position].margin + 7.65)

                        changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,49,position)

                    } else if (userArrayList[position].enterValue <= 74 && userArrayList[position].itemNo.equals("Engine Oil")) {
                        valueData += (userArrayList[position].enterValue * userArrayList[position].ltr) * (userArrayList[position].margin + 17.85)

                        changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,74,position)
                    } else if (userArrayList[position].itemNo.equals("Engine Oil")) {
                        valueData += (userArrayList[position].enterValue * userArrayList[position].ltr) * (userArrayList[position].margin + 28.05)

                        changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,100,position)
                    } else if (userArrayList[position].enterValue <= 24 && userArrayList[position].itemNo.equals("Coolant")) {
                        valueData += (userArrayList[position].enterValue * userArrayList[position].ltr) * (userArrayList[position].margin)
                        changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,24,position)

                    } else if (userArrayList[position].enterValue <= 49 && userArrayList[position].itemNo.equals("Coolant")) {
                        valueData += (userArrayList[position].enterValue * userArrayList[position].ltr) * (userArrayList[position].margin + 3.30)
                        changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,49,position)

                    } else if (userArrayList[position].enterValue <= 74 && userArrayList[position].itemNo.equals("Coolant")) {
                        valueData += (userArrayList[position].enterValue * userArrayList[position].ltr) * (userArrayList[position].margin + 7.70)
                        changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,74,position)

                    } else if (userArrayList[position].itemNo.equals("Coolant")) {
                        valueData += (userArrayList[position].enterValue * userArrayList[position].ltr) * (userArrayList[position].margin + 12.10)
                        changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,100,position)

                    } else {
                        valueData += (userArrayList[position].enterValue * userArrayList[position].ltr) * userArrayList[position].margin
                        if (userArrayList[position].enterValue <= 24) {
                            changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,24,position)

                        } else if (userArrayList[position].enterValue <= 49) {
                            changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,49,position)

                        } else if (userArrayList[position].enterValue <= 74) {
                            changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,74,position)

                        } else {
                            changeColorText(viewHolder.binding.tvTotalAmount,viewHolder.binding.etEnterValue,100,position)

                        }
                    }
/*
                    val df = DecimalFormat("#.##")
                    val roundoff = df.format(valueData)*/
                    userArrayList[position].totalAmount =valueData

                    viewHolder.binding.tvTotalAmount.setText("₹${Utill.change(valueData,2)}")
                     OnClickListener?.let {
                        it(userArrayList)
                    }

                } catch (ex: Exception) {
                    ex.printStackTrace()
                }

            }

            override fun onValueChanged(minProgress: Int,maxProgress: Int,fromUser: Boolean) {
                super.onValueChanged(minProgress,maxProgress,fromUser)

                Log.d("DiscreteSlider","MinProgress: " + minProgress + ", MaxProgress: " + maxProgress + ", fromUser: " + fromUser)
            }
        })


        viewHolder.binding.etEnterValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence,start: Int,count: Int,after: Int) {}
            override fun onTextChanged(s: CharSequence,start: Int,before: Int,count: Int) {}
            override fun afterTextChanged(s: Editable) {
                try {
                    if (!s.toString().equals("")) {
                        var value = s.toString().toInt() //userArrayList[position].enterValue = value
                        var totalAmount = userArrayList[position].margin * userArrayList[position].enterValue

                        viewHolder.binding.tvTotalAmount.setText("₹${Utill.change(totalAmount,2)}")
                        OnClickListener?.let {
                            it(userArrayList)
                        } //  viewHolder.binding.simpleSeekBar.setMax(value)
                        if (!isChange) {
                            if (value < 100) {
                                viewHolder.binding.discreteSlider.setProgress(value)
                            } else {
                                viewHolder.binding.discreteSlider.setProgress(100)
                                var totalAmount = userArrayList[position].margin * userArrayList[position].enterValue

                                viewHolder.binding.tvTotalAmount.setText("${Utill.change(totalAmount,2)}")
                                OnClickListener?.let {
                                    it(userArrayList)
                                }
                            }
                        } else {
                            isChange = true

                        }

                    } else {
                        userArrayList[position].enterValue = 0
                        userArrayList[position].oldValue = 0

                        userArrayList[position].totalAmount = 0.0
                        viewHolder.binding.discreteSlider.setProgress(0)
                    }
                } catch (ex: Exception) {
                    userArrayList[position].enterValue = 0
                    userArrayList[position].oldValue = 0
                    userArrayList[position].totalAmount = 0.0
                    viewHolder.binding.discreteSlider.setProgress(0)

                    ex.printStackTrace()
                }

            }

        }) // perform seek bar change listener event used for getting the progress value

    }


    private fun changeColorText(tvTotalAmount: EditText,etEnterValue: EditText,value: Int,position: Int) {
        if (value == 24) {
            tvTotalAmount.setTextColor(Color.parseColor("#ffffff"))
            etEnterValue.setTextColor(Color.parseColor("#ffffff"))

            OnShowGifeClickListener?.let {
                it(24,userArrayList,position)

            }
        } else if (value == 49) {
            tvTotalAmount.setTextColor(Color.parseColor("#848482"))
            etEnterValue.setTextColor(Color.parseColor("#848482"))
            OnShowGifeClickListener?.let {
                it(49,userArrayList,position)

            }
        } else if (value == 74) {
            tvTotalAmount.setTextColor(Color.parseColor("#FFD700"))
            etEnterValue.setTextColor(Color.parseColor("#FFD700"))
            OnShowGifeClickListener?.let {
                it(74,userArrayList,position)

            }
        } else if (value == 100) {
            tvTotalAmount.setTextColor(Color.parseColor("#FFFF00"))
            etEnterValue.setTextColor(Color.parseColor("#FFFF00"))
            OnShowGifeClickListener?.let {
                it(100,userArrayList,position)
            }
        }

    }


    private var OnShowGifeClickListener: ((value: Int,userArrayList: MutableList<CalculatorListData>,position: Int) -> Unit)? = null

    fun setOnShowGiftClickListener(listener: (value: Int,userArrayList: MutableList<CalculatorListData>,position: Int) -> Unit) {
        OnShowGifeClickListener = listener
    }

    private var OnClickListener: ((userArrayList: MutableList<CalculatorListData>) -> Unit)? = null

    fun setOnClickListener(listener: (userArrayList: MutableList<CalculatorListData>) -> Unit) {
        OnClickListener = listener
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    fun updateUserList(userArrayList: MutableList<CalculatorListData>?) { //  this.userArrayList.clear();
        if (userArrayList != null) {
            this.userArrayList = userArrayList
            arraylist!!.addAll(userArrayList)

        }
        notifyDataSetChanged()
    }


    init {
        userArrayList = ArrayList()
        arraylist = ArrayList()
    }

}