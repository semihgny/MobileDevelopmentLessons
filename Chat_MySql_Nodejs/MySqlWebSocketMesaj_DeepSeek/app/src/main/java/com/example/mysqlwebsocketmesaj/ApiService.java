package com.example.mysqlwebsocketmesaj;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
    @POST("messages")
    Call<Void> sendMessage(@Body Message message);

    @GET("messages")
    Call<List<Message>> getMessages();
}