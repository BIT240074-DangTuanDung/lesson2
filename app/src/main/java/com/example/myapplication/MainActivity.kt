package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnClickMe.setOnClickListener {
            val userName = binding.etUserName.text?.toString()?.trim() ?: ""
            val mssv = binding.etMssv.text?.toString()?.trim() ?: ""

            var isValid = true

            if (userName.isEmpty()) {
                binding.tilUserName.error = getString(R.string.err_empty_username)
                isValid = false
            } else {
                binding.tilUserName.error = null
            }

            if (mssv.isEmpty()) {
                binding.tilMssv.error = getString(R.string.err_empty_mssv)
                isValid = false
            } else {
                binding.tilMssv.error = null
            }

            if (isValid) {
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("EXTRA_USER_NAME", userName)
                    putExtra("EXTRA_MSSV", mssv)
                }
                startActivity(intent)
            }
        }
    }
}