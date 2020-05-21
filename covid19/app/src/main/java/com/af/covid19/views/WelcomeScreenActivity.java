package com.af.covid19.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.af.covid19.R;
import java.util.ArrayList;
import java.util.Random;

public class WelcomeScreenActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        TextView tips = findViewById(R.id.tips);
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(0,"STAY AT HOME");
        arrayList.add(1,"WASH HANDING");
        arrayList.add(2,"Dispose of tissue after use");
        arrayList.add(3,"Avoid touching eyes , node and mouth with unwashed hands");
        arrayList.add(4,"Avoid Contact with sick people");
        arrayList.add(5,"Avoid Shaking hands and hugging");

        Random random = new Random();
        tips.setText(arrayList.get(random.nextInt(arrayList.size()))+"");

        new Handler().postDelayed(() -> startActivity(new Intent(WelcomeScreenActivity.this, MainActivity.class)),4000);

    }

    @Override
    public void onStop(){
        super.onStop();
        finish();
    }

}
