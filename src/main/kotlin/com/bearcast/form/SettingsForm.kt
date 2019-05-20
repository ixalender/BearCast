package com.bearcast.form

import com.intellij.openapi.project.Project
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.event.KeyEvent
import javax.swing.*

class SettingsForm() : JDialog() {
    private var contentPane: JPanel? = null
    private var buttonOK: JButton? = null
    private var buttonCancel: JButton? = null
    private var chkProjectNameTag: JCheckBox? = null

    private var okFlag: Int = OK_FLAG_CANCEL

    init {
        val form = SettingsFormBase()

        this.contentPane = form.contentPane
        this.buttonOK = form.buttonOK
        this.buttonCancel = form.buttonCancel
        this.chkProjectNameTag = form.chkProjectNameTag

        isModal = true
        this.setContentPane(form.contentPane)
        this.getRootPane().defaultButton = form.buttonOK

        this.buttonOK?.addActionListener { onOK() }
        this.buttonCancel?.addActionListener { onCancel() }
        this.chkProjectNameTag?.addActionListener { println(it.paramString()) }


        defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
        this.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                onCancel()
            }
        })

        this.contentPane?.registerKeyboardAction(
            { onCancel() },
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        )

        form.setLocationRelativeTo(null);
    }

    private fun onOK() {
        okFlag = OK_FLAG_OK
        dispose()
    }

    private fun onCancel() {
        okFlag = OK_FLAG_CANCEL
        dispose()
    }

    companion object {
        const val OK_FLAG_CANCEL = 0
        const val OK_FLAG_OK = 1

        @JvmStatic
        fun show(project: Project): Int {
            val dialog = SettingsForm()

            dialog.pack()
            dialog.isVisible = true

            return dialog.okFlag
        }

        @JvmStatic
        fun main() {
            val dialog = SettingsForm()
            dialog.setLocationRelativeTo(null)
            dialog.pack()
            dialog.isVisible = true
//            System.exit(0)
        }
    }

}