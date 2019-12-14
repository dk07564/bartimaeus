package com.bartimaeus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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

        //MainActivity에서 값을 전달 받음
        intent=getIntent();
        barCodeNumber=intent.getStringExtra("barCodeNumber");


        //TTS기능 처리
        tts= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                tts.setLanguage(Locale.KOREA);

//                if(barCodeNumber.equals("8801094502669")){
//                    scan.setText(barCode);
//                    tts.speak(barCode,TextToSpeech.QUEUE_FLUSH, null);
//                }else{
//                    intent.putExtra("barCodeNumber", barCodeNumber);
//                    scan.setText(barCodeNumber);
//                }
                switch (barCodeNumber){
                    case "8801062893546":
                        barCode="롯데 ABC 초코 쿠키\n코코아분말 5%, 과자 43%, 265칼로리.\n설탕이 들어가 단 맛이 강한 제품이며, 우유, 대두, 밀, 달걀로 인한 알레르기가 유발될 수 있는 제품입니다.";
                        scan.setText(barCode);
                        tts.speak(barCode,TextToSpeech.QUEUE_FLUSH, null);
                        break;

                    case "8801062009374":
                        barCode="롯데 씨리얼 초코\n준초콜릿 54%, 귀리분말 30%, 215칼로리.\n설탕이 들어가 단 맛이 강한 제품이며, 우유, 대두, 밀, 달걀로 인한 알레르기가 유발될 수 있는 제품입니다.";
                        scan.setText(barCode);
                        tts.speak(barCode,TextToSpeech.QUEUE_FLUSH, null);
                        break;

                    case "8801155731632":
                        barCode="동원 덴마크 딸기딸기 우유\n탈지분유 미국산 6%, 유크림 5%, 딸기 농축액 1%, 220칼로리.\n설탕이 들어가 단 맛이 강한 제품이며, 우유로 인한 알레르기가 유발될 수 있는 제품입니다.";
                        scan.setText(barCode);
                        tts.speak(barCode,TextToSpeech.QUEUE_FLUSH, null);
                        break;

                    case "8801155731625":
                        barCode="동원 덴마크 초코초코 우유\n원유 국산 200%, 혼합분유 네덜란드산 5%, 유크림 5%, 코코아분말 싱가포르산 1.7%, 330칼로리.\n설탕이 들어가 단 맛이 강한 제품이며, 우유로 인한 알레르기가 유발될 수 있는 제품입니다.";
                        scan.setText(barCode);
                        tts.speak(barCode,TextToSpeech.QUEUE_FLUSH, null);
                        break;

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
