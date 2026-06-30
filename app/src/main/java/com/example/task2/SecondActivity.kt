package com.example.task2

import android.os.Bundle
import androidx.activity.ComponentActivity

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //intent
        val name = intent.getStringExtra("name") // lấy dữ liệu trường có key là name
        val age = intent.getIntExtra("age", 0)
        println(name)
        println(age)
        //bundle
        val bundle = intent.extras //là một Bundle chứa tất cả dữ liệu được truyền từ Activity (hoặc Fragment) khác sang Activity hiện tại
        //extras trả về một đối tượng Bundle?
        val name2 = bundle?.getString("name")//?: nếu Nếu bundle khác null thì gọi getString("name"), còn nếu bundle là null thì trả về null
        val age2 = bundle?.getInt("age")
        println(name2)
        println(age2)

    }

}