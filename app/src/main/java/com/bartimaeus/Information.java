package com.bartimaeus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.TextView;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;



public class Information extends AppCompatActivity {
    private TextToSpeech tts;
    private Intent intent;
    TextView scan, tv_name, tv_brand, tv_material, tv_allergy;
    String barCode;
    String barCodeNumber, barCodeNum;
    String name, brand, nation, allergy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        tv_name = findViewById(R.id.name);
        tv_brand = findViewById(R.id.brand);
        tv_material = findViewById(R.id.material);
        tv_allergy = findViewById(R.id.allergy);

        //MainActivity에서 값을 전달 받음
        intent = getIntent();
        barCodeNumber = intent.getStringExtra("barCodeNumber");
        barCodeNumber = "8801111913928";

        getData(barCodeNumber);

    }

    public void getData(String number){
        try {
            AssetManager am = getAssets();
            InputStream inputStream = am.open("Food.xls");

            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
            HSSFWorkbook workbook = new HSSFWorkbook(poifsFileSystem);
            HSSFSheet sheet = workbook.getSheetAt(0);

            int barNum = sheet.getLastRowNum();

            for(int i=1; i<barNum; i++){
                Row row = sheet.getRow(i);
                Row nameRow = sheet.getRow(i);
                Row brandRow = sheet.getRow(i);
                Row nationRow = sheet.getRow(i);
                Row allergyRow = sheet.getRow(i);

                Cell cell = row.getCell(0);
                Cell nameCell = nameRow.getCell(1);
                Cell brandCell = brandRow.getCell(2);
                Cell nationCell = nationRow.getCell(3);
                Cell allergyCell = allergyRow.getCell(4);

                String productNumber = cell.getStringCellValue();
                Log.d("I", String.valueOf(i));
                Log.d("상품", productNumber);

                if(productNumber.equals(number)){
                    name=nameCell.getStringCellValue();
                    brand=brandCell.getStringCellValue();
                    nation=nationCell.getStringCellValue();
                    allergy=allergyCell.getStringCellValue();
                    Log.d("ALLERGY", allergy);

                    tts= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int i) {
                            tts.setLanguage(Locale.KOREA);

                            tv_name.setText("이름: "+name);
                            tv_brand.setText("제조사: "+brand);
                            tv_material.setText("제조 국가:"+nation);
                            tv_allergy.setText("알레르기 정보: "+allergy);

                            barCode ="이름: "+ name + "\n" + "제조사: "+ brand + "\n" + "제조 국가: "+ nation + "\n" + "알레르기 정보: "+ allergy;

                            tts.speak(barCode, TextToSpeech.QUEUE_FLUSH, null);

                        }
                    });

                    break;
                }else{

                }

            }

        } catch (IOException e) {
            Log.d("ERROR", "ERROR");

        }
    }




    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
