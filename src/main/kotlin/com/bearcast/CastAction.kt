package com.bearcast

import com.bearcast.model.BearAppNote
import com.bearcast.settings.BearCastUserSettings
import com.bearcast.ui.Popup
import com.bearcast.url.BearAppUrl
import com.intellij.ide.BrowserUtil
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

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

        val bearAppNote = BearAppNote(
            title = "${project.name} - ${file.name}",
            text = textTemplate.format(code.cleaned),
            tags = mutableListOf<String>()
                .also {
                    "projects/${project.name}".takeIf {
                        BearCastUserSettings.instance.isAddProjectNameTag
                    }?.let(it::add)
                }
                .also {
                    language.takeIf {
                        BearCastUserSettings.instance.isAddLanguageTag
                    }?.let(it::add)
                }
        )
        val url = BearAppUrl(ConfigRepo.load().bear.createUrl).forNote(bearAppNote)

        BrowserUtil.browse(url)
    }
}