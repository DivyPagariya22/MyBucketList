package com.example.android.mybucketlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class BucketRV(private val context: Context, private val listner: ClickListner) : RecyclerView.Adapter<BucketRV.BucketViewHolder>(){

    val allGoals = ArrayList<Bucket>()


    inner class BucketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val textView = itemView.findViewById<TextView>(R.id.goal)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
        val done = itemView.findViewById<ImageView>(R.id.done)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BucketViewHolder {
       val viewHolder = BucketViewHolder(
           LayoutInflater.from(context).inflate(
               R.layout.listof_item,
               parent,
               false
           )
       )
        viewHolder.deleteButton.setOnClickListener {
            listner.onItemClicked(allGoals[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: BucketViewHolder, position: Int) {
        val current = allGoals[position]
        holder.textView.text = current.goal

        if(current.done == 1){
            holder.done.isVisible= true
            holder.textView.setBackgroundResource(R.drawable.done_corners)
        }
        holder.textView.setOnClickListener {
            listner.onDoneClicked(it,position,current,holder)
        }

    }

    override fun getItemCount(): Int {
        return allGoals.size
    }



    fun updateList(it: List<Bucket>) {
        allGoals.clear()
        allGoals.addAll(it)
        notifyDataSetChanged()
    }

    fun deleteList() {
        allGoals.clear()
        notifyDataSetChanged()
    }



    interface ClickListner{
        fun onItemClicked(bucket: Bucket)
        fun onDoneClicked(view: View, pos: Int,bucket: Bucket,holder: BucketViewHolder)
    }
}