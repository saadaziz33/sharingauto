package com.example.sharingauto.Retro;


import io.reactivex.*;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface myservice {

    @POST("register")
    @FormUrlEncoded
    Observable<String> registeruser(
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password);

    @POST("signin")
    @FormUrlEncoded
    Observable<String> signinuser(
            @Field("email") String email,
            @Field("password") String password);
}
