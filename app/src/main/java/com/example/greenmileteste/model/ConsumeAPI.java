package com.example.greenmileteste.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsumeAPI {

    public static final String API_URL= "https://jsonplaceholder.typicode.com/";

    public static <S> S consumeAPI(Class<S> serviceClass){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                //.baseUrl("http://portal.greenmilesoftware.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return (S) retrofit.create(JSONPlaceholder.class);
    }




}
