package com.magic.Owners.presentation.ui.general

import android.app.Activity
import android.app.AlertDialog
import com.magic.Owners.R

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
class DialogsManager(private val activity: Activity) {

    fun showAlertDialog(
        title: String,
        message: String,
        listener: AlertInteractionListener? = null
    ): AlertDialog? {
        return showAlertDialog(title, message, activity.getString(android.R.string.ok), listener)
    }

    fun showAlertDialog(
        title: String,
        message: String,
        buttonText: String,
        listener: AlertInteractionListener? = null
    ): AlertDialog {
        return AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(buttonText) { _, _ -> listener?.onOkPressed() }
            .show()
    }

    fun showQuestionDialog(
        title: String,
        message: String,
        listener: QuestionInteractionListener
    ): AlertDialog? {
        return showQuestionDialog(
            title,
            message,
            activity.getString(android.R.string.yes),
            listener
        )
    }

    fun showQuestionDialog(
        title: String,
        message: String,
        okBtnText: String,
        cancelText: String,
        listener: QuestionInteractionListener
    ): AlertDialog {
        return AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(okBtnText) { _, _ -> listener.onOkPressed() }
            .setNegativeButton(cancelText) { _, _ -> listener.onCancelPressed() }
            .show()
    }

    fun showQuestionDialog(
        title: String,
        message: String,
        okBtnText: String,
        listener: QuestionInteractionListener
    ): AlertDialog {
        return showQuestionDialog(
            title, message, okBtnText,
            activity.getString(android.R.string.no), listener
        )
    }

    fun showErrorDialog(message: String?) {
        showAlertDialog(
            activity.getString(R.string.general_error),
            message ?: activity.getString(R.string.general_error_message)
        )
    }

    interface QuestionInteractionListener {
        fun onOkPressed()

        fun onCancelPressed()
    }

    interface AlertInteractionListener {
        fun onOkPressed()
    }
}
