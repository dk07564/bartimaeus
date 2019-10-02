package com.bartimaeus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private IntentIntegrator barCodeScan;
    private Intent intent;

    String barCodeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBarCodeScan();
    }

    public void onResume(){
        super.onResume();
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        barCodeNumber=(String.valueOf(result.getContents()));

        intent = new Intent(this, Information.class);
        intent.putExtra("barCodeNumber", barCodeNumber);
        startActivity(intent);
    }

    public  void setBarCodeScan(){
        barCodeScan= new IntentIntegrator(this);
        barCodeScan.setCaptureActivity(AnyOrientationCaptureActivity.class);
        barCodeScan.setOrientationLocked(false);
        barCodeScan.initiateScan();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finishAffinity();
    }
}



