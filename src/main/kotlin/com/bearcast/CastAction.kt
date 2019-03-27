package com.bearcast

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import org.apache.http.client.utils.URIBuilder

class CastAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val ed = e.getData(CommonDataKeys.EDITOR) ?: return

        val query = URIBuilder(ConfigRepo.load().bear.createUrl).apply {
            addParameter("title", e.getData(CommonDataKeys.VIRTUAL_FILE)?.name)
            addParameter("text", ed.selectionModel.selectedText)
            addParameter("tags", listOf("Tag1").joinToString(","))
            build()
        }

        Runtime.getRuntime().exec("open ${query.toString().replace("+", "%20")}")
    }
}