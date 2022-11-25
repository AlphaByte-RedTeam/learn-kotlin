package com.mobileprogramming.learnapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSearch;
    EditText searchInput;
    ImageView poster;
    private MovieService movieService;
    TextView textViewPlot, textViewMovie, textViewTitle, textViewGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch = findViewById(R.id.btnSearch);
        searchInput = findViewById(R.id.etField);
        poster = findViewById(R.id.imgViewPoster);
        textViewMovie = findViewById(R.id.textViewMovie);
        textViewPlot = findViewById(R.id.textViewPlot);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewGenre = findViewById(R.id.textViewGenre);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Retrofit retrofitInstance = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieService = retrofitInstance.create(MovieService.class);
        Call<Movie> movieCall = movieService.searchMovie(searchInput.getText().toString());
        movieCall.enqueue((Callback<Movie>) this);
    }

    public void onResponse(Call<Movie> call, Response<Movie> response) {
        textViewTitle.setText((response.body().getTitle().toString()));
        textViewMovie.setText("Actor: " + (response.body().getActors()).toString());
        textViewPlot.setText("Plot: " + (response.body().getPlot()).toString());
        textViewGenre.setText("Genre: " + (response.body().getGenre()).toString());
        Log.i("MainActivity", "onResponse: " + response.body().getPoster());
        Picasso.with(this).load(response.body().getPoster()).into(poster);
    }

    public void onFailure(Call<Movie> call, Throwable t) {
        textViewMovie.setText("Invalid info");
    }
}