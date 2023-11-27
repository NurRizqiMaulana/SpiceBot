package com.dicoding.spicebot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.spicebot.databinding.ListitemChatBinding

class AdapterChatbot : RecyclerView.Adapter<AdapterChatbot.MyViewHolder>() {
    private val list = ArrayList<String>()

    inner class MyViewHolder(binding: ListitemChatBinding) : RecyclerView.ViewHolder(binding.root) {
        var txtChat = binding.txtChat

        fun bind(chat: String) {
            txtChat.text = chat

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyViewHolder {
        val binding = ListitemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitList(newList: String) {
        list.add(newList)
        notifyDataSetChanged()
    }
}