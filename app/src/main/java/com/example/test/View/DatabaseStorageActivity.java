package com.example.test.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class DatabaseStorageActivity extends AppCompatActivity {

    private TextView createDbBtn;
    private TextView updateDbBtn;
    private TextView insertBtn;
    private TextView updateBtn;
    private TextView delBtn;
    private TextView queryBtn;
    private TextView testBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_storage);
        initView();
    }

    private void initView() {
        createDbBtn = findViewById(R.id.create_db);
        updateDbBtn = findViewById(R.id.update_db);
        insertBtn = findViewById(R.id.insert);
        updateBtn = findViewById(R.id.update);
        delBtn = findViewById(R.id.delete);
        queryBtn = findViewById(R.id.query);
        testBtn = findViewById(R.id.test_transaction);
    }
}
