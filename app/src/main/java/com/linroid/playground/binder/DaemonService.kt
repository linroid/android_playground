package com.linroid.playground.binder

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.linroid.playground.DaemonInterface
import com.linroid.playground.SomeCallback

/**
 * Created by linroid on 11/18/20.
 */
class DaemonService : Service() {

    private var callback: SomeCallback? = null

    private val binder = object : DaemonInterface.Stub() {

        override fun setCallback(callback: SomeCallback?) {
            this@DaemonService.callback = callback
            callback?.pong()
            Log.i(TAG, "setCallback(): $callback")
        }

        override fun releaseCallback(gc : Boolean) {
            callback = null
            if (gc) {
                Runtime.getRuntime().gc()
            }
            Log.i(TAG, "releaseCallback(): gc=$gc")
        }

        override fun ping(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {
            Log.i(TAG, "pong")
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

    override fun onBind(intent: Intent?): IBinder? {
        return binder.asBinder()
    }

    companion object {
        private const val TAG = "DaemonService"
    }
}