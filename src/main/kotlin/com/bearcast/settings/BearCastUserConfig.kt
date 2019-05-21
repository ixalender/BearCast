package com.bearcast.settings

import com.bearcast.BearCast
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.options.Configurable
import com.intellij.util.xmlb.XmlSerializerUtil
import javax.swing.JComponent

@State(name="BearCastUserConfig", storages = [(Storage("BearCast.xml"))])
object BearCastUserConfig: Configurable, PersistentStateComponent<BearCastSettings> {
    var settings = BearCastSettings()
    private val panel by lazy { SettingsPanel() }

    override fun getState(): BearCastSettings {
        return settings
    }

    override fun loadState(state: BearCastSettings) {
        settings = state
    }


    override fun isModified(): Boolean {
        return panel.projectNameTag != settings.projectNameTag
    }

    override fun getDisplayName(): String {
        return BearCast.APP_NAME
    }

    override fun apply() {
        settings.projectNameTag = panel.projectNameTag
    }

    override fun createComponent(): JComponent {
        return panel.rootPanel
    }

    override fun reset() {
        panel.reset(settings)
    }
}