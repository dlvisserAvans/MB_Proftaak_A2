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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.deschatkamervankoningavanius.Video.VideoActivity;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.HashMap;

public class QR_code_scanner extends AppCompatActivity {


    //here we get the camera feed from
    private CameraSource cameraSource;
    //here we display the camera feed
    private SurfaceView surfaceView;
    //here we save the string from the qr-code
    private String qrCodeValue;
    //here we display the qr-code value
    private TextView displayQRCodeValue;
    //hash map with all correct values
    private HashMap<String, Difficulty> correctValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_scanner);

        //find the views and set them as attributes
        this.displayQRCodeValue = findViewById(R.id.displayQRCodeValue);
        this.surfaceView = findViewById(R.id.surfaceView);

        //initialise the rest of the attributes
        this.qrCodeValue = "";
        this.correctValues = new HashMap<>();

        this.correctValues.put("Avanius", Difficulty.Hard);
        this.correctValues.put("EsstelingA2Easy", Difficulty.Easy);
        this.correctValues.put("EsstelingA2Medium", Difficulty.Medium);
        this.correctValues.put("EsstelingA2Hard", Difficulty.Hard);
    }

    /**
     * this function initialises the following things
     * -barcodeDetector
     * -cameraSource
     * -surfaceView
     */
    private void initialiseDetectorsAndSources() {
        //make a barcode detector and set its mode to qr-codes
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        //make a camera source and pass it the barcode detector so it knows what it is searching for
        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        //add a callback so the display is updated real time
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

            //if we quit we must close the source so they don't keep existing
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        //here we declare what we want to do if we detect something with the barcode detector
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                //the barcode detector returns a SparseArray of barcode's
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                //we check if there are any detected
                if (barcodes.size() != 0) {
                    //make a thread so it updates the display
                    displayQRCodeValue.post(new Runnable() {
                        @Override
                        public void run() {
                            //we only use the first one it scans
                            qrCodeValue = barcodes.valueAt(0).displayValue;
                            //set the value to the display in de upper left corner
                            displayQRCodeValue.setText(qrCodeValue);

                            //check if the value is in the array with valid values
                            if (correctValues.containsKey(qrCodeValue)) {
                                //it is so we can proceed
                                difficultySelected(correctValues.get(qrCodeValue));
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
        //make sure there aren't unnecessary things running
        cameraSource.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //because we released it we have to re-initiate everything
        initialiseDetectorsAndSources();
    }

    /**
     * with this function we start the home page
     * we pass the barcode value to it via the intent
     *
     * @param difficulty determent how many quests have to be done
     */
    private void difficultySelected(Difficulty difficulty) {
        //we create a intent to go to the home page
        Intent intent = new Intent(this, NavFragmentBaseActivity.class);
        //add the value from the barcode to the intent
        intent.putExtra("Difficulty", difficulty);

        //start the intent
        startActivity(intent);
    }

    /**
     * with this function we go back to the start screen
     */
    public void onButtonBackClicked(View view) {
        //make an intent to transition back to the start
        Intent intent = new Intent(this, ScanscreenActivity.class);
        //start the intent
        startActivity(intent);
    }
}
