package com.aad.admin_login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceApi {


    @FormUrlEncoded
    @POST("login.php")
    Call<User> add_user(@Field("admin")String login,
                        @Field("password")String password);



}
