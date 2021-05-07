package com.example.greenmileteste.model;

import com.example.greenmileteste.entity.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder {

    @GET("posts")
    //@GET("get_resources_since")
    Call<List<Post>> getPost();

}
