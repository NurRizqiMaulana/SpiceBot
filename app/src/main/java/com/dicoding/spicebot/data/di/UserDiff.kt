package com.dicoding.spicebot.data.di

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.spicebot.data.model.ChatModel

class UserDiff (
    private val mOldUserList: List<ChatModel>,
    private val mNewUserList: List<ChatModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = mOldUserList.size

    override fun getNewListSize(): Int = mNewUserList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserList[oldItemPosition].chat == mNewUserList[newItemPosition].chat
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldUserList[oldItemPosition]
        val newEmployee = mOldUserList[newItemPosition]
        return oldEmployee.isBot == newEmployee.isBot
    }
}