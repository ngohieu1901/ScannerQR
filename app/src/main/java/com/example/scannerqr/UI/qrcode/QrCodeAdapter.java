package com.example.scannerqr.UI.qrcode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scannerqr.R;
import com.example.scannerqr.DTO.QrCodeDTO;
import com.example.scannerqr.itemclicklistener.ItemTypeClickListener;

import java.util.ArrayList;

public class QrCodeAdapter extends RecyclerView.Adapter<QrCodeAdapter.ViewHolder> {
    private Context context;
    private ArrayList<QrCodeDTO> list;
    private int row_index = 0;
    private ItemTypeClickListener itemTypeClickListener;
    public QrCodeAdapter(Context context,ItemTypeClickListener clickListener) {
        this.context = context;
        this.itemTypeClickListener = clickListener;
    }

    public void setData(ArrayList<QrCodeDTO> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Position");
        list1.add("Text");
        list1.add("Url");
        list1.add("Wifi");
        list1.add("Contact");
        list1.add("Email");
        list1.add("Schedule");

        QrCodeDTO qrCodeDTO = list.get(position);

        if (qrCodeDTO.isSelected() == true){
            holder.tv_bg.setVisibility(View.VISIBLE);
        }else {
            holder.tv_bg.setVisibility(View.GONE);
        }
        holder.iv_type.setImageResource(list.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    holder.tv_bg.setText("Position");
                } else if (position == 1) {
                    holder.tv_bg.setText("Text");
                }else if (position == 2) {
                    holder.tv_bg.setText("Url");
                }else if (position == 3) {
                    holder.tv_bg.setText("Wifi");
                }else if (position == 4) {
                    holder.tv_bg.setText("Contact");
                }else if (position == 5) {
                    holder.tv_bg.setText("Email");
                }else if (position == 6) {
                    holder.tv_bg.setText("Schdule");
                }
                itemTypeClickListener.click(position);
                row_index = position;
                notifyDataSetChanged();
            }
        });

        if (row_index == position){
                holder.tv_bg.setVisibility(View.VISIBLE);
                holder.tv_bg.setText(list1.get(position));
        }else {
                holder.tv_bg.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_type;
        TextView tv_bg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_type = itemView.findViewById(R.id.iv_type);
            tv_bg = itemView.findViewById(R.id.tv_bg);
        }
    }
}
