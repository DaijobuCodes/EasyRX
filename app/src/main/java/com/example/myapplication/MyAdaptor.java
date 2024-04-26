package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder> {

    private ArrayList<Data> datalist;

    public MyAdaptor(ArrayList<Data> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    private Context context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(datalist.get(position).getImageURL()).into(holder.recyclerImage);
        holder.recycleCaption.setText(datalist.get(position).getCaption());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView recycleCaption;
        ImageView recyclerImage;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            recycleCaption = itemView.findViewById(R.id.recyclerCaption);
            recyclerImage = itemView.findViewById(R.id.recyclerImage);

        }
    }



}
