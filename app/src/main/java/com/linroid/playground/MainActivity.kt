package com.linroid.playground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linroid.playground.binder.AidlActivity
import kotlinx.android.synthetic.main.main_activity.*
import kotlin.reflect.KClass

/**
 * Created by linroid on 11/18/20.
 */
class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aidl.setOnClickListener {
            startActivity(AidlActivity::class)
        }
        systemBars.setOnClickListener {
            startActivity(SystemBarsActivity::class)
        }
    }

    private fun startActivity(kClass: KClass<out AppCompatActivity>) {
        startActivity(Intent(this, kClass.java))
    }
}