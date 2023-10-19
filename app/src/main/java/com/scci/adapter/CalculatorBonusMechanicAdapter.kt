package com.scci.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scci.democalculator.databinding.CalculatorBonusListItemBinding
import com.scci.model.CalculatorBonusListData
import com.scci.utlity.Utill
import javax.inject.Inject


class CalculatorBonusMechanicAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var userArrayList: MutableList<CalculatorBonusListData>
    lateinit var context: Context
    var possionClick: Int = 0
    var arraylist: MutableList<CalculatorBonusListData>

    class HomeViewHolder(itemBinding: CalculatorBonusListItemBinding) : RecyclerView.ViewHolder(itemBinding.getRoot()) {
        val binding = itemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return HomeViewHolder(CalculatorBonusListItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,position: Int) {

        val viewHolder = holder as HomeViewHolder

        viewHolder.binding.tvNoService.text = userArrayList[position].noOfService
        viewHolder.binding.tvEngineOil.text = "₹${Utill.change(userArrayList[position].EngineOil,2)}"
        viewHolder.binding.tvCoolant.text = "₹${Utill.change(userArrayList[position].Coolant,2)}"
        if (position == 0) {
            viewHolder.binding.tvNoService.setTextColor(Color.parseColor("#ffffff"))
            viewHolder.binding.tvEngineOil.setTextColor(Color.parseColor("#ffffff"))
            viewHolder.binding.tvCoolant.setTextColor(Color.parseColor("#ffffff"))

        } else if (position == 1) {
            viewHolder.binding.tvNoService.setTextColor(Color.parseColor("#848482"))
            viewHolder.binding.tvEngineOil.setTextColor(Color.parseColor("#848482"))
            viewHolder.binding.tvCoolant.setTextColor(Color.parseColor("#848482"))

        } else if (position == 2) {
            viewHolder.binding.tvNoService.setTextColor(Color.parseColor("#FFD700"))
            viewHolder.binding.tvEngineOil.setTextColor(Color.parseColor("#FFD700"))
            viewHolder.binding.tvCoolant.setTextColor(Color.parseColor("#FFD700"))

        } else {
            viewHolder.binding.tvNoService.setTextColor(Color.parseColor("#FFFF00"))
            viewHolder.binding.tvEngineOil.setTextColor(Color.parseColor("#FFFF00"))
            viewHolder.binding.tvCoolant.setTextColor(Color.parseColor("#FFFF00"))

        }/*  if (possionClick == position) {
                viewHolder.binding.tvBonusZero.setBackgroundResource(R.drawable.btn_round2)
                viewHolder.binding.tvBonusZero.setTextColor(Color.parseColor("#000000"))
            } else {
                viewHolder.binding.tvBonusZero.setBackgroundResource(R.drawable.btn_round)
                viewHolder.binding.tvBonusZero.setTextColor(Color.parseColor("#111111"))
            }
            viewHolder.binding.tvBonusZero.setOnClickListener({
                possionClick = position
                notifyDataSetChanged()
                OnClickListener?.let {
                    it(userArrayList[position].bonusPercentage)
                }
            })*/

    }


    private var OnClickListener: ((userArrayList: Int) -> Unit)? = null

    fun setOnClickListener(listener: (userArrayList: Int) -> Unit) {
        OnClickListener = listener
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    fun updateUserList(userArrayList: MutableList<CalculatorBonusListData>?) { //  this.userArrayList.clear();
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