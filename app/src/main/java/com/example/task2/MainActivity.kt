package com.example.task2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.CheckBox
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class MainActivity : ComponentActivity() {

    // Khai báo các View
    private lateinit var imgAvatar: ImageView
    private lateinit var txtTitle: TextView
    private lateinit var edtName: EditText
    private lateinit var btnSave: Button
    private lateinit var chkStudent: CheckBox
    private lateinit var swStatus: Switch



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Gắn file XML
        setContentView(R.layout.layout)
        // Ánh xạ View từ XML
        imgAvatar = findViewById(R.id.imgAvatar)
        txtTitle = findViewById(R.id.txtTitle)
        edtName = findViewById(R.id.edtName)
        btnSave = findViewById(R.id.btnSave)
        chkStudent = findViewById(R.id.chkStudent)
        swStatus = findViewById(R.id.swStatus)

        // 1. onClick
        // Khi bấm nút "Lưu"
        btnSave.setOnClickListener {
            val name = edtName.text.toString()

            if (name.isBlank()) {
                Toast.makeText(this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show()
            } else {
                txtTitle.text = "Xin chào $name"
            }
        }
        // 2. onLongClick
        // Giữ nút "Lưu"
        btnSave.setOnLongClickListener {
            Toast.makeText(this, "Bạn đang giữ nút Lưu", Toast.LENGTH_SHORT).show()
            true
        }
        // 3. onTouch
        // Chạm vào ImageView
        imgAvatar.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    txtTitle.text = "ACTION_DOWN"
                }
                MotionEvent.ACTION_MOVE -> {
                    txtTitle.text = "ACTION_MOVE"
                }
                MotionEvent.ACTION_UP -> {
                    txtTitle.text = "ACTION_UP"
                }
            }
            true
        }
        // 4. Click ImageView
        imgAvatar.setOnClickListener {
            Toast.makeText(this, "Bạn vừa click vào ImageView", Toast.LENGTH_SHORT).show()
        }

        // 5. TextWatcher
        // Theo dõi khi nhập EditText
        edtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Chạy trước khi nội dung thay đổi
            }
            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                // Chạy khi người dùng đang nhập
                txtTitle.text = s
            }

            override fun afterTextChanged(s: Editable?) {
                // Chạy sau khi nhập xong
            }
        })
        // 6. CheckBox
        chkStudent.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                Toast.makeText(this, "Đã chọn: Là sinh viên", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Đã bỏ chọn", Toast.LENGTH_SHORT).show()
            }
        }
        // 7. Switch
        swStatus.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                txtTitle.text = "Trạng thái: Online"
            } else {
                txtTitle.text = "Trạng thái: Offline"
            }
        }
    }
}