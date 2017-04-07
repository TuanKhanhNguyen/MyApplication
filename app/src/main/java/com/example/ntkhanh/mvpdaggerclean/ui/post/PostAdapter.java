package com.example.ntkhanh.mvpdaggerclean.ui.post;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ntkhanh.mvpdaggerclean.R;
import com.example.ntkhanh.mvpdaggerclean.data.model.Post;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ntkhanh on 3/26/17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> postList;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_post_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.textView.setText(post.getTitle());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void replaceData(List<Post> tasks) {
        setList(tasks);
        notifyDataSetChanged();
    }

    private void setList(List<Post> tasks) {
        postList = checkNotNull(tasks);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
