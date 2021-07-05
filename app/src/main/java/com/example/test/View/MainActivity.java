package com.example.test.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private TextView btn1;
    private TextView btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
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
            default:
                break;
        }
    }

    private void startActivity(Class targetCls) {
        Intent intent = new Intent(this, targetCls);
        startActivity(intent);
    }
}