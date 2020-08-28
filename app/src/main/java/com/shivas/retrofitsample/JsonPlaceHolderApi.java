package com.shivas.retrofitsample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {

    @GET("photos")
    Call<List<Movies>> getMovies();

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/1")
    Call<Post> getPost();

    @GET("/posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET("/posts")
    Call<List<Post>> getPosts(@Query("userId") Integer[] postId,
                              @Query("_sort") String sort,
                              @Query("_order") String order);

    @GET("/posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, Object> parameters);/*@Query("userId") Integer[] postId,
                              @Query("_sort") String sort,
                              @Query("_order") String order);*/

    @POST("posts")
    Call<Post> createPost(@Body Post post);
}
