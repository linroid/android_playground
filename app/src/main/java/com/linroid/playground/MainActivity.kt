package com.linroid.playground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linroid.playground.binder.AidlActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aidlBtn.setOnClickListener {
            val intent = Intent(it.context, AidlActivity::class.java)
            startActivity(intent)
        }
    }
}