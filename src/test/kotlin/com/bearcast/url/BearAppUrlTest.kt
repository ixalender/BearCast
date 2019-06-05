package com.bearcast.url

import com.bearcast.ConfigRepo
import com.bearcast.model.BearAppNote
import org.apache.http.client.utils.URLEncodedUtils
import org.junit.Test
import java.net.URI
import java.nio.charset.Charset
import kotlin.test.assertEquals

class BearAppUrlTest {
    @Test
    fun `test url generation`() {
        val testText = """
            def test():
                pass
        """.trimIndent()
        val tags = listOf("test-tag", "second-tag")
        val title = "Test title"

        val bearAppNote = BearAppNote(title, testText, tags)
        val url = BearAppUrl(ConfigRepo.load().bear.createUrl).forNote(bearAppNote)

        val parsedUrl = URLEncodedUtils.parse(URI(url), Charset.defaultCharset())

        with(URI(url)) {
            assertEquals("x-callback-url", host)
            assertEquals("/create", path)
            assertEquals(
                title,
                parsedUrl.find { pair -> pair.name == "title" }?.value
            )
            assertEquals(
                testText,
                parsedUrl.find { pair -> pair.name == "text" }?.value
            )
            assertEquals(
                tags.joinToString(","),
                parsedUrl.find { pair -> pair.name == "tags" }?.value
            )
        }
    }
}