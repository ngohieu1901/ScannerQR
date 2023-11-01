package com.example.scannerqr.UI.permission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.scannerqr.databinding.ActivityPermissionBinding;
import com.example.scannerqr.UI.scan.ScanActivity;

public class PermissionActivity extends AppCompatActivity {
    private ActivityPermissionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
                }
            }
        });
        binding.tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PermissionActivity.this, ScanActivity.class));
                finish();
            }
        });
    }
}