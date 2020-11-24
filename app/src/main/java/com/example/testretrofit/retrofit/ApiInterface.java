package com.example.testretrofit.retrofit;

import com.example.testretrofit.model.Comment;
import com.example.testretrofit.model.Todo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("posts")
    Call<List<Todo>> getTodo();

    @GET("posts")
    Observable<List<Todo>> getTodo_RxJava();

    @GET("comments")
    Observable<List<Comment>> getTodoById(@Query("postId") int postId);
}
