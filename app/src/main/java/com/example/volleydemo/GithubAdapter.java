package com.example.volleydemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.zip.Inflater;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GithubViewHolder> {

    private Context context;
    private User[] data;
    public GithubAdapter(Context context,User[] data)
    {
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public GithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_user_layout,parent,false);
        return new GithubViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GithubViewHolder holder, int position) {
        final User user=data[position];
        holder.txtView.setText(user.getLogin());//text binding
        Glide.with(holder.imgUser.getContext()).load(user.getAvatarUrl()).into(holder.imgUser);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,user.getLogin()+"was clicked",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class GithubViewHolder extends RecyclerView.ViewHolder{
        ImageView imgUser;
        TextView txtView;
        public GithubViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUser=(ImageView)itemView.findViewById(R.id.imgUser);
            txtView=(TextView)itemView.findViewById(R.id.txtUser);

        }
    }
}
