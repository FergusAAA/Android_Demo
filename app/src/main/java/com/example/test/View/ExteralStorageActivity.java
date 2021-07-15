package com.example.test.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.test.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class ExteralStorageActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ExteralStorageActivity";

    private EditText fileName;
    private EditText fileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exteral_storage);
        fileName = findViewById(R.id.file_name);
        fileContent = findViewById(R.id.file_content);
        TextView btn1 = findViewById(R.id.save_1);
        TextView btn2 = findViewById(R.id.save_2);
        TextView btn3 = findViewById(R.id.save_3);
        TextView btn4 = findViewById(R.id.save_4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        requestPermission();
    }

    /**
     * 保存文件
     *
     * @param isInPackageName 是否是保存在应用目录下
     * @throws Exception
     */
    private void save(boolean isInPackageName) throws Exception {
        //判断SD卡是否装载
        if (!TextUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "SD卡没有挂载", Toast.LENGTH_SHORT).show();
            return;
        }
        String fileName_string = fileName.getText().toString();
        String fileConten_string = fileContent.getText().toString();
        String fileDir;
        if (isInPackageName) {
            fileDir = this.getExternalFilesDir(null).getAbsoluteFile() + "/" + fileName_string;
            Log.w(TAG, "save: fileDir:　" + fileDir);
        } else {
            //自己建文件件
//            fileDir = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/xxx/";
//             File file = new File(fileDir);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            fileDir = file.getAbsolutePath() + fileName_string;
            fileDir = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/" + fileName_string;
            Log.w(TAG, "save: fileDir:　" + fileDir);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(fileDir);
        fileOutputStream.write(fileConten_string.getBytes(StandardCharsets.UTF_8));
        fileOutputStream.close();
        Toast.makeText(this, "存储成功", Toast.LENGTH_SHORT).show();
    }

    private void requestPermission() {
        //动态申请权限
        int WRITE_PERMISSION = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int READ_PERMISSION = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (WRITE_PERMISSION != PackageManager.PERMISSION_GRANTED || READ_PERMISSION != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    private void read(boolean isInPackageName) throws Exception {
        //SD卡是否装载
        if (!TextUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)) {
            return;
        }
        String fileName_string = fileName.getText().toString();
        String fileContent_string;
        String fileDir;
        //拿到文件路径
        if (isInPackageName) {
            fileDir = this.getExternalFilesDir(null).getAbsolutePath() + "/" + fileName_string;
        } else {
            fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName_string;
        }
        FileInputStream fileInputStream = new FileInputStream(fileDir);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        if ((len = fileInputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, len);
        }
        fileContent_string = byteArrayOutputStream.toString();

        this.fileContent.setText(fileContent_string);
        Toast.makeText(this, "读取成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.save_1:
                    save(true);
                    break;
                case R.id.save_2:
                    read(true);
                    break;
                case R.id.save_3:
                    save(false);
                    break;
                case R.id.save_4:
                    read(false);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}