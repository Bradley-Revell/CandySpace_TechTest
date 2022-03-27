package com.example.candyspace_tech

import com.google.common.base.CharMatcher.`is`
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AppUnitTest {

    @Test
    fun valueIsGreaterThan_isCorrect(){
        assert(2 > 0)
    }

    @Test
    fun valueIsLessThan_isCorrect(){
        assert(0 < 2)
    }

    @Test
    fun textHasBeenEntered_isCorrect(){
        val text = "text"
        assert(text.count() > 0)
    }

    @Test
    fun textFieldEmpty_isFalse(){
        val text = ""
        assertFalse(text.count() > 0)
    }



}