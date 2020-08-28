package com.shivas.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    private MoviesViewModel mMoviesViewModel;
    private static JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.text);

        mMoviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        mMoviesViewModel.getMovies().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                //Log.e("Mallikarjun.S",movies.toString());
                Toast.makeText(MainActivity.this, "DataChnages " + movies.toString(), Toast.LENGTH_LONG).show();

                generateDataList(movies);
            }
        });



        //getPosts();
        //getPost();
        //getComments();
        //getPosts(1);
        //createPost();

        //getMovies();

    }


    private void generateDataList(List<Movies> moviesList) {
        recyclerView = findViewById(R.id.movies_list);
        adapter = new MovieAdapter(this, moviesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    void getMovies() {
        tv.setText("Waiting response");
        Call<List<Movies>> call = jsonPlaceHolderApi.getMovies();

        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                tv.setText("");

                if(response.isSuccessful()) {
                    generateDataList(response.body());
                    tv.setVisibility(View.GONE);
                    for(Movies movies : response.body()) {
                        tv.append(movies.toString());
                    }
                } else {
                    tv.setText("Not Sucessfully ");
                }
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                tv.setText("Something went wrong...Please try later!");
            }
        });


    }

    void createPost() {
        Post post = new Post(18, "Title", "Body");
        Call<Post> call = jsonPlaceHolderApi.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                tv.setText("Post request Sucessfull");
                Post post = response.body();
                tv.setText("Reponse Code: " + response.code() + "\n" + post.toString());

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tv.setText("Post request failed");

            }
        });
    }

    private void getPosts(int postId) {
        Map map = new HashMap<String, Object>();
        map.put("userId" , 2);
        map.put("_sort", "id");
        map.put("_order", "DESC");


        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(map);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                //tv.setText("Sucessful: " + );

                tv.setText("");
                if(!response.isSuccessful()) {
                    tv.setText("Response not sucessful: " + response.code());
                }
                List<Post> postList = response.body();
                //tv.setText(postList.toString());
                for(Post post : postList) {
                    tv.append(post.toString() + "\n\n");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tv.setText("onFailure: " + t.getMessage());
            }
        });
    }

    void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if(response.isSuccessful()) {
                    List<Comment> comments = response.body();
                    for(Comment comment : comments) {
                        String data = "";
                        data += comment.toString();

                        tv.append(data + "\n\n");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

    }

    void getPost() {
        Call<Post> call = jsonPlaceHolderApi.getPost();
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()) {
                    Post post = response.body();
                    tv.setText("" + post.getId() + ", " + post.getUserId() + ", " + post.getTitle() + ", " + post.getBody());
                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void getPosts() {

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                //tv.setText("Sucessful: " + );
                if(!response.isSuccessful()) {
                    tv.setText(response.code());
                }
                List<Post> postList = response.body();
                //tv.setText(postList.toString());
                for(Post post : postList) {
                    tv.append(post.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tv.setText("Failure: " + t.getMessage());

            }
        });
    }
}