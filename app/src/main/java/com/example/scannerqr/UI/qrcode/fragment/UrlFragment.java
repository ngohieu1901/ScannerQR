package com.example.scannerqr.UI.qrcode.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.scannerqr.R;
import com.example.scannerqr.UI.DETAIL.CreateQrResultlActivity;
import com.example.scannerqr.databinding.FragUrlBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;

public class UrlFragment extends Fragment {
    private FragUrlBinding binding;
    private BarcodeEncoder barcodeEncoder;
    private Bitmap bitmap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragUrlBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = binding.edName.getText().toString();
                    String url = binding.edUrl.getText().toString();
                    String content = "MEBKM:TITLE:" + name + ";URL:" + url + ";;";
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
