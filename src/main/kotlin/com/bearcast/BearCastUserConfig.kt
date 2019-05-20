package com.bearcast

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name="BearCastUserConfig", storages = [Storage("BearCastUserConfig.xml")])
class BearCastUserConfig: PersistentStateComponent<BearCastUserConfig> {
    override fun loadState(state: BearCastUserConfig) {
        XmlSerializerUtil.copyBean(state, this)
    }

    override fun getState(): BearCastUserConfig? {
        return this
    }
}