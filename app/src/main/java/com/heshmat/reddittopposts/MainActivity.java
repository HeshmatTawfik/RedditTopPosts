package com.heshmat.reddittopposts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.heshmat.reddittopposts.Adapters.ReditPostAdapter;
import com.heshmat.reddittopposts.api.RetrofitInit;
import com.heshmat.reddittopposts.models.Children;
import com.heshmat.reddittopposts.models.RedditPosts;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    List<Children> children;
    ReditPostAdapter adapter;
    RetrofitInit retrofitInit;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofitInit = RetrofitInit.getInstance();
        recyclerView = findViewById(R.id.postRv);
        children = new ArrayList<>();
        adapter = new ReditPostAdapter(this, children);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        }
        else{
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        }
        recyclerView.setAdapter(adapter);
        progressBar = findViewById(R.id.progressBar);
        getFirstPage();


    }
    private void getFirstPage(){
        progressBar.setVisibility(View.VISIBLE);
        retrofitInit.getApiInterface().getData().enqueue(new Callback<RedditPosts>() {
            @Override
            public void onResponse(Call<RedditPosts> call, Response<RedditPosts> response) {
                RedditPosts redditResponse = response.body();
                if (redditResponse != null && redditResponse.getData() != null) {

                    children.addAll(redditResponse.getData().getChildren());
                    adapter.notifyDataSetChanged();
                }

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<RedditPosts> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                checkInternetConnection(t);
            }
        });
    }
    public void checkInternetConnection(Throwable t){
        Log.i(TAG, "onFailure: "+t.fillInStackTrace());
        if (t instanceof UnknownHostException){
            new AlertDialog.Builder(MainActivity.this).setMessage(getString(R.string.check_internet)).show();
        }
        else
            new AlertDialog.Builder(MainActivity.this).setMessage(t.getLocalizedMessage()).show();


    }
}
