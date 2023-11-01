package com.example.scannerqr.UI.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent; 
import android.os.Bundle;
import android.view.View;

import com.example.scannerqr.support.OnSingleClickListener;
import com.example.scannerqr.databinding.ActivityMainBinding;
import com.example.scannerqr.UI.qrcode.QrCodeActivity;
import com.example.scannerqr.UI.scan.ScanActivity;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivScan.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                startActivity(new Intent(MainActivity.this, ScanActivity.class));
            }
        });

        binding.layoutQr.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                startActivity(new Intent(MainActivity.this, QrCodeActivity.class));
            }
        });
    }


}