package com.bearcast.url

import com.bearcast.model.BearAppNote

interface AppUrl {
    fun forNote(bearAppNote: BearAppNote): String
}