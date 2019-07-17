package com.bearcast.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name="BearCastUserSettings", storages = [(Storage("BearCast.xml"))])
class BearCastUserSettings: PersistentStateComponent<BearCastSettings> {
    var settings = BearCastSettings()

    override fun getState(): BearCastSettings {
        return settings
    }

    override fun loadState(state: BearCastSettings) {
        settings = state
    }

    var defaultTags: List<String>
        get() = settings.defaultTags
        set(defaultTags) {
            settings.defaultTags = defaultTags
        }

    var isAddProjectNameTag: Boolean
        get() = settings.addProjectNameTag
        set(addProjectNameTag) {
            settings.addProjectNameTag = addProjectNameTag
        }

    var isAddLanguageTag: Boolean
        get() = settings.addLanguageTag
        set(addLanguageTag) {
            settings.addLanguageTag = addLanguageTag
        }

    companion object {
        val instance: BearCastUserSettings
            get() = ServiceManager.getService(BearCastUserSettings::class.java)
    }
}