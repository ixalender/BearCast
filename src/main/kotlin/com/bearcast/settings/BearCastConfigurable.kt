package com.bearcast.settings

import com.bearcast.BearCast
import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JCheckBox
import javax.swing.JPanel
import javax.swing.JTextField

class BearCastConfigurable : Configurable {
    private lateinit var panel: JPanel
    private lateinit var addProjectNameTagCheckBox: JCheckBox
    private lateinit var addLanguageTagCheckBox: JCheckBox
    private lateinit var defaultTagsTextField: JTextField

    @Nls
    override fun getDisplayName() = BearCast.APP_NAME
    override fun getHelpTopic(): String? = null

    private fun init() {
        val settings = BearCastUserSettings.instance

        defaultTagsTextField.text = settings.defaultTags.joinToString(", ")
        addProjectNameTagCheckBox.isSelected = settings.isAddProjectNameTag
        addLanguageTagCheckBox.isSelected = settings.isAddLanguageTag
    }

    override fun isModified(): Boolean {
        val settings = BearCastUserSettings.instance

        return addProjectNameTagCheckBox.isSelected != settings.isAddProjectNameTag ||
                addLanguageTagCheckBox.isSelected != settings.isAddLanguageTag ||
                defaultTagsTextField.text != settings.defaultTags.joinToString(", ")
    }

    override fun apply() {
        val settings = BearCastUserSettings.instance

        settings.defaultTags = defaultTagsTextField
            .text.splitToSequence(",")
            .toList().map(String::trim)
        settings.isAddProjectNameTag = addProjectNameTagCheckBox.isSelected
        settings.isAddLanguageTag = addLanguageTagCheckBox.isSelected
    }

    override fun createComponent() = panel

    override fun reset() {
        init()
    }
}