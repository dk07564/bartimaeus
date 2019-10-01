package com.bartimaeus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private IntentIntegrator qrScan;
    private IntentIntegrator Scan;
    private TextToSpeech tts;
    TextView scan;
    String barCordNumber;
    String barCord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan=findViewById(R.id.textView);

        tts= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                tts.setLanguage(Locale.KOREA);
            }
        });


    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        barCordNumber=(String.valueOf(result.getContents()));

        barCord="맥심\n티오피\n스위트 아메리카노";
        if(barCordNumber.equals("8801037057843")){
            scan.setText(barCord);

            tts.speak(barCord,TextToSpeech.QUEUE_FLUSH, null);
        }else{
            scan.setText(barCordNumber);
        }

        //scan.setText(barCordNumber);
        qrScan= new IntentIntegrator(this);
        qrScan.setCaptureActivity(AnyOrientationCaptureActivity.class);
        qrScan.setOrientationLocked(false);
        qrScan.initiateScan();

    }

}



