package com.example.scannerqr.UI.intro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scannerqr.R;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.ViewHolder> {
    int pageCount = 3;

    public IntroAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_intro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0 ) {
            holder.tv_1.setText("text 1");
            holder.tv_2.setText("text 1");
//            holder.iv_background.setImageResource();
        } else if (position == 1) {
            holder.tv_1.setText("text 2");
            holder.tv_2.setText("text 2");
//            holder.iv_background.setImageResource();
        } else if (position == 2) {
            holder.tv_1.setText("text 3");
            holder.tv_2.setText("text 3");
//            holder.iv_background.setImageResource();
        }
    }

    @Override
    public int getItemCount() {
        return pageCount;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_background;
        TextView tv_1, tv_2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_background = itemView.findViewById(R.id.iv_bg);
            tv_1 = itemView.findViewById(R.id.tv_1);
            tv_2 = itemView.findViewById(R.id.tv_2);
        }
    }
}
