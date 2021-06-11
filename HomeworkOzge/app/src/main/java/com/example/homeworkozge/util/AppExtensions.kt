package com.example.homeworkozge

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.Toast

inline fun Context.toast(message:String, duration:Int){
    Toast.makeText(this, message , duration).show()
}

inline fun Activity.showDataNullDialog() {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setContentView(R.layout.custom_dialog)
    val btn = dialog.findViewById(R.id.button_dialog) as Button
    btn.setOnClickListener { dialog.dismiss() }
    dialog.show()

}