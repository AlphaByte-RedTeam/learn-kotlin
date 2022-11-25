package com.mobileprogramming.learnapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("?i=tt3896198&apikey=4b3f141d")
    Call<Movie> searchMovie(@Query("t") String title);

}
