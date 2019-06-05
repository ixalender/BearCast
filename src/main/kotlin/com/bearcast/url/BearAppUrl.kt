package com.bearcast.url

import com.bearcast.model.BearAppNote
import org.apache.http.client.utils.URIBuilder

class BearAppUrl(private var baseUri: String) : AppUrl {

    override fun forNote(bearAppNote: BearAppNote): String {
        return URIBuilder(baseUri).let{
            it.addParameter("title", bearAppNote.title)
            it.addParameter("text", bearAppNote.text)
            it.addParameter("tags", bearAppNote.tags.joinToString(","))

            it.build().toString().replace("+", URL_SPACE_SIGN)
        }
    }

    companion object {
        private const val URL_SPACE_SIGN = "%20"
    }
}