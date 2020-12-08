package com.linroid.benchmark

import android.os.Looper
import android.os.MessageQueue
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by linroid on 12/8/20.
 */
@RunWith(AndroidJUnit4::class)
class ReflectBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun findClass() {
        benchmarkRule.measureRepeated {
            Class.forName("android.os.MessageQueue")
        }
    }

    @Test
    fun getClassMethods() {
        benchmarkRule.measureRepeated {
            MessageQueue::class.java.methods
        }
    }

    @Test
    fun getClassDeclaredMethods() {
        benchmarkRule.measureRepeated {
            MessageQueue::class.java.declaredMethods
        }
    }

    @Test
    fun getFields() {
        benchmarkRule.measureRepeated {
            MessageQueue::class.java.fields
        }
    }

    @Test
    fun getDeclaredFields() {
        benchmarkRule.measureRepeated {
            MessageQueue::class.java.declaredFields
        }
    }

    @Test
    fun getConstructors() {
        benchmarkRule.measureRepeated {
            MessageQueue::class.java.constructors
        }
    }

    @Test
    fun getAnnotations() {
        benchmarkRule.measureRepeated {
            MessageQueue::class.java.annotations
        }
    }

    @Test
    fun getDeclaredConstructors() {
        benchmarkRule.measureRepeated {
            MessageQueue::class.java.declaredConstructors
        }
    }

    @Test
    fun findField() {
        benchmarkRule.measureRepeated {
            val field = MessageQueue::class.java.getDeclaredField("mMessages")
            field.isAccessible = true
        }
    }

    @Test
    fun getValueFromField() {
        val field = MessageQueue::class.java.getDeclaredField("mMessages")
        field.isAccessible = true
        val queue = Looper.getMainLooper().queue

        benchmarkRule.measureRepeated {
            field.get(queue)
        }
    }
}