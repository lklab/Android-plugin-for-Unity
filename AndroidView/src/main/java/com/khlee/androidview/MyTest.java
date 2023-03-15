package com.khlee.androidview;

import android.app.Activity;
import android.widget.Toast;

public class MyTest
{
    private final Activity mContext;

    public MyTest(Activity context)
    {
        mContext = context;

        MyKotlin my = MyKotlin.Companion.getInstance(context);
        my.showToast("hahas");
    }

    public void showToast(String text)
    {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static MyTest getInstance(Activity context)
    {
        return new MyTest(context);
    }
}
