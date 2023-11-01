package com.example.scannerqr.UI.language;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.scannerqr.R;
import com.example.scannerqr.databinding.ActivityLanguageBinding;
import com.example.scannerqr.DTO.LanguageDTO;
import com.example.scannerqr.UI.intro.IntroActivity;

import java.util.ArrayList;

public class LanguageActivity extends AppCompatActivity {
    LanguageAdapter adapter;
    ArrayList<LanguageDTO> list;
    private ActivityLanguageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();
        list.add(new LanguageDTO(R.drawable.anh,"English"));
        list.add(new LanguageDTO(R.drawable.hindi,"Hindi"));
        list.add(new LanguageDTO(R.drawable.tbn,"Spanish"));
        list.add(new LanguageDTO(R.drawable.phap,"French"));
        list.add(new LanguageDTO(R.drawable.bdn,"Portuguese"));
        list.add(new LanguageDTO(R.drawable.indo,"Indonesian"));
        list.add(new LanguageDTO(R.drawable.duc,"German"));

        RecyclerView rc_lang = binding.rcLanguage;
        adapter = new LanguageAdapter(this,list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rc_lang.setLayoutManager(linearLayoutManager);
        rc_lang.setAdapter(adapter);

        binding.ivCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanguageActivity.this, IntroActivity.class));
                finish();
            }
        });
    }
}