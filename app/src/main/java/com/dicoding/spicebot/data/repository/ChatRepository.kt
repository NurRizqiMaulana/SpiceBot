package com.dicoding.spicebot.data.repository

//class ChatRepository {
//    private val _users = MutableLiveData<ChatResponse?>()
//
//    private val apiClient = ApiConfig.getApiService()
//
//
//    fun createChatCompletion(message: String) {
//        try {
//            apiClient.chatWithTheBit(message).enqueue(object : Callback<ChatResponse> {
//                override fun onResponse(
//                    call: Call<ChatResponse>,
//                    response: Response<ChatResponse>
//                ) {
//                    if (response.isSuccessful){
//                        val responseBody = response.body()
//
//                        if (responseBody != null)
//                            _users.value=responseBody
//                    }else{
//                        Log.e("user", "onFailure: ${response.message()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
//                    t.printStackTrace()
//                }
//
//            })
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//}
//}