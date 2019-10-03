package com.bartimaeus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private IntentIntegrator barCodeScan = new IntentIntegrator(this);
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

    //바코드가 스캔되었을 때 결과값을 넘겨줌
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

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

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finishAffinity();
    }
}



