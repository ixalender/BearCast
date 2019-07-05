package com.bearcast.settings

import com.bearcast.BearCast
import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPanel

class BearCastConfigurable : Configurable {
    private lateinit var panel: JPanel
    private lateinit var addProjectNameTagCheckBox: JCheckBox
    private lateinit var addLanguageTagCheckBox: JCheckBox

    @Nls
    override fun getDisplayName() = BearCast.APP_NAME
    override fun getHelpTopic(): String? = null

    private fun init() {
        val settings = BearCastUserSettings.instance

        addProjectNameTagCheckBox.isSelected = settings.isAddProjectNameTag
        addLanguageTagCheckBox.isSelected = settings.isAddLanguageTag
    }

    override fun isModified(): Boolean {
        val settings = BearCastUserSettings.instance

        return addProjectNameTagCheckBox.isSelected != settings.isAddProjectNameTag ||
                addLanguageTagCheckBox.isSelected != settings.isAddLanguageTag
    }

    override fun apply() {
        val settings = BearCastUserSettings.instance

        settings.isAddProjectNameTag = addProjectNameTagCheckBox.isSelected
        settings.isAddLanguageTag = addLanguageTagCheckBox.isSelected
    }

    override fun createComponent(): JComponent {
        return panel
    }

    override fun reset() {
        init()
    }
}