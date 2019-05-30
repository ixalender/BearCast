package com.bearcast

import com.bearcast.settings.BearCastUserSettings
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

        val file = e.getData(CommonDataKeys.PSI_FILE) ?: return
        val project = e.getData(CommonDataKeys.PROJECT) ?: return

        val selected = with(e.getData(CommonDataKeys.EDITOR) ?: return) {
            selectionModel.selectedText?.trim()
        }

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
            mapOf(
                "title" to "${project.name} - ${file.name}",
                "text" to textTemplate.format(code.cleaned),
                "tags" to mutableListOf(language).also {
                    "projects/${project.name}".takeIf{
                        BearCastUserSettings.instance.isAddProjectNameTag
                    }?.let(it::add)
                }.joinToString(",")
            ).forEach { k, v ->
                this.addParameter(k, v)
            }

            build()
        }

        BrowserUtil.browse(query.toString().replace("+", "%20"))
    }
}