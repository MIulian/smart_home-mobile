package com.example.myapplication.network;


import com.example.myapplication.data.JsonUser;
import com.example.myapplication.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApiService {

    //Save New User
    @POST("users/")
    Call<JsonUser> saveBoard(@Body User user );

}
