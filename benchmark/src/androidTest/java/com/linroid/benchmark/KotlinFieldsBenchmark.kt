package com.linroid.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by linroid on 12/18/20.
 */
@RunWith(AndroidJUnit4::class)
class KotlinFieldsBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun jvmFieldGetter() {
        val house = House()
        benchmarkRule.measureRepeated {
            repeat(REPEAT_COUNT) { house.jvmField }
        }
    }

    @Test
    fun kotlinGetter() {
        val house = House()
        benchmarkRule.measureRepeated {
            repeat(REPEAT_COUNT) { house.kotlinField }
        }
    }

    @Test
    fun jvmFieldSetter() {
        val house = House()
        benchmarkRule.measureRepeated {
            repeat(REPEAT_COUNT) { house.jvmField = 0 }
        }
    }

    @Test
    fun kotlinSetter() {
        val house = House()
        benchmarkRule.measureRepeated {
            repeat(REPEAT_COUNT) { house.kotlinField = 0 }
        }
    }

    @Test
    fun accessLateinitField() {
        val house = House()
        house.lateField = house
        benchmarkRule.measureRepeated {
            repeat(REPEAT_COUNT) {
                house.lateField
            }
        }
    }

    @Test
    fun accessLazyField() {
        val house = House()
        benchmarkRule.measureRepeated {
            repeat(REPEAT_COUNT) {
                house.lazyField
            }
        }
    }

    private class House {
        @JvmField
        var jvmField: Int = 0

        var kotlinField: Int = 0

        lateinit var lateField: Any

        val lazyField by lazy {
            0
        }
    }

    companion object {
        private const val REPEAT_COUNT = 1000000
    }
}