package com.khlee.androidview;

import android.app.Activity;
import android.widget.Toast;

public class MyTest
{
    private final Activity mContext;

    public MyTest(Activity context)
    {
        mContext = context;
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
}
