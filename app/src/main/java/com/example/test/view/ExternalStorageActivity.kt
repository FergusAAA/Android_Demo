package com.example.test.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.test.R
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception
import java.nio.charset.StandardCharsets

class ExternalStorageActivity : AppCompatActivity(), OnClickListener {
    companion object {
        private const val TAG: String = "ExternalStorageActivity"
    }

    private lateinit var mFileName: TextView
    private lateinit var mFileContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exteral_storage)
        mFileName = findViewById(R.id.file_name)
        mFileContent = findViewById(R.id.file_content)
        val btn1: TextView = findViewById(R.id.save_1)
        val btn2: TextView = findViewById(R.id.save_2)
        val btn3: TextView = findViewById(R.id.save_3)
        val btn4: TextView = findViewById(R.id.save_4)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)

        requestPermission()
    }

    /**
     * 保存文件
     *
     * @param isInPackageName 是否是保存在应用目录下
     * @throws Exception
     */
    private fun save(isInPackageName: Boolean) {
        //判断SD卡是否装载
        if (!TextUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "SD卡没有挂载", Toast.LENGTH_SHORT).show()
            return
        }
        val fileName = mFileName.text.toString()
        val fileContent = mFileContent.text.toString()
        val fileDir: String
        if (isInPackageName) {
            fileDir = getExternalFilesDir(null)?.absolutePath + "/" + fileName
            Log.w(TAG, "save: fileDir:　$fileDir")
        } else {
            //自己建文件件
//            fileDir = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/xxx/";
//             File file = new File(fileDir);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            fileDir = file.getAbsolutePath() + fileName_string;
            fileDir = Environment.getExternalStorageDirectory().absolutePath + "/" + fileName
            Log.w(TAG, "save: fileDir $fileDir")
        }
        val fileOutputStream = FileOutputStream(fileDir)
        fileOutputStream.write(fileContent.toByteArray(StandardCharsets.UTF_8))
        fileOutputStream.close()
        Toast.makeText(this, "存储成功", Toast.LENGTH_SHORT).show()
    }

    private fun requestPermission() {
        //动态申请权限
        val writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val readPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }
    }

    /**
     * @param isInPackageName 是否从应用目录下读取
     *                          true:是
     *                          false:否
     */
    private fun read(isInPackageName: Boolean) {
        //SD卡是否装载
        if (!TextUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)) {
            return
        }
        val fileName = mFileName.text.toString()
        val fileContent: String
        //拿到文件路径
        val fileDir = if (isInPackageName) {
            getExternalFilesDir(null)?.absolutePath + "/" + fileName
        } else {
            Environment.getExternalStorageDirectory().absolutePath + "/" + fileName
        }
        val fileInputStream:FileInputStream
        try {
            fileInputStream = FileInputStream(fileDir)
        } catch (e: Exception) {
            Toast.makeText(this, "没有找到文件", Toast.LENGTH_SHORT).show()
            return
        }
        val byteArrayOutputStream = ByteArrayOutputStream()
        val bytes = ByteArray(1024)
        var len = fileInputStream.read(bytes)
        while (len != -1) {
            byteArrayOutputStream.write(bytes, 0, len)
            len = fileInputStream.read(bytes)
        }
        fileContent = byteArrayOutputStream.toString()
        mFileContent.text = fileContent
        Toast.makeText(this, "读取成功", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.save_1 -> save(true)
            R.id.save_2 -> read(true)
            R.id.save_3 -> save(false)
            R.id.save_4 -> read(false)
        }
    }
}