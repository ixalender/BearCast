package com.bearcast.settings

import com.bearcast.BearCast
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.options.Configurable
import com.intellij.util.xmlb.XmlSerializerUtil
import javax.swing.JComponent

@State(name="BearCastUserSettings", storages = [(Storage("BearCast.xml"))])
class BearCastUserSettings: PersistentStateComponent<BearCastSettings> {
    var settings = BearCastSettings()

    override fun getState(): BearCastSettings {
        return settings
    }

    override fun loadState(state: BearCastSettings) {
        settings = state
    }

    var isAddProjectNameTag: Boolean
        get() = settings.addProjectNameTag
        set(addProjectNameTag) {
            settings.addProjectNameTag = addProjectNameTag
        }

    companion object {
        val instance: BearCastUserSettings
            get() = ServiceManager.getService(BearCastUserSettings::class.java)
    }
}