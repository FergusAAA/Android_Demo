package com.example.test.view

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R
import java.io.FileOutputStream
import java.io.InputStream

class InnerFirestoneActivity : AppCompatActivity(), View.OnClickListener {
    private var save: TextView? = null
    private var read: TextView? = null
    private var img: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_innerfilestorage)
        save = findViewById(R.id.btn_1)
        read = findViewById(R.id.btn_2)
        img = findViewById(R.id.img)

        save?.setOnClickListener(this)
        read?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_1 -> save()
            R.id.btn_2 -> read()
        }
    }

    /**
     * 保存图片
     */
    private fun save(){
        //得到 assetManager
        val assetManager: AssetManager = assets
        //得到输入流
        val inputStream: InputStream = assetManager.open("batman.jpg")
        //得到输出流
        val fileOutputStream: FileOutputStream = openFileOutput("batman.jpg", MODE_PRIVATE)
        //读取文件
        val bytes = byteArrayOf(1024.toByte())
        var len: Int = inputStream.read(bytes)
        while (len != -1) {
            fileOutputStream.write(bytes, 0, len)
            len = inputStream.read(bytes)
        }
        //关闭流
        inputStream.close()
        fileOutputStream.close()
    }

    /**
     * 从文件中找到图片，并加载
     */
    private fun read() {
        //得到 Files 文件夹的绝对路径
        val absoluteFile: String = filesDir.absolutePath
        //图片的绝对路径
        val bitmapDir = "$absoluteFile/batman.jpg"
        //得到bitmap
        val bitmap = BitmapFactory.decodeFile(bitmapDir)
        //设置资源
        if (bitmap != null) {
            img?.setImageBitmap(bitmap)
            img?.visibility = View.VISIBLE
        }
    }
}