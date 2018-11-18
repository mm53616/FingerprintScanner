package com.example.mmant.qrnavigation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class OpenScanner extends AppCompatActivity {

    ImageView imageViewOfPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_scanning);
     //   imageViewOfPhoto = (ImageView)findViewById(R.id.imageViewOfPhoto);
    }


    public void scanCode(View view){
        Intent i = new Intent(this, FingerprintScanning.class);
        startActivity(i);
    }

    public void learnAboutScanning(View view){
        Intent i = new Intent(this, OpenInformation.class);
        startActivity(i);
    }


    public void Back(View view)
    {
        finish();
    }
}
