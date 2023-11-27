package com.dicoding.spicebot.data.network.retrofit

import com.dicoding.spicebot.data.network.response.ChatResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("chat")
    fun chatWithTheBit(
        @Field("chatInput") chatText : String
    ): Call<ChatResponse>
}
