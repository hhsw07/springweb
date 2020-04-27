package com.example.empsearch;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.ViewHolder> {
    ArrayList<Emp> items = new ArrayList<Emp>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.emp_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Emp item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Emp item) {
        items.add(item);
    }

    public void setItems(ArrayList<Emp> items) {
        this.items = items;
    }

    public Emp getItem(int position) {
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView enameTV;
        TextView salTV;

        public ViewHolder(View itemView) {
            super(itemView);

            enameTV = itemView.findViewById(R.id.enameTV);
            salTV = itemView.findViewById(R.id.salTV);
        }

        public void setItem(Emp item) {
            enameTV.setText(item.getEname());
            salTV.setText(item.getSal() + " 만원");
        }

    }

}
