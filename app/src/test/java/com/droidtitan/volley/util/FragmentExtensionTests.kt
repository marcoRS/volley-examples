package com.droidtitan.volley.util

import org.junit.Test
import kotlin.test.assertEquals


public class FragmentExtensiontests {

    @Test fun testFirstToUpperCase() {
        val cases = arrayOf("volley examples", "m", " ", "Test")
        val expected = arrayOf("Volley examples", "M", " ", "Test")

        for (i in 0..cases.size() - 1) {
            assertEquals(expected.get(i), cases.get(i).firstToUpperCase())
        }
    }
}