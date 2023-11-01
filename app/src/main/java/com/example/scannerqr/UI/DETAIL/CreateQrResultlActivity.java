package com.example.scannerqr.UI.DETAIL;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.scannerqr.databinding.ActivityCreateQrResultBinding;

import java.io.ByteArrayOutputStream;


public class CreateQrResultlActivity extends AppCompatActivity {
    private Bitmap imageBitmap;
    private ActivityCreateQrResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateQrResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        byte[] imageQrCode = getIntent().getByteArrayExtra("imageQrCode");
        imageBitmap = BitmapFactory.decodeByteArray(imageQrCode, 0, imageQrCode.length);
        binding.ivQrcode.setImageBitmap(imageBitmap);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageBitmap != null) {
                    MediaStore.Images.Media.insertImage(
                            getContentResolver(),
                            imageBitmap,
                            null,
                            null
                    );
                    Toast.makeText(getApplicationContext(), "Hình ảnh đã được lưu vào bộ sưu tập.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Không có hình ảnh để lưu.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageBitmap != null) {
                    // Tạo Intent để chia sẻ ảnh
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    // Đặt dữ liệu ảnh để chia sẻ
                    Uri imageUri = getImageUri(CreateQrResultlActivity.this, imageBitmap);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    // Khởi chạy Intent để chia sẻ
                    startActivity(Intent.createChooser(shareIntent, "Chia sẻ ảnh qua"));
                } else {
                    Toast.makeText(getApplicationContext(), "Không có hình ảnh để chia sẻ.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Uri getImageUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }


}