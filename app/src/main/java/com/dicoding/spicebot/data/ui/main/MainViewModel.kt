package com.dicoding.spicebot.data.ui.main

//class MainViewModel : ViewModel() {
//    private val _users = MutableLiveData<ChatResponse?>()
//    val users: MutableLiveData<ChatResponse?> = _users
//
//    companion object {
//        private const val TAG = "MainViewModel"
//    }
//
//    init {
////        getUsers()
//    }
//
//    private fun getUsers(chatBot: String) {
//
//        val client = ApiConfig.getApiService().chatWithTheBit(chatBot)
//        client.enqueue(object : Callback<ChatResponse> {
//            override fun onResponse(
//                call: Call<ChatResponse>,
//                response: Response<ChatResponse>
//            ) {
//                if (response.isSuccessful){
//                    val responseBody = response.body()
//                    if (responseBody != null)
//                        _users.value=responseBody
//                }else{
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }
//}