package com.bartimaeus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

public class MainActivity extends AppCompatActivity {
    private IntentIntegrator qrScan;
    TextView scan;
    String barCord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scan=findViewById(R.id.textView);
        qrScan= new IntentIntegrator(this);
        qrScan.setCaptureActivity(AnyOrientationCaptureActivity.class);
        qrScan.setOrientationLocked(false);
        qrScan.initiateScan();


    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        barCord=(String.valueOf(result.getContents()));

        scan.setText(barCord);
    }

    }



