package com.example.banganhbui.interview.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.banganhbui.interview.R;
import com.example.banganhbui.interview.view.model.Result;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Result> examples;
    private Context context;

    public Adapter(Context context, ArrayList<Result> examples) {
        this.context = context;
        this.examples = examples;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (examples.get(position).getUrl() != null) {
            Glide.with(context).load(examples.get(position).getUrl()).into(holder.imv_view);
        } else {
            holder.imv_view.setImageResource(R.drawable.ic_launcher);
        }
        holder.tv_name.setText(examples.get(position).getName());
        holder.tv_address.setText(examples.get(position).getAddress());
        holder.tv_distance.setText(examples.get(position).getDistance());
    }

    @Override
    public int getItemCount() {
        return examples.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imv_view;
        private TextView tv_name, tv_address, tv_distance;

        public ViewHolder(View itemView) {
            super(itemView);
            imv_view = itemView.findViewById(R.id.imv_view);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_distance = itemView.findViewById(R.id.tv_distance);
        }
    }
}
