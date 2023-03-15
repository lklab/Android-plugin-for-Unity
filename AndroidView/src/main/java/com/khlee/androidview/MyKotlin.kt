package com.khlee.androidview

import android.app.Activity
import android.widget.Toast

class MyKotlin(var context: Activity)
{
    fun showToast(text: String)
    {
        context.runOnUiThread {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    companion object
    {
        fun getInstance(context: Activity): MyKotlin {
            return MyKotlin(context)
        }
    }
}