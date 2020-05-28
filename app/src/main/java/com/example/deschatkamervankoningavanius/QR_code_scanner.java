package com.example.deschatkamervankoningavanius;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;

public class QR_code_scanner extends AppCompatActivity {

    SurfaceView surfaceView;
    TextView displayQRCodeValue;
    private CameraSource cameraSource;
    String qrCodeValue;
    private ArrayList<String> correctValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_scanner);

        this.displayQRCodeValue = findViewById(R.id.displayQRCodeValue);
        this.surfaceView = findViewById(R.id.surfaceView);

        this.qrCodeValue = "";
        this.correctValues = new ArrayList<>();
        //maybe we can fill this array with a REST API
        this.correctValues.add("Avanius");
    }


    private void initialiseDetectorsAndSources() {
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(QR_code_scanner.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(QR_code_scanner.this, new
                                String[]{Manifest.permission.CAMERA}, 201);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    displayQRCodeValue.post(new Runnable() {
                        @Override
                        public void run() {
                            qrCodeValue = barcodes.valueAt(0).displayValue;
                            displayQRCodeValue.setText(qrCodeValue);

                            //todo check if the value is in the array with valid values
                            if (correctValues.contains(qrCodeValue)) {
                                //you could use this method for this
                                difficultySelected();
                            }

                        }
                    });
                }
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();
    }

    private void difficultySelected() {
        Intent intent = new Intent(this, NavActivityFragmentBase.class);
        intent.putExtra("Difficulty", qrCodeValue);
        startActivity(intent);
    }


    public void onButtonChooseDifficultyPressed(View view) {
        difficultySelected();
    }
}
