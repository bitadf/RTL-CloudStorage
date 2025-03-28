package com.example.pcstorage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcstorage.R
import com.example.pcstorage.data.HomeGridItem

class HomeGridAdapter(
    private val context: Context ,
    private  val cardList : List<HomeGridItem> ,
    private val onItemClick : ((position : Int , item : HomeGridItem) -> Unit)? = null):
RecyclerView.Adapter<HomeGridAdapter.ViewHolder>(){

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val cardView : CardView = view.findViewById(R.id.home_card_view)
        val icon : ImageView = view.findViewById(R.id.home_icon)
        val title : TextView = view.findViewById(R.id.home_title)
        val storage: TextView = view.findViewById(R.id.home_storage)
        val dotIcon : ImageView = view.findViewById(R.id.home_icon_2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_grid_card,parent,false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cardList[position]
        holder.cardView.setCardBackgroundColor(card.bgColor)
        holder.icon.setColorFilter(card.color)
        holder.dotIcon.setColorFilter(card.color)
        holder.title.text = card.title
        holder.title.setTextColor(card.color)
        holder.storage.text = card.filledStorage
        holder.storage.setTextColor(card.color)

        holder.cardView.setOnClickListener{
            onItemClick?.invoke(position , card)
        }

    }
}