package com.shivas.retrofitsample;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesViewModel extends AndroidViewModel {
    private MutableLiveData<List<Movies>> mMoviesList = new MutableLiveData<List<Movies>>();;
    private MoviesRepository mRepo;

    public MoviesViewModel(@NonNull final Application application) {
        super(application);
        mRepo =  MoviesRepository.getInstance(application);
        Movies initMovie = new Movies(1,
                1,
                "accusamus beatae ad facilis cum similique qui sunt",
                "https://via.placeholder.com/600/92c952",
                "https://via.placeholder.com/150/92c952");
        ArrayList<Movies> initArrayList = new ArrayList<Movies>();
        initArrayList.add(initMovie);
        mMoviesList = new MutableLiveData<List<Movies>>();
        //mMoviesList.setValue(initArrayList);

        mRepo.getMovies(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                //tv.setText("");

                if(response.isSuccessful()) {
                    System.out.print("Mallikarjun.S " + response.body().toString());
                    //Toast.makeText(application, response.body().toString(), Toast.LENGTH_LONG).show();
                    /*tv.setVisibility(View.GONE);
                    for(Movies movies : response.body()) {
                        tv.append(movies.toString());
                    }*/
                    mMoviesList.setValue((List<Movies>)response.body());
                    //mMoviesList.notifyAll();
                } else {
                    //tv.setText("Not Sucessfully ");
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    /*public List<Movies> getMovies() {
        return  mRepo.getMovies();

    }*/


    public LiveData<List<Movies>> getMovies() {
        return mMoviesList;
    }
}
