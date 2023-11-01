package com.example.scannerqr.UI.scan;
import static androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.mlkit.vision.MlKitAnalyzer;
import androidx.camera.view.LifecycleCameraController;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.scannerqr.R;
import com.example.scannerqr.DATABASE.CodeDatabase;
import com.example.scannerqr.databinding.ActivityScanBinding;
import com.example.scannerqr.DTO.CodeDTO;
import com.example.scannerqr.UI.DETAIL.DetailActivity;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Collections;
import java.util.List;

public class ScanActivity extends AppCompatActivity {

    Uri selectedImageUri;
    int cameraFacing = CameraSelector.LENS_FACING_BACK;
    int SELECT_PICTURE = 123;
    private LifecycleCameraController cameraController = null;
    private BarcodeScannerOptions barcodeScannerOptions;
    private BarcodeScanner barcodeScanner;
    private ActivityScanBinding binding;
    private boolean isOpenFlash = false;
    public static boolean isInsert = false;
    private ScanOptions scanOptions;

    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result) {
                startCamera(cameraFacing);
            }
        }
    });

    @Override
    protected void onResume() {
        super.onResume();
        isInsert = false;
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityScanBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
//            capture = findViewById(R.id.capture);
//            toggleFlash = findViewById(R.id.toggleFlash);
//            flipCamera = findViewById(R.id.flipCamera);

            if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                activityResultLauncher.launch(Manifest.permission.CAMERA);
            } else {
                startCamera(cameraFacing);
            }

            binding.ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            binding.layoutAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageChooser();
                }
            });

        }
    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void startCamera(int cameraFacing) {
        // Tạo một đối tượng cameraController sử dụng LifecycleCameraController và truyền vào Context.
        LifecycleCameraController cameraController = new LifecycleCameraController(this);
        // Tạo tùy chọn cấu hình cho quét mã vạch, ở đây chỉ chọn định dạng QR Code.
        BarcodeScannerOptions options = new BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_EAN_13,Barcode.FORMAT_QR_CODE,Barcode.FORMAT_ALL_FORMATS)
                .build();
        // Gọi phương thức configBarcodeScanner để cấu hình cameraController với tùy chọn và trình phân tích hình ảnh.
        configBarcodeScanner(cameraController, options);
    }

    private void configBarcodeScanner(LifecycleCameraController cameraController, BarcodeScannerOptions options) {
        // Tạo một đối tượng BarcodeScanner sử dụng tùy chọn cấu hình được truyền vào.
        barcodeScanner = BarcodeScanning.getClient(options);
        binding.layoutFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpenFlash){
                    cameraController.enableTorch(true);
                    binding.layoutFlash.setBackgroundResource(R.drawable.layout_flashon);
                }else {
                    cameraController.enableTorch(false);
                    binding.layoutFlash.setBackgroundResource(R.drawable.layout_flashoff);
                }
                isOpenFlash= !isOpenFlash;
            }
        });
        // Thiết lập một trình phân tích hình ảnh cho cameraController với các đối tượng liên quan đến quét mã vạch.
        cameraController.setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(this), // Executor chạy trên luồng chính
                new MlKitAnalyzer(
                        Collections.singletonList(barcodeScanner), // Danh sách trình quét mã vạch sử dụng
                        COORDINATE_SYSTEM_VIEW_REFERENCED, // Hệ toạ độ được sử dụng
                        ContextCompat.getMainExecutor(this), // Executor chạy trên luồng chính
                        result -> { // Lambda expression xử lý kết quả từ trình phân tích
                            List<Barcode> barcodeResults = result != null ? result.getValue(barcodeScanner) : null;
                            if (barcodeResults == null || barcodeResults.size() == 0 || barcodeResults.get(0) == null) {
                                // Nếu không có kết quả hoặc kết quả đầu tiên là null, xóa overlay và thoát.
                                binding.cameraPreview.getOverlay().clear();
                                return;
                            }
                            Log.e("configBarcodeScanner", "configBarcodeScanner: " + barcodeResults);
                            handelQrCode(barcodeResults.get(0));
                            binding.cameraPreview.getOverlay().clear();
                        }
                )
        );
        // Kết nối cameraController với vòng đời của fragment/hoạt động.
        cameraController.bindToLifecycle(this);
        // Thiết lập controller của previewView để sử dụng cameraController.
        binding.cameraPreview.setController(cameraController);
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
                } case  Barcode.TYPE_EMAIL: {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                Intent intent = new Intent(ScanActivity.this,PreviewActivity.class);
                String path = selectedImageUri.toString();
                intent.putExtra("path",path);
                startActivity(intent);
            }
        }
    }
    private void scanResult(CodeDTO codeDTO){
        Log.d("handelInsertCode","isINSERT: "+ isInsert);
        if(isInsert == false) {
                isInsert = true;
                Intent intent = new Intent(ScanActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("QRCODE", codeDTO);
                intent.putExtras(bundle);
                startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
