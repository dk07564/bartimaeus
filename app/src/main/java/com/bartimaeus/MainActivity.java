package com.bartimaeus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private IntentIntegrator qrScan;
    TextView scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scan=findViewById(R.id.textView);

        qrScan= new IntentIntegrator(this);

        qrScan.initiateScan();
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){

               IntentResult result=IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

               scan.setText(result.getContents());
       }
    }


