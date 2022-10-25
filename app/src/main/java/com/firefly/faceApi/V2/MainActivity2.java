package com.firefly.faceApi.V2;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        startActivity(new Intent(this,MainActivity.class));

        return super.onKeyDown(keyCode, event);
    }
}