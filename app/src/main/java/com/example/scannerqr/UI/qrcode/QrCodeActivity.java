package com.example.scannerqr.UI.qrcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.scannerqr.R;
import com.example.scannerqr.databinding.ActivityQrCodeBinding;
import com.example.scannerqr.DTO.QrCodeDTO;
import com.example.scannerqr.itemclicklistener.ItemTypeClickListener;

import java.util.ArrayList;

public class QrCodeActivity extends AppCompatActivity implements ItemTypeClickListener {
    private ActivityQrCodeBinding binding;
    private QrCodeAdapter adapter;
    public PagerQrCodeAdapter pagerAdapter;
    private ArrayList<QrCodeDTO> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();
        list.add(new QrCodeDTO(R.drawable.ic_position,true));
        list.add(new QrCodeDTO(R.drawable.ic_text,false));
        list.add(new QrCodeDTO(R.drawable.ic_url,false));
        list.add(new QrCodeDTO(R.drawable.ic_wifi,false));
        list.add(new QrCodeDTO(R.drawable.ic_contact,false));
        list.add(new QrCodeDTO(R.drawable.ic_email,false));
        list.add(new QrCodeDTO(R.drawable.ic_calender,false));

        adapter = new QrCodeAdapter(this,this);
        adapter.setData(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        binding.rcType.setLayoutManager(linearLayoutManager);
        binding.rcType.setAdapter(adapter);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        pagerAdapter = new PagerQrCodeAdapter(this);
        binding.pager2.setAdapter(pagerAdapter);
        binding.pager2.setUserInputEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void click(int position) {
        binding.pager2.setCurrentItem(position, false);
    }
}