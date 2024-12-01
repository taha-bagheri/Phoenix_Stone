package com.tahabagheri.phoenixstone

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

class ProgressDialogHelper(private val context: Context) {

    private var dialog: Dialog? = null

    /**
     * Show the progress dialog with an optional message.
     * @param message The message to display in the dialog.
     */
    fun show(message: String? = null) {
        if (dialog == null) {
            dialog = Dialog(context)
            val view: View =
                LayoutInflater.from(context).inflate(R.layout.dialog_progress, null)
            dialog?.apply {
                setContentView(view)
                setCancelable(false)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                // Set the message if provided
                message?.let {
                    val messageTextView = view.findViewById<TextView>(R.id.progress_message)
                    messageTextView.text = it
                    messageTextView.visibility = View.VISIBLE
                }
            }
        }
        dialog?.show()
    }

    /**
     * Dismiss the progress dialog if it is showing.
     */
    fun dismiss() {
        dialog?.dismiss()
        dialog = null
    }
}
