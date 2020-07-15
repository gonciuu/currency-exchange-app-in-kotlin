package com.example.walutki.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class ShowMessageDialog(private val title:String,private val message:String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
                AlertDialog.Builder(it).setTitle(title).setMessage(message)
                    .setPositiveButton("Ok",DialogInterface.OnClickListener{dialog,_ -> dialog.cancel()})
                    .create()
            }?: throw IllegalStateException("Activity cannot be null")
    }
}