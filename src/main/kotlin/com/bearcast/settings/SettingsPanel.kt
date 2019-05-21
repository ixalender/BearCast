package com.bearcast.settings

import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.layout.Cell
import com.intellij.ui.layout.GrowPolicy
import com.intellij.ui.layout.LCFlags
import com.intellij.ui.layout.panel
import java.util.*
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPanel
import kotlin.reflect.KProperty

internal class SettingsPanel {
    private val projectNameTagCheckBox = JBCheckBox(resourceText("projectNameTagLabel"))

    fun resourceText(s: String) = ResourceBundle.getBundle("BearCastResources").getString(s)
    fun Cell.short(component: JComponent) = component(growPolicy = GrowPolicy.SHORT_TEXT)
    fun Cell.medium(component: JComponent) = component(growPolicy = GrowPolicy.MEDIUM_TEXT)

    internal val rootPanel: JPanel = panel {
        titledRow(resourceText("settingsTitle")) {
            row {
                short(projectNameTagCheckBox)
            }
        }
    }

    internal var projectNameTag by projectNameTagCheckBox

    fun reset(settings: BearCastSettings) {
        projectNameTag = settings.projectNameTag
    }

    private operator fun JCheckBox.getValue(a: SettingsPanel, p: KProperty<*>) = isEnabled
    private operator fun JCheckBox.setValue(a: SettingsPanel, p: KProperty<*>, enabled: Boolean) {
        isEnabled = enabled
    }
}