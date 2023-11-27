package com.dicoding.spicebot.data.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.spicebot.AdapterChatbot
import com.dicoding.spicebot.data.network.response.ChatResponse
import com.dicoding.spicebot.data.network.retrofit.ApiConfig
import com.dicoding.spicebot.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapterChatBot = AdapterChatbot()

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://10.5.9.129:5000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val apiService = retrofit.create(ApiService::class.java)


        binding.rvChatList.layoutManager = LinearLayoutManager(this)
        binding.rvChatList.adapter = adapterChatBot

//        binding.btnSend.setOnClickListener {
////            if (binding.etChat.text.isNullOrEmpty()) {
////                Toast.makeText(this@MainActivity, "Please enter a text", Toast.LENGTH_LONG).show()
////            }else {
//                adapterChatBot.submitList(binding.etChat.text.toString())
//                apiService.chatWithTheBit(binding.etChat.text.toString()).enqueue(callBack)
//                binding.etChat.text.clear()
//
//            }

        binding.btnSend.setOnClickListener { view ->
            postReview(binding.etChat.text.toString())
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun postReview(review: String) {
        val client = ApiConfig.getApiService().chatWithTheBit(review)
        client.enqueue(object : Callback<ChatResponse> {
            override fun onResponse(
                call: Call<ChatResponse>,
                response: Response<ChatResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    setReviewData(responseBody.spiceBotReply)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun setReviewData(chat: String) {
        val adapter = AdapterChatbot()
        adapter.submitList(chat)
        binding.rvChatList.adapter = adapter
        binding.etChat.text.clear()
    }

//    private val callBack = object : Callback<ChatResponse>{
//        override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
//            if(response.isSuccessful && response.body()!= null){
//                adapterChatBot.submitList(response.body()!!.spiceBotReply)
//            }else{
//                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_LONG).show()
//            }
//        }
//
//        override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
//            Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_LONG).show()
//        }
//
//    }
}