package com.droidtitan.volley.util

import org.junit.Test
import kotlin.test.assertEquals


public class FragmentExtensiontests {

    @Test fun testFirstToUpperCase() {
        val lowercase = "volley examples"
        assertEquals("Volley examples", lowercase.firstToUpperCase())
    }
}