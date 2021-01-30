package com.heshmat.reddittopposts.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.heshmat.reddittopposts.R;
import com.heshmat.reddittopposts.models.Children;
import com.heshmat.reddittopposts.utils.DownloadImageTask;

import java.util.Date;
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

    private void bindPost(Children children, ViewHolder holder) {
        String authorName = children.getData().getAuthor();
        long createdAt = (long) children.getData().getCreatedUtc() * 1000;
        String commentNum = String.valueOf(children.getData().getNumComments());
        String thumbUri = children.getData().getThumbnail();
        String title = children.getData().getTitle();
        holder.authorNameTv.setText(authorName);
        holder.createdAtTv.setText(new Date(createdAt).toString());
        holder.commentNumTv.setText(commentNum);
        holder.titleTv.setText(title);
        new DownloadImageTask(holder.thumbIv).execute(thumbUri);


    }

}
