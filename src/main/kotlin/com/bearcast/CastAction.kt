package com.bearcast

import com.intellij.ide.BrowserUtil
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import org.apache.http.client.utils.URIBuilder

class CastAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        if (!System.getProperty("os.name").startsWith("Mac OS")) {
            Popup(e.project, NotificationType.WARNING).apply {
                show("Sorry, but using this plugin may have sense only on macOS.")
                return
            }
        }

        val ed = e.getData(CommonDataKeys.EDITOR) ?: return
        val file = e.getData(CommonDataKeys.PSI_FILE) ?: return
        val project = e.getData(CommonDataKeys.PROJECT) ?: return

        val selected = ed.selectionModel.selectedText?.trim()
        if (selected == null || selected.isEmpty()) {
            Popup(e.project).apply {
                show("Please, select the text to export to the Bear.")
            }
            return
        }

        val code = Code(selected)

        val language = file.language.displayName.toLowerCase()
        val textTemplate = """```$language${System.lineSeparator()}%s${System.lineSeparator()}```"""

        val query = URIBuilder(ConfigRepo.load().bear.createUrl).apply {
            addParameter("title", "${project.name} - ${file.name}")
            addParameter("text", textTemplate.format(code.cleaned))
            addParameter("tags", listOf(language).joinToString(","))
            build()
        }

        BrowserUtil.browse(query.toString().replace("+", "%20"))
    }
}