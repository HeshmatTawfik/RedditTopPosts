package com.heshmat.reddittopposts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.heshmat.reddittopposts.utils.DownloadImageTask;
import com.heshmat.reddittopposts.utils.TouchImageView;

public class FullScreenViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_view);
        ImageView closeIv =findViewById(R.id.closeIv);
        Intent intent=getIntent();
        String imgUrl=intent.getStringExtra("IMG_URL");
        closeIv.setOnClickListener((View view)->{
            FullScreenViewActivity.this.finish();


        });

        TouchImageView touchImageView=findViewById(R.id.imgDisplay);
        new DownloadImageTask(touchImageView).execute(imgUrl);

    }
}
