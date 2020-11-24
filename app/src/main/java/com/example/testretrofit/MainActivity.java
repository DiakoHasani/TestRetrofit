package com.example.testretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.testretrofit.model.Todo;
import com.example.testretrofit.retrofit.ApiClient;
import com.example.testretrofit.retrofit.ApiInterface;

import java.io.IOException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    Disposable dispose_getTodo_RxJava,dispose_getTodoById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
//        getTodo();
//        getTodo_RxJava();
        getTodoById();
    }

    /**
     * در اینجا یک لیست به روش ساده گرفته می شود
     **/
    void getTodo() {
        Call<List<Todo>> todo = apiInterface.getTodo();
        todo.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful()){

                }else{
                    String error="";
                    switch (response.code()){
                        case 404:
                            error="notFound";
                            break;
                        case 500:
                            error="serverError";
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                //no Internet
                int a = 1;
                a++;
            }
        });
    }

    /**
     * در اینجا یک لیست با آرایکس جاوا گرفته می شود
     * **/
    void getTodo_RxJava() {
        dispose_getTodo_RxJava = apiInterface.getTodo_RxJava()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(todos -> {
                    int a=1;
                    a++;
                },throwable -> {
                    int a=1;
                    a++;
                });
    }

    /**
     * در اینجا یک لیست را براساس آیدی از سرور می گیرد
     * **/
    void getTodoById(){
        dispose_getTodoById=apiInterface.getTodoById(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comments -> {
                    int a=1;
                    a++;
                },throwable -> {
                    int a=1;
                    a++;
                });
    }
}