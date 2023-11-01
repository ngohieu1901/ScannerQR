package com.example.scannerqr.UI.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.scannerqr.databinding.ActivityIntroBinding;
import com.example.scannerqr.UI.permission.PermissionActivity;

public class IntroActivity extends AppCompatActivity {

    private FrameLayout native_ads;
    IntroAdapter adapter;
    private ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new IntroAdapter();

        binding.pager2.setAdapter(adapter);

        binding.pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {

                } else if (position == 1) {

                } else if (position == 2) {

                }
            }
        });

        binding.tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.pager2.getCurrentItem() == 0) {
                    binding.pager2.setCurrentItem(binding.pager2.getCurrentItem() + 1);
                } else if (binding.pager2.getCurrentItem() == 1) {
                    binding.pager2.setCurrentItem(binding.pager2.getCurrentItem() + 1);
                } else if (binding.pager2.getCurrentItem() == 2) {
                    startActivity(new Intent(IntroActivity.this, PermissionActivity.class));
                    finish();
                }
            }
        });
    }
}