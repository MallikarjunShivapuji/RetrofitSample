package com.shivas.retrofitsample;

import android.content.Context;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {
    private Context mContext;
    private static MoviesRepository instance;

    private static JsonPlaceHolderApi jsonPlaceHolderApi;


    private MoviesRepository(Context context) {
        mContext = context;
    }

    public static MoviesRepository getInstance(Context context) {
        if(instance == null) {
            instance = new MoviesRepository(context);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        }
        return instance;
    }

    public List<Movies> getMovies(Callback callback) {
        Call<List<Movies>> call = jsonPlaceHolderApi.getMovies();

        call.enqueue(callback);
        return null;
    }
}
