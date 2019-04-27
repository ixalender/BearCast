package com.bearcast

import com.bearcast.BearCast.Companion.APP_NAME
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

class Notification (
    project: Project? = null,
    type: NotificationType = NotificationType.INFORMATION
) {
    private var project: Project? = null
    private var type: NotificationType

    init {
         this.project = project
         this.type = type
    }

    fun notify(text: String) {
        Notification(
            APP_NAME,
            APP_NAME,
            text,
            type
        ).apply { notify(project) }
    }
}