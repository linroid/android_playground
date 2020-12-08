package com.linroid.playground

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by linroid on 12/8/20.
 */
class SystemBarsActivity : AppCompatActivity(R.layout.system_bar_activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window?.navigationBarColor = Color.TRANSPARENT
            window?.statusBarColor = Color.TRANSPARENT
        }
    }
}