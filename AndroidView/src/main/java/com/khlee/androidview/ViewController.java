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

        mContext.runOnUiThread(() -> {
            mRootLayout = new RelativeLayout(mContext);
            RelativeLayout.LayoutParams rootLayoutParams = new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            mContext.addContentView(mRootLayout, rootLayoutParams);
        });
    }

    public void show()
    {
        mContext.runOnUiThread(() -> {
            if (mMainView != null)
                return;

            mMainView = mContext.getLayoutInflater().inflate(R.layout.my_view, mRootLayout, false);
            mRootLayout.addView(mMainView);

            mCountText = mMainView.findViewById(R.id.count_text);
            mCount = 0;
            setCountText(mCountText, mCount);

            Button addCountButton = mMainView.findViewById(R.id.add_unity_count);
            addCountButton.setOnClickListener(v -> UnityPlayer.UnitySendMessage(
                    "NativeMessageReceiver",
                    "AddCount",
                    ""));
        });
    }

    public void hide()
    {
        mContext.runOnUiThread(() -> {
            if (mMainView == null)
                return;

            mRootLayout.removeView(mMainView);
            mMainView = null;
        });
    }

    public void addCount()
    {
        mContext.runOnUiThread(() -> {
            if (mMainView == null)
                return;

            mCount++;
            setCountText(mCountText, mCount);
        });
    }

    private void setCountText(TextView text, int count)
    {
        text.setText("android count: " + count);
    }
}
