package com.example.scannerqr.UI.language;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scannerqr.R;
import com.example.scannerqr.databinding.ItemLangBinding;
import com.example.scannerqr.DTO.LanguageDTO;
import java.util.ArrayList;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder>{
    Context context;
    ArrayList<LanguageDTO> list;
    int rowIndex = -1;
    public LanguageAdapter(Context context, ArrayList<LanguageDTO> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLangBinding binding = ItemLangBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.ivAnh.setImageResource(list.get(position).getHinh());
        holder.binding.tvTen.setText(list.get(position).getTen());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowIndex = position;
                notifyDataSetChanged();
            }
        });
        if(rowIndex == position){
            holder.binding.layout.setBackgroundResource(R.drawable.bg_item_lang);
        }else {
            holder.binding.layout.setBackgroundColor(Color.parseColor("#F7F7F7"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ItemLangBinding binding;
        public ViewHolder(@NonNull ItemLangBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
