package com.example.test.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class InnerFilestorageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView save;
    private TextView read;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innerfilestorage);
        save = findViewById(R.id.btn_1);
        read = findViewById(R.id.btn_2);
        img = findViewById(R.id.img);

        save.setOnClickListener(this);
        read.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btn_1:
                    save();
                    break;
                case R.id.btn_2:
                    read();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存图片
     */
    private void save() throws Exception {
        //得到 assetManager
        AssetManager assetManager = getAssets();
        //得到输入流
        InputStream inputStream = assetManager.open("batman.jpg");
        //得到输出流
        FileOutputStream fileOutputStream = openFileOutput("batman.jpg", MODE_PRIVATE);
        //读取文件
        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        while (len != -1) {
            fileOutputStream.write(bytes, 0, len);
            len = inputStream.read(bytes);
        }
        //关闭流
        inputStream.close();
        fileOutputStream.close();
    }

    /**
     * 从文件中找到图片，并加载
     */
    private void read() {
        //得到 Fils 文件夹的绝对路径
        File absoluteFile = getFilesDir().getAbsoluteFile();
        //图片的绝对路径
        String bitmapDir = absoluteFile + "/batman.jpg";
        //得到bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(bitmapDir);
        //设置资源
        if (bitmap != null) {
            img.setImageBitmap(bitmap);
            img.setVisibility(View.VISIBLE);
        }
    }
}