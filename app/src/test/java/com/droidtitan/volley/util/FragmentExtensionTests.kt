package com.droidtitan.volley.util

import junit.framework.Assert.assertEquals
import org.junit.Test


class FragmentExtensiontests {

  @Test fun testFirstToUpperCase() {
    val cases = arrayOf("volley examples", "m", " ", "Test", "")
    val expected = arrayOf("Volley examples", "M", " ", "Test", "")

    for (i in 0 until cases.size) {
      assertEquals(expected[i], cases[i].firstToUpperCase())
    }
  }
}