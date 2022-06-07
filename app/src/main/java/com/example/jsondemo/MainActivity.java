package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private List<MovieResponse> movieResponseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);
        searchView = findViewById(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                movieAdapter.getFilter().filter(query);
                return false;
            }
        });


        movieList = new ArrayList<>();
        movieResponseList = new ArrayList<>();

        ApiInterface apiService = TvMazeApiClient.getClient().create(ApiInterface.class);
        Call<List<MovieResponse>> call = apiService.getMovies();
        //Log.v("DS",""+call);
        call.enqueue(new Callback<List<MovieResponse>>() {
            @Override
            public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {
                Log.d("TAG", "Response = " + response.body().size());
                if(response.equals("")) {
                    Toast.makeText(MainActivity.this,"error", Toast.LENGTH_SHORT).show();
                }
                else{

                    for(int i = 0; i<response.body().size(); i++){
                        movieResponseList.add(response.body().get(i));
                        movieList.add(response.body().get(i).getShow());
                    }
                    movieAdapter.setMovieList(getApplicationContext(), movieList);
                }
            }

            @Override
            public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
                Toast.makeText(MainActivity.this,"HELLO"+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}