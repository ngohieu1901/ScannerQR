package com.example.scannerqr.UI.DETAIL;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amazic.ads.util.AppOpenManager;
import com.example.scannerqr.support.Mapper;
import com.example.scannerqr.support.OnSingleClickListener;
import com.example.scannerqr.databinding.ActivityDetailBinding;
import com.example.scannerqr.DTO.CodeDTO;
import com.example.scannerqr.UI.scan.ScanActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Log.d("handelInsertCode","ANDROI > 13");
                CodeDTO qrCode = bundle.getParcelable("QRCODE", CodeDTO.class);
                String content = qrCode.getContent();
                Log.d("handelInsertCode","TYPE: "+qrCode.getType());
                Log.d("handelInsertCode","ANDROI < 13");
                if (Integer.parseInt(qrCode.getType()) == 0){
                    Toast.makeText(this, "TYPE_UNKNOWN", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(qrCode.getType()) == 1) {
                    List<String> content1 = Mapper.convertToValueContact(content);
                    Log.d("handelInsertCode","Value: "+content1);
                    String name = content1.get(0);
                    String phone = content1.get(1);
                    String email = content1.get(2);
                    Log.d("handelInsertCode","Value1: "+name);
                    Log.d("handelInsertCode","Value2: "+phone);
                    Log.d("handelInsertCode","Value3: "+email);
                    binding.tvDetail.setText("Name: "+name+"\nPhone: "+phone+"\nEmail: "+email);
                } else if (Integer.parseInt(qrCode.getType()) == 2) {//email
                    ArrayList<String> result = Mapper.convertToValueEmail(content);
                    String email = result.get(0);
                    String sub = result.get(1);
                    binding.tvDetail.setText("Email: "+email +"\n"+"Subject: "+sub);
                } else if (Integer.parseInt(qrCode.getType()) == 9) {
                    List<String> content1 = Mapper.convertToValueWifi(content);
                    Log.d("handelInsertCode","Value: "+content1);
                    String name = content1.get(0);
                    String type = content1.get(1);
                    String pass = content1.get(2);
                    Log.d("handelInsertCode","Value1: "+name);
                    Log.d("handelInsertCode","Value2: "+type);
                    Log.d("handelInsertCode","Value3: "+pass);
                    binding.tvDetail.setText("Wifi: "+name+"\nType: "+type+"\nPassword: "+pass);
                }else if (Integer.parseInt(qrCode.getType()) == 10) {
                    List<String> content1 = Mapper.convertToValueLocation(content);
                    Log.d("handelInsertCode","Value: "+content1);
                    String latitude = content1.get(0);
                    String longitude = content1.get(1);
                    String altitude = content1.get(2);
                    Log.d("handelInsertCode","Value1: "+latitude);
                    Log.d("handelInsertCode","Value2: "+longitude);
                    Log.d("handelInsertCode","Value3: "+altitude);
                    binding.tvDetail.setText("Latitude: "+latitude+"\nLongitude: "+longitude+"\nAltitude: "+altitude);
                }else if (Integer.parseInt(qrCode.getType()) == 7){ // text
                    binding.tvDetail.setText(content);
                }else if (Integer.parseInt(qrCode.getType()) == 8){//url
                    ArrayList<String> result = Mapper.convertToValueUrl(content);
                    String name = result.get(0);
                    String url = result.get(1);
                    binding.tvDetail.setText("Name: "+name +"\n"+"Url: "+url);
                }else if (Integer.parseInt(qrCode.getType()) == 32 || Integer.parseInt(qrCode.getType()) == 64){//barcode
                    binding.tvDetail.setText(content);
                }
            }else {
                @Deprecated
                CodeDTO qrCode = bundle != null ? bundle.getParcelable("QRCODE") : null;
                String content = qrCode.getContent();
                Log.d("handelInsertCode","TYPE: "+qrCode.getType());
                Log.d("handelInsertCode","ANDROI < 13");
                if (Integer.parseInt(qrCode.getType()) == 0){
                    Toast.makeText(this, "TYPE_UNKNOWN", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(qrCode.getType()) == 1) {
                    List<String> content1 = Mapper.convertToValueContact(content);
                    Log.d("handelInsertCode","Value: "+content1);
                    String name = content1.get(0);
                    String phone = content1.get(1);
                    String email = content1.get(2);
                    Log.d("handelInsertCode","Value1: "+name);
                    Log.d("handelInsertCode","Value2: "+phone);
                    Log.d("handelInsertCode","Value3: "+email);
                    binding.tvDetail.setText("Name: "+name+"\nPhone: "+phone+"\nEmail: "+email);
                } else if (Integer.parseInt(qrCode.getType()) == 2) {//email
                    ArrayList<String> result = Mapper.convertToValueEmail(content);
                    String email = result.get(0);
                    String sub = result.get(1);
                    binding.tvDetail.setText("Email: "+email +"\n"+"Subject: "+sub);
                } else if (Integer.parseInt(qrCode.getType()) == 9) {
                    List<String> content1 = Mapper.convertToValueWifi(content);
                    Log.d("handelInsertCode","Value: "+content1);
                    String name = content1.get(0);
                    String type = content1.get(1);
                    String pass = content1.get(2);
                    Log.d("handelInsertCode","Value1: "+name);
                    Log.d("handelInsertCode","Value2: "+type);
                    Log.d("handelInsertCode","Value3: "+pass);
                    binding.tvDetail.setText("Wifi: "+name+"\nType: "+type+"\nPassword: "+pass);
                }else if (Integer.parseInt(qrCode.getType()) == 10) {
                    List<String> content1 = Mapper.convertToValueLocation(content);
                    Log.d("handelInsertCode","Value: "+content1);
                    String latitude = content1.get(0);
                    String longitude = content1.get(1);
//                    String altitude = content1.get(2);
                    Log.d("handelInsertCode","Value1: "+latitude);
                    Log.d("handelInsertCode","Value2: "+longitude);
//                    Log.d("handelInsertCode","Value3: "+altitude);
                    binding.tvDetail.setText("Latitude: "+latitude+"\nLongitude: "+longitude);
                }else if (Integer.parseInt(qrCode.getType()) == 7){ // text
                    binding.tvDetail.setText(content);
                }else if (Integer.parseInt(qrCode.getType()) == 8){//url
                    ArrayList<String> result = Mapper.convertToValueUrl(content);
                    String name = result.get(0);
                    String url = result.get(1);
                    binding.tvDetail.setText("Name: "+name +"\n"+"Url: "+url);
                }else if (Integer.parseInt(qrCode.getType()) == 32 || Integer.parseInt(qrCode.getType()) == 64){//barcode
                    binding.tvDetail.setText(content);
                }
            }
        }else {
            Log.d("handelInsertCode","NULL");
        }

        binding.ivBack.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                onBackPressed();
            }
        });

        binding.ivCopy.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String text = binding.tvDetail.getText().toString();
                ClipData clipData = ClipData.newPlainText("Label",text);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(DetailActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });

        binding.ivSearch.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                String content = binding.tvDetail.getText().toString();
                goToGoogle(content);
            }
        });
    }

    private void gotoUrl(String url) {
        try {
            Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(openBrowserIntent);
            AppOpenManager.getInstance().disableAppResumeWithActivity(DetailActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToGoogle(String text) {
        Intent openBrowserIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        openBrowserIntent.putExtra(SearchManager.QUERY, text);
        startActivity(openBrowserIntent);
        AppOpenManager.getInstance().disableAppResumeWithActivity(DetailActivity.class);
    }

    private void gotoConnectWifi() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivity(intent);
        AppOpenManager.getInstance().disableAppResumeWithActivity(DetailActivity.class);
    }

    private void gotoCallPhone(String numberPhone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + numberPhone));
        startActivity(intent);
        AppOpenManager.getInstance().disableAppResumeWithActivity(DetailActivity.class);
    }

    private void goToMap(String latitude, String longitude) {
        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude;
        Intent openMapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(openMapIntent);
        AppOpenManager.getInstance().disableAppResumeWithActivity(DetailActivity.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}