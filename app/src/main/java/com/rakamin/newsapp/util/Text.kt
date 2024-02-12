package com.rakamin.newsapp.util

import android.content.Context
import android.widget.Toast

class Text {
    fun makeAToast (context: Context, message : String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}