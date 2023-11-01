package com.example.scannerqr.UI.qrcode.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.scannerqr.R;
import com.example.scannerqr.UI.DETAIL.CreateQrResultlActivity;
import com.example.scannerqr.databinding.FragPositionBinding;
import com.example.scannerqr.databinding.FragWifiBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;

public class PositionFragment extends Fragment {
    private FragPositionBinding binding;
    private BarcodeEncoder barcodeEncoder;
    private Bitmap bitmap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragPositionBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String lati = binding.edLa.getText().toString();
                    String longti = binding.edLong.getText().toString();
                    String content = "geo:" + lati + "," + longti;
                    barcodeEncoder = new BarcodeEncoder();
                    bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 500, 500);
                    byte[] imageQrCode = convertBitmapToByteArray(bitmap);
                    Intent intent = new Intent(getActivity(), CreateQrResultlActivity.class);
                    intent.putExtra("imageQrCode",imageQrCode);
                    startActivity(intent);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

