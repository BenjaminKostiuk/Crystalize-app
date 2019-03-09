package com.benko.crystalize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class SplashPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
    }
    public void onSplashPageClick(View view) {
        Intent intent = new Intent(this, Questions.class);
        startActivity(intent);
        finish();
    }

}
