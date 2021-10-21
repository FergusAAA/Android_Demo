package com.example.test.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private TextView btn1;
    private TextView btn2;
    private TextView btn3;
    private TextView btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                startActivity(TweenActivity.class);
                break;
            case R.id.btn_2:
                startActivity(InnerFilestorageActivity.class);
                break;
            case R.id.btn_3:
                startActivity(ExteralStorageActivity.class);
                break;
            case R.id.btn_4:
                startActivity(DatabaseStorageActivity.class);
                break;
            default:
                break;
        }
    }

    private void startActivity(Class targetCls) {
        Intent intent = new Intent(this, targetCls);
        startActivity(intent);
    }
}