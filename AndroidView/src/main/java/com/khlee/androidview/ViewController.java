package com.khlee.androidview;

import android.app.Activity;
import android.widget.Button;
import android.widget.LinearLayout;

/* refer: https://jhoplin7259.tistory.com/224 */
public class ViewController
{
    private final Activity mContext;
    private LinearLayout mLayout;
    private LinearLayout.LayoutParams mLayoutParams;

    public ViewController(Activity context)
    {
        mContext = context;

        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLayout = new LinearLayout(mContext);
                mLayout.setOrientation(LinearLayout.VERTICAL);

                mLayoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                mContext.addContentView(mLayout, mLayoutParams);
            }
        });
    }

    public void show()
    {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Button button = new Button(mContext);
                button.setText("This is button");
                button.setLayoutParams(mLayoutParams);
                mLayout.addView(button);
            }
        });
    }
}
