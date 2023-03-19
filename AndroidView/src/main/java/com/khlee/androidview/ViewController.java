package com.khlee.androidview;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unity3d.player.UnityPlayer;

/* refer: https://jhoplin7259.tistory.com/224 */
public class ViewController
{
    private final Activity mContext;
    private RelativeLayout mRootLayout;
    private View mMainView = null;

    private TextView mCountText;
    private int mCount;

    public ViewController(Activity context)
    {
        mContext = context;

        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRootLayout = new RelativeLayout(mContext);
                RelativeLayout.LayoutParams rootLayoutParams = new RelativeLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                mContext.addContentView(mRootLayout, rootLayoutParams);
            }
        });
    }

    public void show()
    {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mMainView != null)
                    return;

                mMainView = mContext.getLayoutInflater().inflate(R.layout.my_view, mRootLayout);

                mCountText = (TextView)mMainView.findViewById(R.id.count_text);
                mCount = 0;

                Button addCountButton = (Button)mMainView.findViewById(R.id.add_unity_count);
                addCountButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UnityPlayer.UnitySendMessage(
                                "NativeMessageReceiver",
                                "AddCount",
                                "");
                    }
                });
            }
        });
    }

    public void hide()
    {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mMainView == null)
                    return;

                mRootLayout.removeView(mMainView);
                mMainView = null;
            }
        });
    }

    public void addCount()
    {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCount++;
                mCountText.setText("android count: " + mCount);
            }
        });
    }
}
