package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view){
        int level = Integer.valueOf(((TextView)view).getText().toString());
        Intent intent = new Intent(getApplicationContext(),Exercise.class);
        intent.putExtra("level", level);
            startActivity(intent);
        }
}