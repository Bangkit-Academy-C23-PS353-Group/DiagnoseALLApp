package com.example.diagnosaallapps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class adapterUser(val listUser: Array<Array<String>>) : RecyclerView.Adapter<adapterUser.ListViewHolder>() {
//    private lateinit var onItemClickCallback: OnItemClickCallback
    class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val patientname: TextView = itemView.findViewById(R.id.tv_patientname)
        val date: TextView = itemView.findViewById(R.id.tv_date)
        val result: TextView = itemView.findViewById(R.id.btn_result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.listcardpasien,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (listUser!=null){
            return listUser.size
        }
        else{
            return 0
        }

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.patientname.text = listUser[position][0]
        holder.result.text=listUser[position][1]
        holder.date.text=listUser[position][2].toString()

//        holder.username.text = listUser?.get(position)?.login
//        Glide.with(holder.avatar)
//            .load(listUser?.get(position)?.avatarUrl)
//            .error(R.drawable.ic_launcher_background)
//            .into(holder.avatar)

//        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listUser?.get(holder.adapterPosition))}
    }

//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }
//
//    interface OnItemClickCallback {
//        fun onItemClicked(data: ItemsItem?)
//    }
}