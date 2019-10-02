package com.bartimaeus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

public class Information extends AppCompatActivity {
    private TextToSpeech tts;
    private Intent intent;
    TextView scan;
    String barCode;
    String barCodeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        scan=findViewById(R.id.textView);

        intent=getIntent();
        barCodeNumber=intent.getStringExtra("barCodeNumber");

        barCode="맥심\n티오피\n스위트 아메리카노";

        tts= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                tts.setLanguage(Locale.KOREA);

                if(barCodeNumber.equals("8801037057843")){
                    scan.setText(barCode);
                    tts.speak(barCode,TextToSpeech.QUEUE_FLUSH, null);
                }else{
                    intent.putExtra("barCodeNumber", barCodeNumber);
                    scan.setText(barCodeNumber);
                }
            }
        });





    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
