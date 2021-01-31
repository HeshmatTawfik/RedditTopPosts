package com.heshmat.reddittopposts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.heshmat.reddittopposts.utils.DownloadImageTask;
import com.heshmat.reddittopposts.utils.TouchImageView;

import java.util.concurrent.ExecutionException;

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
        try {
            Bitmap bitmap= new DownloadImageTask(touchImageView).execute(imgUrl).get();
            if (bitmap==null){ // check whether the link is an image or no
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(imgUrl));
                startActivity(browserIntent);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
