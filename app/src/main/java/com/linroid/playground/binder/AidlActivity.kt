package com.linroid.playground.binder

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.linroid.playground.DaemonInterface
import com.linroid.playground.R
import com.linroid.playground.SomeCallback
import kotlinx.android.synthetic.main.aidl_activity.*
import java.lang.ref.WeakReference

/**
 * Created by linroid on 11/18/20.
 */
class AidlActivity : AppCompatActivity(R.layout.aidl_activity), ServiceConnection {

    private var daemonInterface: DaemonInterface? = null
    private var callbackRef = WeakReference<SomeCallback>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        container.isEnabled = false
        val intent = Intent(this, DaemonService::class.java)
        bindService(intent, this, BIND_AUTO_CREATE)
        ping.setOnClickListener {
            daemonInterface!!.ping(0, 0L, true, 1.0f, 1.0, "Hello World")
        }
        setCallback.setOnClickListener {
            val callback = object : SomeCallback.Stub() {
                override fun pong() {
                    Log.i(TAG, "callback pong called")
                }

                override fun linkToDeath(recipient: IBinder.DeathRecipient, flags: Int) {
                    super.linkToDeath(recipient, flags)
                    Log.w(TAG, "linkToDeath")
                }

                override fun unlinkToDeath(recipient: IBinder.DeathRecipient, flags: Int): Boolean {
                    Log.w(TAG, "unlinkToDeath")
                    return super.unlinkToDeath(recipient, flags)
                }
            }
            callbackRef = WeakReference(callback)
            daemonInterface!!.setCallback(callback)
        }
        releaseCallback.setOnClickListener {
            val gc = triggerGC.isChecked
            daemonInterface!!.releaseCallback(gc)
            if (gc) {
                Runtime.getRuntime().gc()
            }
        }
        checkInstance.setOnClickListener {
            val gc = triggerGC.isChecked
            if (gc) {
                Runtime.getRuntime().gc()
            }
            Toast.makeText(this, "ref.get()=${callbackRef.get()}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.i(TAG, "onServiceConnected")
        daemonInterface = DaemonInterface.Stub.asInterface(service)
        container.isEnabled = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Log.i(TAG, "onServiceDisconnected")
        daemonInterface = null
        container.isEnabled = false
    }

    companion object {
        private const val TAG = "AidlActivity"
    }
}