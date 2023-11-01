package com.example.scannerqr.UI.scan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.scannerqr.R;
import com.example.scannerqr.DATABASE.CodeDatabase;
import com.example.scannerqr.DTO.CodeDTO;
import com.example.scannerqr.UI.DETAIL.DetailActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.io.IOException;
import java.util.List;

public class PreviewActivity extends AppCompatActivity {
    private Uri selectedImage;
    private BarcodeScannerOptions options;
    private BarcodeScanner barcodeScanner;

    private ImageView iv_preview;
    public static boolean isInsert = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        iv_preview = findViewById(R.id.iv_preview);
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        if (path != null) {
            selectedImage = Uri.parse(path);
            Glide.with(this).load(selectedImage).into(iv_preview);
            try {
                InputImage inputImage = InputImage.fromFilePath(this,selectedImage);
                options = new BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_EAN_13,Barcode.FORMAT_QR_CODE,Barcode.FORMAT_ALL_FORMATS).build();
                barcodeScanner = BarcodeScanning.getClient(options);
                barcodeScanner.process(inputImage).addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                            @Override
                            public void onSuccess(List<Barcode> barcodes) {
                                if (barcodes.isEmpty()){
                                    Toast.makeText(PreviewActivity.this,"Unsupported data type: ", Toast.LENGTH_SHORT).show();
                                }
                                handelQrCode(barcodes.get(0));
                            }
                        });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isInsert = false;
    }

    private String handelQrCode(Barcode barcode){
        switch ((barcode.getValueType())){
            case  Barcode.TYPE_URL: {
                String type = String.valueOf(barcode.getValueType());
                String content = barcode.getRawValue();
                Log.d("handelInsertCode","TYPE:" + type);
                Log.d("handelInsertCode","VALUE: " + content);
                CodeDTO codeDTO = new CodeDTO(type,content);
                CodeDatabase.getInstance(this).codeDAO().insertCode(codeDTO);
                scanResult(codeDTO);
                break;
            }
            case  Barcode.TYPE_TEXT: {
                Log.e("handelInsertCode", "handelInsertCode2: ${result.exception} TYPE_TEXT " + barcode.getRawValue());
                String type = String.valueOf(barcode.getValueType());
                String content = barcode.getRawValue();
                Log.d("handelInsertCode","TYPE: " + type);
                Log.d("handelInsertCode","VALUE: " + content);
                CodeDTO codeDTO = new CodeDTO(type,content);
                CodeDatabase.getInstance(this).codeDAO().insertCode(codeDTO);
                scanResult(codeDTO);
                break;
            } case  Barcode.TYPE_WIFI: {
                Log.e("handelInsertCode", "handelInsertCode2: ${result.exception} TYPE_WIFI " + barcode.getRawValue());
                String type = String.valueOf(barcode.getValueType());
                String content = barcode.getRawValue();
                CodeDTO codeDTO = new CodeDTO(type,content);
                CodeDatabase.getInstance(this).codeDAO().insertCode(codeDTO);
                scanResult(codeDTO);
                break;
            }case  Barcode.TYPE_EMAIL: {
                Log.e("handelInsertCode", "handelInsertCode2: ${result.exception} TYPE_EMAIL " + barcode.getRawValue());
                String type = String.valueOf(barcode.getValueType());
                String content = barcode.getRawValue();
                CodeDTO codeDTO = new CodeDTO(type,content);
                CodeDatabase.getInstance(this).codeDAO().insertCode(codeDTO);
                scanResult(codeDTO);
                break;
            }
            case  Barcode.TYPE_CONTACT_INFO: {
                Log.e("handelInsertCode", "handelInsertCode2: ${result.exception} TYPE_CONTACT_INFO " + barcode.getRawValue());
                String type = String.valueOf(barcode.getValueType());
                String content = barcode.getRawValue();
                Log.d("handelInsertCode","TYPE: " + type);
                Log.d("handelInsertCode","VALUE: " + content);
                CodeDTO codeDTO = new CodeDTO(type,content);
                CodeDatabase.getInstance(this).codeDAO().insertCode(codeDTO);
                scanResult(codeDTO);
                break;
            }
            case  Barcode.TYPE_GEO: {
                Log.e("handelInsertCode", "handelInsertCode2: ${result.exception} TYPE_GEO " + barcode.getRawValue());
                String type = String.valueOf(barcode.getValueType());
                String content = barcode.getRawValue();
                Log.d("handelInsertCode","TYPE: " + type);
                Log.d("handelInsertCode","VALUE: " + content);
                CodeDTO codeDTO = new CodeDTO(type,content);
                CodeDatabase.getInstance(this).codeDAO().insertCode(codeDTO);
                scanResult(codeDTO);
                break;
            }
            case  Barcode.TYPE_PRODUCT:{
                Log.e("handelInsertCode", "handelInsertCode2: ${result.exception} TYPE_PRODUCT "+ barcode.getRawValue());
                String type = String.valueOf(barcode.getValueType());
                String content = barcode.getRawValue();
                Log.d("handelInsertCode","TYPE: " + type);
                Log.d("handelInsertCode","VALUE: " + content);
                CodeDTO codeDTO = new CodeDTO(type,content);
                CodeDatabase.getInstance(this).codeDAO().insertCode(codeDTO);
                scanResult(codeDTO);
                break;
            }
            case  Barcode.TYPE_PHONE:{
                Log.e("handelInsertCode", "handelInsertCode2: ${result.exception} TYPE_PHONE ");
                String type = String.valueOf(barcode.getValueType());
                String content = barcode.getRawValue();
                Log.d("handelInsertCode","TYPE: " + type);
                Log.d("handelInsertCode","VALUE: " + content);
                CodeDTO codeDTO = new CodeDTO(type,content);
                CodeDatabase.getInstance(this).codeDAO().insertCode(codeDTO);
                scanResult(codeDTO);
                break;
            }
            case  Barcode.TYPE_ISBN:{
                Log.e("handelInsertCode", "handelInsertCode2: ${result.exception} TYPE_ISBN ");
                String type = String.valueOf(barcode.getValueType());
                String content = barcode.getRawValue();
                Log.d("handelInsertCode","TYPE: " + type);
                Log.d("handelInsertCode","VALUE: " + content);
                CodeDTO codeDTO = new CodeDTO(type,content);
                CodeDatabase.getInstance(this).codeDAO().insertCode(codeDTO);
                scanResult(codeDTO);
                break;
            }
        }
        return "";
    }

    private void scanResult(CodeDTO codeDTO){
        Log.d("handelInsertCode","isINSERT: "+ isInsert);
        if(isInsert == false) {
            isInsert = true;
            Intent intent = new Intent(PreviewActivity.this, DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("QRCODE", codeDTO);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}