package com.bearcast

import org.junit.Test
import kotlin.test.assertEquals

class CodeTest {
    @Test fun `test cleaned simple`() {
        val dirtyCode: String = """
            fun test(s: String) {
                println(s)
            }
            """

        val cleanerCode: String =
            "fun test(s: String) {\n" +
            "    println(s)\n" +
            "}"

        val code = Code(dirtyCode)

        assertEquals(cleanerCode, code.cleaned)
    }

    @Test fun `test cleaned`() {
        val dirtyCode: String = """
    fun test(s: String) {
                println(s)
            }
            """

        val cleanerCode: String =
            "fun test(s: String) {\n" +
            "            println(s)\n" +
            "        }"

        val code = Code(dirtyCode)

        assertEquals(cleanerCode, code.cleaned)
    }
}