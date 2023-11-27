package com.dicoding.spicebot

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.spicebot.data.model.ChatModel
import com.dicoding.spicebot.databinding.ListitemChatBinding

class AdapterChatbot : RecyclerView.Adapter<AdapterChatbot.MyViewHolder>() {
    private val list = ArrayList<ChatModel>()

    inner class MyViewHolder(binding: ListitemChatBinding) : RecyclerView.ViewHolder(binding.root) {
        var txtChat = binding.txtChat

        fun bind(chat: ChatModel) = with(itemView) {
            if(!chat.isBot) {
                txtChat.setBackgroundColor(Color.WHITE)
                txtChat.setTextColor(Color.BLACK)
                txtChat.text = chat.chat
            }else{
                txtChat.setBackgroundColor(Color.CYAN)
                txtChat.setTextColor(Color.BLACK)
                txtChat.text = chat.chat
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyViewHolder {
        val binding = ListitemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

//    override fun getItemCount() : Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val chat = list[position]
        holder.bind(chat)
    }

    fun submitList(newList: ChatModel) {
        list.clear()
        list.add(newList)
        notifyDataSetChanged()
    }


//    @SuppressLint("NotifyDataSetChanged")
//    fun addChatToList(chat: String) {
//        list.add(chat)
//        notifyDataSetChanged()
//    }

//    companion object {
//        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ChatResponse>() {
//            override fun areItemsTheSame(oldItem: ChatResponse, newItem: ChatResponse): Boolean {
//                return oldItem == newItem
//            }
//
//            override fun areContentsTheSame(oldItem: ChatResponse, newItem: ChatResponse): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
}