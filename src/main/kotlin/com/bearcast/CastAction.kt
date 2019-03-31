package com.bearcast

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import org.apache.http.client.utils.URIBuilder

class CastAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        if (!System.getProperty("os.name").startsWith("Mac OS")) {
            return
        }

        val ed = e.getData(CommonDataKeys.EDITOR) ?: return
        val file = e.getData(CommonDataKeys.PSI_FILE) ?: return
        val project = e.getData(CommonDataKeys.PROJECT) ?: return

        val language = file.language.displayName.toLowerCase()
        val textTemplate = """```$language${System.lineSeparator()}%s```"""

        val query = URIBuilder(ConfigRepo.load().bear.createUrl).apply {
            addParameter("title", "${project.name} - ${file.name}")
            addParameter("text", textTemplate.format(ed.selectionModel.selectedText))
            addParameter("tags", listOf(language).joinToString(","))
            build()
        }

        BrowserUtil.browse(query.toString().replace("+", "%20"))
    }
}