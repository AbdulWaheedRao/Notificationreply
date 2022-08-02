package com.example.myreply;

import androidx.appcompat.app.AppCompatActivity;

import android.app.RemoteInput;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
TextView tvView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvView=findViewById(R.id.tvView);
        Intent intent=getIntent();

        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
           CharSequence string=remoteInput.getCharSequence(MainActivity.KEY_TEXT_REPLY);
           tvView.setText(string);
        }
    }
}