package com.bartimaeus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private IntentIntegrator barCodeScan = new IntentIntegrator(this);
    private SensorManager sensorManager;
    private Intent intent;

    String barCodeNumber;

    double  currentX, currentY, preX, preY=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBarCodeScan();
    }

    public void onResume(){
        super.onResume();
    }

    //바코드가 스캔되었을 때 결과값을 넘겨줌
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

//        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//                SensorManager.SENSOR_DELAY_NORMAL);

        barCodeNumber=(String.valueOf(result.getContents()));

        intent = new Intent(this, Information.class);
        intent.putExtra("barCodeNumber", barCodeNumber);
        startActivity(intent);
    }

    //바코드 스캔을 위한 함수
    public  void setBarCodeScan(){
        //barCodeScan= new IntentIntegrator(this);
        barCodeScan.setCaptureActivity(AnyOrientationCaptureActivity.class);
        barCodeScan.setOrientationLocked(false);
        barCodeScan.initiateScan();
    }

//    @Override
//    public  void onAccuracyChanged(Sensor arg0, int arg1){
//
//    }
//
//    @Override
//    public  void onSensorChanged(SensorEvent event){
//        preX=event.values[0];
//        preY=event.values[1];
//
//        if(Math.abs(preX)>Math.abs(currentX)+1 ||
//                Math.abs(preY)>Math.abs(currentY)+1){
//            speed();
//
//            preX = 0;
//            preY = 0;
//            currentX = 0;
//            currentY = 0;
//
//        }
//        currentX=preX;
//        currentY=preY;
//    }
//
//    //속도가 빠름을 TTS로 알려주는 함수
//    public void speed(){
//        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int i) {
//                tts.setLanguage(Locale.KOREAN);
//
//                tts.speak("속도가 빠릅니다", TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });
//    }
}



