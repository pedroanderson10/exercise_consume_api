package com.example.greenmileteste.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenmileteste.R;
import com.example.greenmileteste.entity.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    Context context;
    List<Post> postList;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        postList = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.example_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Post post = postList.get(position);

        holder.userId.setText(post.getUserId());
        holder.id.setText(post.getId());
        holder.tittle.setText(post.getTitle());
        holder.body.setText(post.getBody());

        /*holder.created_at.setText(post.getCreated_at());
        holder.updated_at.setText(post.getUpdated_at());
        holder.resource_id.setText(post.getResource_id());
        holder.module_id.setText(post.getModule_id());*/

        //holder.resource.setText(post.getResource());



    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder{

        TextView userId, id, tittle, body;
        //TextView created_at, updated_at, resource_id, module_id, value, language_id;
        //TextView resource;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            userId = itemView.findViewById(R.id.userid);
            id = itemView.findViewById(R.id.id);
            tittle = itemView.findViewById(R.id.tittle);
            body = itemView.findViewById(R.id.body);

            /*created_at = itemView.findViewById(R.id.userid);
            updated_at = itemView.findViewById(R.id.id);
            resource_id = itemView.findViewById(R.id.tittle);
            module_id = itemView.findViewById(R.id.body);*/

            //resource = itemView.findViewById(R.id.userid);

        }
    }

    public void filterListAux(ArrayList<Post> listAux){
        postList = listAux;
        notifyDataSetChanged();
    }


}
