package com.heshmat.reddittopposts.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.heshmat.reddittopposts.FullScreenViewActivity;
import com.heshmat.reddittopposts.R;
import com.heshmat.reddittopposts.models.Children;
import com.heshmat.reddittopposts.utils.DownloadImageTask;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReditPostAdapter extends RecyclerView.Adapter<ReditPostAdapter.ViewHolder> {
    private static final String TAG = "Redit";
    Context context;
    List<Children> childrenList;

    public ReditPostAdapter(Context context, List<Children> childrenList) {
        this.context = context;
        this.childrenList = childrenList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Children post = childrenList.get(position);
        bindPost(post, holder);

    }

    @Override
    public int getItemCount() {
        return childrenList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView authorNameTv, createdAtTv, titleTv, commentNumTv;
        ImageView thumbIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            authorNameTv = itemView.findViewById(R.id.authorNameTv);
            createdAtTv = itemView.findViewById(R.id.createdAtTv);
            commentNumTv = itemView.findViewById(R.id.commentNumTv);
            thumbIv = itemView.findViewById(R.id.thumbIv);
            titleTv = itemView.findViewById(R.id.titleTv);


        }
    }

    private void bindPost(final Children children, ViewHolder holder) {
        String authorName = children.getData().getAuthor();
        /**
         * Reddit API returns the timestamp missing the last 3 digits for example if
         * a post was created Sat Jan 30, 2021, 23:49 the timestamp should be 1612050579351
         * but the API return 1612050579.0 so by casting to long and multiplying by 1000 we get the right timestamp
         * which is 1612050579000 the difference between this timestamp and 1612050579351 is less than a second so
         * it won't affect the date that is presented to the user
         *  */
        long createdUtc = (long) children.getData().getCreatedUtc() * 1000;
        CharSequence ago = DateUtils.getRelativeTimeSpanString(createdUtc, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS);

        String commentNum = String.valueOf(children.getData().getNumComments());
        final String thumbUri = children.getData().getThumbnail();
        String title = children.getData().getTitle();
        holder.authorNameTv.setText(authorName);
        holder.createdAtTv.setText(ago);
        holder.commentNumTv.setText(commentNum);
        holder.titleTv.setText(title);
        if (children.getData().getThumbBitmap() == null)
            new DownloadImageTask(holder.thumbIv, children).execute(thumbUri);
        else {
            // used when the app restore the saved state, it get the thumbnail img from the object instead of downloading it again
            holder.thumbIv.setVisibility(View.VISIBLE);
            holder.thumbIv.setImageBitmap(children.getData().getThumbBitmap());
        }

        if (children.getData().getImgURl() != null) {
            holder.thumbIv.setOnClickListener((View view) -> {
                if (!children.getData().isVideo()) {
                    Intent intent = new Intent(context, FullScreenViewActivity.class);
                    intent.putExtra("IMG_URL", children.getData().getImgURl());
                    context.startActivity(intent);
                } else if (children.getData().isVideo()) {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(children.getData().getImgURl()));
                    context.startActivity(browserIntent);

                }

            });
        }

    }

}
