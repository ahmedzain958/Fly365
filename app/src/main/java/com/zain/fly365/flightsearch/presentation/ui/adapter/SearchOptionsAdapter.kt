package com.zain.fly365.flightsearch.presentation.ui.adapter

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.zain.fly365.R
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import com.zain.fly365.flightsearch.data.CabinClassEnum


class SearchOptionsAdapter(val cabinClassEnums: Array<CabinClassEnum>, val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<SearchOptionsAdapter.ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cabin_type_item, parent, false))
    }

    override fun getItemCount(): Int = cabinClassEnums.size

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewCabinClass.text = cabinClassEnums.get(holder.adapterPosition).toString()
        if (cabinClassEnums.get(holder.adapterPosition).isSelected) {
            holder.textViewCabinClass.background = context.getDrawable(R.drawable.textview_border)
        } else {
            holder.textViewCabinClass.background = null

        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View, adapterPosition: Int)
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var textViewCabinClass: TextView

        init {
            textViewCabinClass = itemView.findViewById(R.id.textViewCabinClass)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            itemClickListener.onItemClick(view, adapterPosition)
        }
    }

}