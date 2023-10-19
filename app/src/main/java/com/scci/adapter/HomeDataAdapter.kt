package com.scci.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.scci.democalculator.databinding.HomeItemBinding
import com.scci.model.HomeData
import java.util.*
import javax.inject.Inject

class HomeDataAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var userArrayList: ArrayList<HomeData>
    lateinit var context: Context
    var arraylist: ArrayList<HomeData>

    class HomeViewHolder(itemBinding: HomeItemBinding) : RecyclerView.ViewHolder(itemBinding.getRoot()) {
        val binding = itemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return HomeViewHolder(HomeItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,position: Int) {

        val viewHolder = holder as HomeViewHolder

        viewHolder.binding.tvTitle.text = userArrayList[position].title
        Glide.with(context).load(userArrayList[position].image).fitCenter().circleCrop().into(viewHolder.binding.image)
    }


    private var OnSelectListener: ((HomeData,Int) -> Unit)? = null

    fun setOnMorePage(listener: (HomeData,Int) -> Unit) {

        OnSelectListener = listener
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    fun updateUserList(userArrayList: ArrayList<HomeData>?) { //  this.userArrayList.clear();
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

    fun filter(charText: String) {
        var charText = charText
        try {
            charText = charText.lowercase(Locale.getDefault())
            userArrayList.clear()
            if (charText.length == 0) {
                arraylist.let { userArrayList.addAll(it) }
            } else {
                for (wp in arraylist) {
                    try {
                        if (wp.title.toLowerCase(Locale.getDefault()).contains(charText)) {
                            userArrayList.add(wp)
                        }
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
            }

            notifyDataSetChanged()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}