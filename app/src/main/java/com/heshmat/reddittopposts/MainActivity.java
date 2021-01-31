package com.heshmat.reddittopposts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
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
import android.widget.Button;
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
    private static final int LIMIT = 4;
    RecyclerView recyclerView;
    List<Children> children;
    ReditPostAdapter adapter;
    RetrofitInit retrofitInit;
    ProgressBar progressBar;
    String after = "";
    String before = null;
    Button prev;
    String firstPageFlag = null; // Saves the first post id to determine the first page
    MutableLiveData<String> listen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prev = findViewById(R.id.prevBt);
        retrofitInit = RetrofitInit.getInstance();
        recyclerView = findViewById(R.id.postRv);
        children = new ArrayList<>();
        adapter = new ReditPostAdapter(this, children);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        }
        recyclerView.setAdapter(adapter);
        progressBar = findViewById(R.id.progressBar);
        getFirstPage();
        listen = new MutableLiveData<>();
        listen.setValue(before);
        listen.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String changedValue) {
                if (changedValue == null || after.equals(firstPageFlag)) {
                    prev.setVisibility(View.GONE);
                } else {
                    prev.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    private void getFirstPage() {
        progressBar.setVisibility(View.VISIBLE);
        retrofitInit.getApiInterface().getData().enqueue(new Callback<RedditPosts>() {
            @Override
            public void onResponse(Call<RedditPosts> call, Response<RedditPosts> response) {
                RedditPosts redditResponse = response.body();
                if (redditResponse != null && redditResponse.getData() != null) {
                    after = redditResponse.getData().getAfter();
                    firstPageFlag = after;
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

    public void checkInternetConnection(Throwable t) {
        Log.i(TAG, "onFailure: " + t.fillInStackTrace());
        if (t instanceof UnknownHostException) {
            new AlertDialog.Builder(MainActivity.this).setMessage(getString(R.string.check_internet))
                    .setPositiveButton(getString(R.string.ok),null)
                    .show();
        } else
            new AlertDialog.Builder(MainActivity.this).setMessage(t.getLocalizedMessage()).show();


    }


    public void getAfter(View view) {
        if (!after.isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);
            retrofitInit.getApiInterface().getDataAfter(after, LIMIT).enqueue(new Callback<RedditPosts>() {
                @Override
                public void onResponse(Call<RedditPosts> call, Response<RedditPosts> response) {
                    RedditPosts redditResponse = response.body();
                    if (redditResponse != null && redditResponse.getData() != null) {
                        after = redditResponse.getData().getAfter();
                        children.clear();
                        children.addAll(redditResponse.getData().getChildren());
                        adapter.notifyDataSetChanged();
                        if (validateData(redditResponse.getData().getChildren())) {
                            before = redditResponse.getData().getChildren().get(0).getData().getName();
                            listen.setValue(before);
                        }
                    }

                    progressBar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<RedditPosts> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    checkInternetConnection(t);
                }
            });


        } else {
            getFirstPage();
        }


    }
    public void getBefore(View view) {
        if (firstPageFlag != null && !firstPageFlag.equals(after)) { // check if  first page
            progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            retrofitInit.getApiInterface().getDataBefore(before, LIMIT).enqueue(new Callback<RedditPosts>() {
                @Override
                public void onResponse(Call<RedditPosts> call, Response<RedditPosts> response) {
                    RedditPosts redditResponse = response.body();
                    if (redditResponse != null && redditResponse.getData() != null) {

                        if (redditResponse.getData().getAfter() != null)
                            after = redditResponse.getData().getAfter();
                        else { // if 'after' in response is null  get the id of the last post in the response
                            if (validateData(redditResponse.getData().getChildren())) {
                                after = redditResponse.getData().getChildren().get(redditResponse.getData().getChildren().size() - 1).getData().getName();

                            }
                        }
                        if (validateData(redditResponse.getData().getChildren())) {
                            before = redditResponse.getData().getChildren().get(0).getData().getName();
                            listen.setValue(before);
                        }
                        children.clear();
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


        } else {
            before = null;
            listen.setValue(before);

        }

    }

    public boolean validateData(List<Children> childrenList) {
        return childrenList != null && !childrenList.isEmpty();
    }
}
