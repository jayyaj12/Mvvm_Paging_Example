package com.example.mvvmexample

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {

    class CalcUtil {
        fun sum(a: Int, b: Int) = a + b
        fun minus(a: Int, b: Int) = a - b
    }

    private lateinit var calcUtil: CalcUtil

    @Before fun initialize() {
        println("start")
        calcUtil = CalcUtil()
    }

    @Test fun plus() {
        val sum = calcUtil.sum(10, 10)
        assertEquals(sum, 20)
    }

    @After fun finish() = println("finish")

}