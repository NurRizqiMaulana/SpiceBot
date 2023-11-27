package com.dicoding.spicebot.data.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.spicebot.AdapterChatbot
import com.dicoding.spicebot.data.model.ChatModel
import com.dicoding.spicebot.data.network.response.ChatResponse
import com.dicoding.spicebot.data.network.retrofit.ApiConfig
import com.dicoding.spicebot.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterChatBot: AdapterChatbot

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvChatList.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvChatList.addItemDecoration(itemDecoration)

//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://192.168.80.239:5000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()

//        val apiService = retrofit.create(ApiService::class.java)

        binding.btnSend.setOnClickListener { view ->
            postChat(binding.etChat.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        adapterChatBot = AdapterChatbot()

        binding.rvChatList.layoutManager = LinearLayoutManager(this)
        binding.rvChatList.adapter = adapterChatBot
    }

    private fun setChatData(spiceBotReply: ChatModel) {
        val adapter = AdapterChatbot()
        adapter.submitList(spiceBotReply)
        binding.rvChatList.adapter = adapter
        binding.etChat.setText("")
    }

    private fun postChat(chat: String) {
        val client = ApiConfig.getApiService().chatWithTheBit(chat)
        client.enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    setChatData(ChatModel(responseBody.SpiceBotReply, true))
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}